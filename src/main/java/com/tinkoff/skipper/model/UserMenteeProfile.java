package com.tinkoff.skipper.model;


import com.tinkoff.skipper.entity.UserEntity;
import lombok.Data;

@Data
public class UserMenteeProfile {

    private String username;
    private String userPicture;
    private String description;
    private String tags;

    //stats
    private Integer allLessons;
    private Integer attendance;
    private Integer cancelledLessons;
    private Integer allLessonsPastMonth;
    private Integer attendancePastMonth;
    private Integer cancelledLessonsPastMonth;
    private Integer allLessonsPast3Month;
    private Integer attendancePast3Month;
    private Integer cancelledLessonsPast3Month;

    //private double rating;

    //private Set<MentorInfoEntity> mentorInfo;

    public static UserMenteeProfile toModel(UserEntity entity, com.tinkoff.skipper.dto.StatsDto stats) {
        UserMenteeProfile model = new UserMenteeProfile();
        model.setUsername(entity.getUsername());
        model.setDescription(entity.getDescription());
        model.setUserPicture(entity.getUserPicture());
        model.setTags(entity.getInterests());

        //model.setMentorInfo(entity.getMentorInfo());
        //model.setRating(entity.getRating());

        Double allLessons;
        Double cancelledLessons;
        Double attendance;

        //вся статистика
        allLessons = stats.getAllLessons().doubleValue();
        cancelledLessons = stats.getCancelledLessons().doubleValue();
        attendance = (100 - (cancelledLessons * 1.0) / allLessons);

        model.setCancelledLessons(cancelledLessons.intValue());
        model.setAllLessons(allLessons.intValue());
        model.setAttendance(attendance.intValue());

        //статистика за последний месяц
        allLessons = stats.getAllLessonsPastMonth().doubleValue();
        cancelledLessons = stats.getCancelledLessonsPastMonth().doubleValue();
        attendance = (100 - (cancelledLessons * 1.0) / allLessons);

        model.setCancelledLessonsPastMonth(cancelledLessons.intValue());
        model.setAllLessonsPastMonth(allLessons.intValue());
        model.setAttendancePastMonth(attendance.intValue());

        //статистика за последние 3 месяца
        allLessons = stats.getAllLessonsPast3Month().doubleValue();
        cancelledLessons = stats.getCancelledLessonsPast3Month().doubleValue();
        attendance = (100 - (cancelledLessons * 1.0) / allLessons);

        model.setCancelledLessonsPast3Month(cancelledLessons.intValue());
        model.setAllLessonsPast3Month(allLessons.intValue());
        model.setAttendancePast3Month(attendance.intValue());

        return model;
    }

}
