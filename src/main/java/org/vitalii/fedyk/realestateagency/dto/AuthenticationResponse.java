package org.vitalii.fedyk.realestateagency.dto;

import lombok.Builder;

@Builder
public record AuthenticationResponse(String accessToken, String refreshToken) {
}
