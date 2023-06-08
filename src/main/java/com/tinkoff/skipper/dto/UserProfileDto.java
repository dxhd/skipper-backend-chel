package com.tinkoff.skipper.dto;

import com.tinkoff.skipper.entity.UserEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class UserProfileDto {

    private String username;
    private String userPicture;
    private String description;
    private String[] interests;
    private LocalDate registrationDate;
    private StatsDto stats;


    public static UserProfileDto toModel(UserEntity entity, StatsDto stats) {
        UserProfileDto model = new UserProfileDto();
        model.setUsername(entity.getUsername());
        model.setUserPicture(entity.getUserPictureURL());
        model.setDescription(entity.getDescription());
        model.setInterests(entity.getInterests().stream().map(
                (interest) -> interest.getName()
            ).toList().toArray(String[] :: new));
        model.setRegistrationDate(entity.getCreatedAt());
        if (stats.getAllLessons() != null) {
            model.setStats(stats);
        }
        return model;
    }
}
