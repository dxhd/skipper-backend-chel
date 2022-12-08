package com.tinkoff.skipper.dto;

import com.tinkoff.skipper.entity.LessonEntity;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class LessonReservationDto {

    private final String lessonType;
    private final Integer lessonLength;
    private final BigDecimal lessonCost;
    private final LocalDateTime lessonDateTime;
    private final String contactInfo;
    private final Long menteeId;
    private Long mentorId;

    public void setMentorId(Long mentorId) {
        this.mentorId = mentorId;
    }

}
