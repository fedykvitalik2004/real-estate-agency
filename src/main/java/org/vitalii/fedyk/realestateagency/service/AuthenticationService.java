package org.vitalii.fedyk.realestateagency.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.vitalii.fedyk.realestateagency.dto.AuthenticationResponse;
import org.vitalii.fedyk.realestateagency.dto.ConsumerRegisterRequest;
import org.vitalii.fedyk.realestateagency.dto.RealtorRegisterRequest;

import java.io.IOException;

public interface AuthenticationService {
    AuthenticationResponse registerConsumer(ConsumerRegisterRequest consumerRegisterRequest);
    AuthenticationResponse registerRealtor(RealtorRegisterRequest realtorRegisterRequest);
    AuthenticationResponse login(String email, String password);
    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
