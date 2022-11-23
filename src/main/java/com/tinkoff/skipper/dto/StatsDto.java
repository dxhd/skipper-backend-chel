package com.tinkoff.skipper.dto;


public interface StatsDto {

    Integer getAllLessons();
    Integer getCancelledLessons();
    default Integer getAttendance() {
        Double attendance = (100 - (getCancelledLessons() * 1.0) / getAllLessons());
        return attendance.intValue();
    }

    Integer getAllLessonsPastMonth();
    Integer getCancelledLessonsPastMonth();
    default Integer getAttendancePastMonth() {
        Double attendance = (100 - (getCancelledLessonsPastMonth() * 1.0) / getAllLessonsPastMonth());
        return attendance.intValue();
    }

    Integer getAllLessonsPast3Month();
    Integer getCancelledLessonsPast3Month();

    default Integer getAttendancePast3Month() {
        Double attendance = (100 - (getCancelledLessonsPast3Month() * 1.0) / getAllLessonsPast3Month());
        return attendance.intValue();
    }

}
