package com.tinkoff.skipper.dto;

import lombok.Data;

import com.tinkoff.skipper.entity.MentorInfoEntity;

import java.time.LocalDate;
import java.math.BigDecimal;

import com.tinkoff.skipper.entity.UserEntity;

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

    // FIXME: merge model and dto
    public static MentorProfileDto toModel(UserEntity userEntity, MentorInfoEntity mentorEntity, StatsDto stats)
    {
        MentorProfileDto model = new MentorProfileDto();
        model.setUsername(mentorEntity.getUsername());
        model.setDescription(mentorEntity.getDescription());
        model.setUserPicture(userEntity.getUserPicture());
        model.setStudentNumber(mentorEntity.getStudentNumber());
        model.setStats(stats);
        model.setTimeZone(userEntity.getTimeZone());
        model.setSpeciality(mentorEntity.getSpeciality());
        model.setRating(mentorEntity.getRating());
        model.setRegistrationDate(userEntity.getCreatedAt());
        model.setId(userEntity.getId());
        return model;
    }
}
