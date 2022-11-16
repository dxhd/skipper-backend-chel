package com.tinkoff.skipper.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MentorDto {
    private String subject;
    private String username;
    private BigDecimal price;
    private String description;
    private BigDecimal rating;
    private String workExperience;
    private String certificate;
    private String education;
    private Long userId;
}
