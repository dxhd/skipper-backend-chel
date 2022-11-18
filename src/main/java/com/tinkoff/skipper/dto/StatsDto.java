package com.tinkoff.skipper.dto;


public interface StatsDto {

    Integer getAllLessons();
    Integer getCancelledLessons();

    Integer getAllLessonsPastMonth();
     Integer getCancelledLessonsPastMonth();

    Integer getAllLessonsPast3Month();
    Integer getCancelledLessonsPast3Month();

}
