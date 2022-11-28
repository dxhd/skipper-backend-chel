package com.tinkoff.skipper.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MentorDataDto {
    private String subjects;
    private BigDecimal price;
    private String description;
    private String workExperience;
    private String certificates;
    private String education;
}