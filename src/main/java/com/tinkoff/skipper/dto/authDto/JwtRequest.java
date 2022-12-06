package com.tinkoff.skipper.dto.authDto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JwtRequest {
    private final String phoneNumber;
    private final String email;
    private final String password;
}
