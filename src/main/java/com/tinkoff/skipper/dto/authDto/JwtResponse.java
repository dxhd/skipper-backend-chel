package com.tinkoff.skipper.dto.authDto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JwtResponse {
    private final String type = "Bearer";
    private final String accessToken;
    private final String refreshToken;
}
