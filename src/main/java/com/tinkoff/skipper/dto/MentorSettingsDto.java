package com.tinkoff.skipper.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Builder
public class MentorSettingsDto {
    private final String category;
    private final Set<String> tags;
    private final String speciality;
    private final String description;
    private final String workExperience;
    private final String education;
    private final Long userId;

}
