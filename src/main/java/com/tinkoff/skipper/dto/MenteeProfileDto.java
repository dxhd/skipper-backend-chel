package com.tinkoff.skipper.dto;

import com.tinkoff.skipper.entity.UserEntity;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
    private StatsDto stats;

    //TODO: добавить статус "в сети"/"не в сети", рейтинг менти и его менторскую информацию,
    // если он им является


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
