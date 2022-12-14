package com.tinkoff.skipper.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class SearchResultMentorDto {

    private final Long id;

    private final String username;
    private final String userPicture;
    private final String speciality;
    private final String description;
    private final Float rating;
    private final BigDecimal price;

}
