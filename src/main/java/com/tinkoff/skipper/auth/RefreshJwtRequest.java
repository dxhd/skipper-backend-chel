package com.tinkoff.skipper.auth;

import lombok.Data;

@Data
public class RefreshJwtRequest {
    private String refreshToken;
}
