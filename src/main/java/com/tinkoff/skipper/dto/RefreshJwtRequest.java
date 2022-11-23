package com.tinkoff.skipper.dto;

import lombok.Data;

@Data
public class RefreshJwtRequest {
    private String refreshToken;
}
