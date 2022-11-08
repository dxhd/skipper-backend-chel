package com.tinkoff.skipper.model;

import com.tinkoff.skipper.entity.MenteeInfoEntity;
import lombok.Data;

@Data
public class Mentee {
    private int allLessons;
    private int attendance;
    private int cancelledLessons;
    private int allLessonsPastMonth;
    private int attendancePastMonth;
    private int cancelledLessonsPastMonth;
    private int allLessonsPast3Month;
    private int attendancePast3Month;
    private int cancelledLessonsPast3Month;

    public static Mentee toModel(MenteeInfoEntity info) {

        Mentee model = new Mentee();

        int _allLessons;
        int _cancelledLessons;
        int _attendance;

        _allLessons = info.getAllLessons();
        _cancelledLessons = info.getCancelledLessons();
        _attendance = (int) (100 - (_cancelledLessons * 1.0) / _allLessons);
        model.setCancelledLessons(_cancelledLessons);
        model.setAllLessons(_allLessons);
        model.setAttendance(_attendance);

        _allLessons = info.getAllLessonsPastMonth();
        _cancelledLessons = info.getCancelledLessonsPastMonth();
        _attendance = (int) (100 - (_cancelledLessons * 1.0) / _allLessons);
        model.setCancelledLessonsPastMonth(_cancelledLessons);
        model.setAllLessonsPastMonth(_allLessons);
        model.setAttendancePastMonth(_attendance);

        _allLessons = info.getAllLessonsPast3Month();
        _cancelledLessons = info.getCancelledLessonsPast3Month();
        _attendance = (int) (100 - (_cancelledLessons * 1.0) / _allLessons);
        model.setCancelledLessonsPast3Month(_cancelledLessons);
        model.setAllLessonsPast3Month(_allLessons);
        model.setAttendancePast3Month(_attendance);

        return model;

    }
}
