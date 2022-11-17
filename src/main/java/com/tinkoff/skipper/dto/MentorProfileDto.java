package com.tinkoff.skipper.dto;

import lombok.Data;

import com.tinkoff.skipper.entity.MentorInfoEntity;
import java.time.OffsetDateTime;

@Data
public class MentorProfileDto {
    private String username;
    private String userPicture;
    private String description;
    private Integer studentNumber;
    private StatsDto stats;
    private String reviews;
    private String lessonTemplates;
    private OffsetDateTime timeLine;

    // FIXME: merge model and dto
    public static MentorProfileDto toModel(MentorInfoEntity entity, StatsDto stats)
    {
        MentorProfileDto model = new MentorProfileDto();
        model.setUsername(entity.getUsername());
        // model.setUserPicture(entity.getUserPicture());
        model.setDescription(entity.getDescription());
        model.setStudentNumber(entity.getStudentNumber());
        model.setStats(stats);
        // TODO: calculate attendance
        //
        return model;
    }

    public static MentorProfileDto toModel(MentorInfoEntity entity)
    {
        MentorProfileDto model = new MentorProfileDto();
        model.setUsername(entity.getUsername());
        // model.setUserPicture(entity.getUserPicture());
        model.setDescription(entity.getDescription());
        model.setStudentNumber(entity.getStudentNumber());
        // TODO: calculate attendance
        return model;
    }
}
