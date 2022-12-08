package com.tinkoff.skipper.dto;

import com.tinkoff.skipper.entity.MentorInfoEntity;
import com.tinkoff.skipper.entity.UserEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class MentorProfileDto {

    private String username;
    private String userPicture;
    private String description;
    private Integer studentNumber;
    private StatsDto stats;
    private String reviews;
    private String lessonTemplates;
    private BigDecimal timeZone;
    private String speciality;
    private BigDecimal rating;
    private LocalDate registrationDate;
    private Long id;

    public static MentorProfileDto toModel(UserEntity userEntity, MentorInfoEntity mentorInfoEntity, StatsDto stats)
    {
        MentorProfileDto model = new MentorProfileDto();
        model.setUsername(mentorInfoEntity.getUser().getUsername());
        model.setDescription(mentorInfoEntity.getDescription());
        model.setUserPicture(userEntity.getUserPicture());
        //model.setStudentNumber(mentorInfoEntity.getStudentNumber());
        model.setStats(stats);
        model.setTimeZone(userEntity.getTimeZone());
        model.setSpeciality(mentorInfoEntity.getSpeciality());
        model.setRating(mentorInfoEntity.getRating());
        model.setRegistrationDate(userEntity.getCreatedAt());
        model.setId(userEntity.getId());

        return model;
    }
}
