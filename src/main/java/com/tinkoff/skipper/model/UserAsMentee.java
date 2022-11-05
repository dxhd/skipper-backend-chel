package com.tinkoff.skipper.model;

import com.tinkoff.skipper.entity.LessonEntity;
import com.tinkoff.skipper.entity.MenteeInfoEntity;
import com.tinkoff.skipper.entity.UserEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
public class UserAsMentee {

    private Long id;
    private Integer attendance;
    private Integer lessonsNumber;
    private Integer cancelledLessonsNumber;
    private Set<LessonEntity> lessons;

    public static UserAsMentee toModel(MenteeInfoEntity entity) {
        UserAsMentee model = new UserAsMentee();
        model.setId(entity.getId());
        model.setAttendance(entity.getAttendance());
        model.setLessonsNumber(entity.getLessonsNumber());
        model.setCancelledLessonsNumber(entity.getCancelledLessonsNumber());
        model.setLessons(entity.getLessons());
        return model;
    }


}
