package org.vitalii.fedyk.realestateagency.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.vitalii.fedyk.realestateagency.dto.AuthenticationResponse;
import org.vitalii.fedyk.realestateagency.dto.ConsumerRegisterRequest;
import org.vitalii.fedyk.realestateagency.dto.RealtorRegisterRequest;
import org.vitalii.fedyk.realestateagency.entity.*;
import org.vitalii.fedyk.realestateagency.enumeration.UserRole;
import org.vitalii.fedyk.realestateagency.exception.NotFoundException;
import org.vitalii.fedyk.realestateagency.exception.UserAlreadyRegistered;
import org.vitalii.fedyk.realestateagency.repository.TokenRepository;
import org.vitalii.fedyk.realestateagency.repository.UserRepository;
import org.vitalii.fedyk.realestateagency.service.AuthenticationService;
import org.vitalii.fedyk.realestateagency.service.JwtService;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse login(final String email, final String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        final User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("exception_user_not_found"));

        final String accessToken = jwtService.generateAccessToken(user);
        final String refreshToken = jwtService.generateRefreshToken(user);

        revokeAllTokens(user.getId());
        saveToken(accessToken, refreshToken, user);

        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public AuthenticationResponse registerConsumer(ConsumerRegisterRequest consumerRegisterRequest) {
        if (userRepository.existsByEmail(consumerRegisterRequest.getEmail())) {
            throw new UserAlreadyRegistered("exception_user_already_registered",
                    List.of(consumerRegisterRequest.getEmail()));
        }
        Consumer consumer = getConsumer(consumerRegisterRequest);
        consumer.setUserRole(UserRole.CONSUMER);
        consumer = userRepository.save(consumer);

        return createAuthenticationResponse(consumer);
    }

    private void saveToken(final String accessToken, final String refreshToken, final User user) {
        final Token token = Token.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .loggedOut(false)
                .user(user)
                .build();
        tokenRepository.save(token);
    }

    private Consumer getConsumer(final ConsumerRegisterRequest consumerRegisterRequest) {
        return Consumer.builder()
                .fullName(new FullName(consumerRegisterRequest.getFirstName(), consumerRegisterRequest.getLastName()))
                .dateOfBirth(consumerRegisterRequest.getDateOfBirth())
                .email(consumerRegisterRequest.getEmail())
                .password(passwordEncoder.encode(consumerRegisterRequest.getPassword()))
                .build();
    }

    @Override
    public AuthenticationResponse registerRealtor(final RealtorRegisterRequest realtorRegisterRequest) {
        if (userRepository.existsByEmail(realtorRegisterRequest.getEmail())) {
            throw new UserAlreadyRegistered("exception_user_already_registered",
                    List.of(realtorRegisterRequest.getEmail()));
        }
        Realtor realtor = getRealtor(realtorRegisterRequest);
        realtor.setUserRole(UserRole.REALTOR);
        realtor = userRepository.save(realtor);

        return createAuthenticationResponse(realtor);
    }

    private AuthenticationResponse createAuthenticationResponse(final User user) {
        final String jwtAccessToken = jwtService.generateAccessToken(user);
        final String jwtRefreshToken = jwtService.generateRefreshToken(user);
        saveToken(jwtAccessToken, jwtRefreshToken, user);

        return AuthenticationResponse.builder()
                .accessToken(jwtAccessToken)
                .refreshToken(jwtRefreshToken)
                .build();

    }

    private Realtor getRealtor(final RealtorRegisterRequest realtorRegisterRequest) {
        return Realtor.builder()
                .fullName(new FullName(realtorRegisterRequest.getFirstName(), realtorRegisterRequest.getLastName()))
                .dateOfBirth(realtorRegisterRequest.getDateOfBirth())
                .email(realtorRegisterRequest.getEmail())
                .password(passwordEncoder.encode(realtorRegisterRequest.getPassword()))
                .phoneNumber(realtorRegisterRequest.getPhoneNumber())
                .build();
    }

    private void revokeAllTokens(final long userId) {
        List<Token> tokens = tokenRepository.findAllValidTokensByUserId(userId);
        tokens.forEach((token) -> token.setLoggedOut(true));
    }

    @Override
    public void refreshToken(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (Objects.isNull(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")) {
            return;
        }
        final String refreshToken = authorizationHeader.substring(7);
        final String username = jwtService.extractUsername(refreshToken);

        final User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new NotFoundException("exception_user_not_found"));

        if (jwtService.isValidRefreshToken(refreshToken, user)) {
            final Token token = tokenRepository.findByRefreshToken(refreshToken)
                    .orElseThrow(() -> new NotFoundException("exception_token_not_found"));
            final String accessToken = jwtService.generateAccessToken(user);

            token.setAccessToken(accessToken);

            final AuthenticationResponse authenticationResponse = AuthenticationResponse.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
            adjustResponse(response, authenticationResponse);
        }
    }

    private void adjustResponse(final HttpServletResponse response, final AuthenticationResponse authenticationResponse) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final String json = mapper.writeValueAsString(authenticationResponse);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(json);
    }
}
