package org.vitalii.fedyk.realestateagency.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import org.vitalii.fedyk.realestateagency.entity.Token;
import org.vitalii.fedyk.realestateagency.repository.TokenRepository;

import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Transactional
public class CustomLogoutHandler implements LogoutHandler {
    private final TokenRepository tokenRepository;

    @Override
    public void logout(final HttpServletRequest request,
                       final HttpServletResponse response,
                       final Authentication authentication) {
        final String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (Objects.isNull(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")) {
            return;
        }
        final String accessToken = authorizationHeader.substring(7);
        final Optional<Token> token = tokenRepository.findByAccessToken(accessToken);
        token.ifPresent(value -> value.setLoggedOut(true));
    }
}
