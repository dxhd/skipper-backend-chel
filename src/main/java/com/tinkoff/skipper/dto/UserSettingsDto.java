package com.tinkoff.skipper.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Data
public class UserSettingsDto {

    private Date birthdate;
    private String description;
    private String email;
    private String password;
    private String phoneNumber;
    private BigDecimal timeZone;
    private String username;
    private Set<String> interests;

}
