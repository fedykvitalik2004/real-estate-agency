package org.vitalii.fedyk.realestateagency.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vitalii.fedyk.realestateagency.dto.AuthenticationRequest;
import org.vitalii.fedyk.realestateagency.dto.AuthenticationResponse;
import org.vitalii.fedyk.realestateagency.dto.ConsumerRegisterRequest;
import org.vitalii.fedyk.realestateagency.dto.RealtorRegisterRequest;
import org.vitalii.fedyk.realestateagency.service.AuthenticationService;

import java.io.IOException;
import java.util.Locale;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthenticationController {
    private AuthenticationService authenticationService;

    @PostMapping("/users/consumer")
    public AuthenticationResponse registerConsumer(@Valid @RequestBody ConsumerRegisterRequest consumerRegisterRequest) {
        return authenticationService.registerConsumer(consumerRegisterRequest);
    }

    @PostMapping("/users/realtor")
    public AuthenticationResponse registerRealtor(@Valid @RequestBody RealtorRegisterRequest realtorRegisterRequest) {
        return authenticationService.registerRealtor(realtorRegisterRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@Valid @RequestBody final AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(
                authenticationService.login(authenticationRequest.email(), authenticationRequest.password())
        );
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<Void> refreshToken(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        authenticationService.refreshToken(request, response);
        return ResponseEntity.ok().build();
    }
}
