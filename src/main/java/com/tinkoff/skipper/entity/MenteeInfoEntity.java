package com.tinkoff.skipper.entity;

import lombok.Data;

@Data
public class MenteeInfoEntity {

    private int allLessons;
    private int cancelledLessons;
    private int allLessonsPastMonth;
    private int cancelledLessonsPastMonth;
    private int allLessonsPast3Month;
    private int cancelledLessonsPast3Month;

}
