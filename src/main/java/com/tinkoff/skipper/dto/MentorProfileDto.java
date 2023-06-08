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
    private StatsDto stats;
    //    private Integer studentNumber;
//    private String reviews;
//    private String lessonTemplates;
//    private BigDecimal timeZone;
//    private BigDecimal rating;
    private LocalDate registrationDate;
    private String speciality;
    private String category;
    private String[] tags;
    private Long id;
    private BigDecimal rating;

    public static MentorProfileDto toModel(UserEntity userEntity, MentorInfoEntity mentorInfoEntity, StatsDto stats, BigDecimal rating)
    {
        MentorProfileDto model = new MentorProfileDto();
        model.setUsername(mentorInfoEntity.getUser().getUsername());
        model.setDescription(mentorInfoEntity.getDescription());
        model.setUserPicture(userEntity.getUserPictureURL());
        model.setCategory(mentorInfoEntity.getCategory().getName());
        model.setTags(mentorInfoEntity.getTags().stream().map(
                (tag) -> tag.getName()
        ).toList().toArray(String[] :: new));
        model.setStats(stats);
        model.setSpeciality(mentorInfoEntity.getSpeciality());
        model.setRegistrationDate(userEntity.getCreatedAt());
        model.setRating(rating);
        model.setId(userEntity.getId());
//        model.setStudentNumber(mentorInfoEntity.getStudentNumber());
//        model.setTimeZone(userEntity.getTimeZone());
//        model.setRating(mentorInfoEntity.getRating());

        return model;
    }
}
