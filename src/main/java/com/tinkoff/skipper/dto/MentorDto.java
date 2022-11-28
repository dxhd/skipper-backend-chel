package com.tinkoff.skipper.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Builder
public class MentorDto { //TODO: добавить всем дто-шкам final и заменить @Data на @Getter и @Builder
    private final String subjects;
    private final BigDecimal price;
    private final String description;
    private final String workExperience;
    private final String certificates;
    private final String education;
    private final Long userId;
}
