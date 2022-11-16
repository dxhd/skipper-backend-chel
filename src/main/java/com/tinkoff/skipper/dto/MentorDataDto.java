package com.tinkoff.skipper.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MentorDataDto {
    private String subjects;
    private String username;
    private BigDecimal price;
    private String description;
    private BigDecimal rating;
    private String workExperience;
    private String certificates;
    private String education;
}
