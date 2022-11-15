package com.tinkoff.skipper.model;

import lombok.Data;

import com.tinkoff.skipper.entity.MentorInfoEntity;
import java.time.OffsetDateTime;
import com.tinkoff.skipper.DTO.StatsDTO;

@Data
public class MentorProfile {
  private String username;
  private String userPicture;
  private String description;
  private Integer studentNumber;
  private StatsDTO stats;
  private String reviews;
  private String lessonTemplates;
  private OffsetDateTime timeLine;

  public static MentorProfile toModel(MentorInfoEntity entity, StatsDTO stats)
  {
    MentorProfile model = new MentorProfile();
    model.setUsername(entity.getUsername());
    model.setUserPicture(entity.getUserPicture());
    model.setDescription(entity.getDescription());
    model.setStudentNumber(entity.getStudentNumber());
    model.setStats(stats);
    // TODO: calculate attendance
    // 
    return model; 
  }
}
