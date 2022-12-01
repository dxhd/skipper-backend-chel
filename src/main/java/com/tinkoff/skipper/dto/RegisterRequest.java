package com.tinkoff.skipper.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegisterRequest {

    private String phoneNumber;
    private String password;

}
