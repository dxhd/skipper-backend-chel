package com.tinkoff.skipper.dto;

import com.tinkoff.skipper.entity.UserEntity;
import lombok.Data;

@Data
public class UserMenteeProfileDto {

    private String username;
    private String userPicture;
    private String description;

    //stats
    private StatsDto stats;
    private Integer attendance;
    private Integer attendancePastMonth;

    //private double rating;
    //private Set<MentorInfoEntity> mentorInfo;

    public static UserMenteeProfileDto toModel(UserEntity entity, StatsDto stats) {
        UserMenteeProfileDto model = new UserMenteeProfileDto();
        model.setUsername(entity.getUsername());
        model.setUserPicture(entity.getUserPicture());
        model.setDescription(entity.getDescription());
        model.setStats(stats);
        // TODO: calculate attendance
        return model;
    }
}
