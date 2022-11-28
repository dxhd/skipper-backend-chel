package com.tinkoff.skipper.dto;

import com.tinkoff.skipper.entity.UserEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class MenteeProfileDto {

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

    //private double rating;
    //private Set<MentorInfoEntity> mentorInfo;

    public static MenteeProfileDto toModel(UserEntity entity, StatsDto stats) {
        MenteeProfileDto model = new MenteeProfileDto();

        model.setUsername(entity.getUsername());
        model.setUserPicture(entity.getUserPicture());
        model.setDescription(entity.getDescription());
        model.setInterests(entity.getInterests());
        model.setTimezone(entity.getTimeZone());
        model.setRegistrationDate(entity.getCreatedAt());
        model.setSpeciality(entity.getSpeciality());

        if (stats.getAllLessons()!=null) {
            model.setStats(stats);
        }

        return model;
    }
}
