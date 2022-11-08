package com.tinkoff.skipper.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Date;

@Entity
@Data
@Table (name = "lessons")
public class LessonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    //связь занятий с менторами
    @ManyToOne
    @JoinColumn(name = "mentor_id")
    private MentorInfoEntity mentorInfo;

    //связь занятий с менти (UserEntity)
    @ManyToOne
    @JoinColumn(name = "mentee_id")
    private UserEntity menteeInfo;

    // сделать время занятия
    private OffsetDateTime schedule;

    @Column(name = "date_of_lesson")
    private Date lessonDate;

    public enum Status {
        CANCELLED,
        FINISHED,
        PLANNED,
        IN_PROGRESS
    }
    @Enumerated(EnumType.STRING)
    private Status status;

}
