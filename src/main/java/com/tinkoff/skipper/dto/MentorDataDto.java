package com.tinkoff.skipper.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class MentorDataDto {
    private final String[] tags;
    private final BigDecimal price;
    private final String description;
    private final String workExperience;
    private final String certificates;
    private final String education;
    private final String speciality;
}