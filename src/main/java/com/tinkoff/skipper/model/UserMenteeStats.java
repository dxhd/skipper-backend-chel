package com.tinkoff.skipper.model;

import com.tinkoff.skipper.DTO.UserMenteeStatsDTO;
import lombok.Data;

@Data
public class UserMenteeStats {

    private Integer allLessons;
    private Integer attendance;
    private Integer cancelledLessons;
    private Integer allLessonsPastMonth;
    private Integer attendancePastMonth;
    private Integer cancelledLessonsPastMonth;
    private Integer allLessonsPast3Month;
    private Integer attendancePast3Month;
    private Integer cancelledLessonsPast3Month;

    public static UserMenteeStats toModel(UserMenteeStatsDTO info) {

        UserMenteeStats model = new UserMenteeStats();

        Double allLessons;
        Double cancelledLessons;
        Double attendance;

        //вся статистика
        allLessons = info.getAllLessons().doubleValue();
        cancelledLessons = info.getCancelledLessons().doubleValue();
        attendance = (100 - (cancelledLessons * 1.0) / allLessons);

        model.setCancelledLessons(cancelledLessons.intValue());
        model.setAllLessons(allLessons.intValue());
        model.setAttendance(attendance.intValue());

        //статистика за последний месяц
        allLessons = info.getAllLessonsPastMonth().doubleValue();
        cancelledLessons = info.getCancelledLessonsPastMonth().doubleValue();
        attendance = (100 - (cancelledLessons * 1.0) / allLessons);

        model.setCancelledLessonsPastMonth(cancelledLessons.intValue());
        model.setAllLessonsPastMonth(allLessons.intValue());
        model.setAttendancePastMonth(attendance.intValue());

        //статистика за последние 3 месяца
        allLessons = info.getAllLessonsPast3Month().doubleValue();
        cancelledLessons = info.getCancelledLessonsPast3Month().doubleValue();
        attendance = (100 - (cancelledLessons * 1.0) / allLessons);

        model.setCancelledLessonsPast3Month(cancelledLessons.intValue());
        model.setAllLessonsPast3Month(allLessons.intValue());
        model.setAttendancePast3Month(attendance.intValue());

        return model;

    }
}
