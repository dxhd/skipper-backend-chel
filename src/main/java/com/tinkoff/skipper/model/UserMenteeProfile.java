package com.tinkoff.skipper.model;

import com.tinkoff.skipper.dto.StatsDto;
import com.tinkoff.skipper.entity.UserEntity;
import lombok.Data;

@Data
public class UserMenteeProfile {

    private String username;
    private String userPicture;
    private String description;

    //stats
    private StatsDto stats;
    private Integer attendance;
    private Integer attendancePastMonth;

    //private double rating;
    //private Set<MentorInfoEntity> mentorInfo;

    public static UserMenteeProfile toModel(UserEntity entity, StatsDto stats) {
        UserMenteeProfile model = new UserMenteeProfile();
        model.setUsername(entity.getUsername());
        model.setUserPicture(entity.getUserPicture());
        model.setDescription(entity.getDescription());
        model.setStats(stats);
        // TODO: calculate attendance
        return model;
    }
}
