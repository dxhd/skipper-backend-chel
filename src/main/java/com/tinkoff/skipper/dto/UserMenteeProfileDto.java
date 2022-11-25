package com.tinkoff.skipper.dto;

import com.tinkoff.skipper.entity.UserEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class UserMenteeProfileDto {

    private String username;
    private String userPicture;
    private String description;
    private String interests;
    private BigDecimal timezone;
    private LocalDate registrationDate;
    private String speciality;
    //TODO: добавить статус "в сети"/"не в сети"

    //stats
    private StatsDto stats;
    //TODO: попытаться перенести посещаемость и её вычисление в StatsDto
    private Integer attendance;
    private Integer attendancePastMonth;
    private Integer attendancePast3Month;

    //private double rating;
    //private Set<MentorInfoEntity> mentorInfo;

    public static UserMenteeProfileDto toModel(UserEntity entity, StatsDto stats) {
        UserMenteeProfileDto model = new UserMenteeProfileDto();

        model.setUsername(entity.getUsername());
        model.setUserPicture(entity.getUserPicture());
        model.setDescription(entity.getDescription());
        model.setInterests(entity.getInterests());
        model.setTimezone(entity.getTimeZone());
        model.setRegistrationDate(entity.getCreatedAt());
        model.setSpeciality(entity.getSpeciality());
        if (stats.getAllLessons()!=null) {
            model.setStats(stats);


            // TODO: вынести вычисление в отдельный метод либо перенести в StatsDto

            Double allLessons;
            Double cancelledLessons;
            Double attendance;


            //вычисление посещаемости за всё время
            allLessons = stats.getAllLessons().doubleValue();
            cancelledLessons = stats.getCancelledLessons().doubleValue();
            attendance = (100 - (cancelledLessons * 1.0) / allLessons);
            model.setAttendance(attendance.intValue());

            //вычисление посещаемости за последний месяц
            allLessons = stats.getAllLessonsPastMonth().doubleValue();
            cancelledLessons = stats.getCancelledLessonsPastMonth().doubleValue();
            attendance = (100 - (cancelledLessons * 1.0) / allLessons);
            model.setAttendancePastMonth(attendance.intValue());

            //вычисление посещаемости за последние 3 месяца
            allLessons = stats.getAllLessonsPast3Month().doubleValue();
            cancelledLessons = stats.getCancelledLessonsPast3Month().doubleValue();
            attendance = (100 - (cancelledLessons * 1.0) / allLessons);
            model.setAttendancePast3Month(attendance.intValue());
        }
        return model;
    }
}
