package com.tinkoff.skipper.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RefreshJwtRequest {
    private final String refreshToken;
}
