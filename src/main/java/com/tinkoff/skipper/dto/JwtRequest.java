package com.tinkoff.skipper.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JwtRequest {
    private final String username;
    private final String password;
}
