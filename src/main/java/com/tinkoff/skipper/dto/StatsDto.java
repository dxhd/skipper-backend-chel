package com.tinkoff.skipper.dto;

import lombok.Data;

@Data
public class StatsDto {
    private Integer allLessons;
    private Integer allLessonsPastMonth;
    private Integer allLessonsPast3Month;
    private Integer cancelledLessons;
    private Integer cancelledLessonsPastMonth;
    private Integer cancelledLessonsPast3Month;
}
