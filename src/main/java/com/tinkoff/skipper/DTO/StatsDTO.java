package com.tinkoff.skipper.DTO;

import lombok.Data;

@Data
public class StatsDTO {
    private Integer allLessons;
    private Integer allLessonsPastMonth;
    private Integer allLessonsPast3Month;
    private Integer cancelledLessons;
    private Integer cancelledLessonsPastMonth;
    private Integer cancelledLessonsPast3Month;
}
