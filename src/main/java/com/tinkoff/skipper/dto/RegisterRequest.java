package com.tinkoff.skipper.dto;

import lombok.Data;

@Data
public class RegisterRequest {

    private String phoneNumber;
    private String password;

}
