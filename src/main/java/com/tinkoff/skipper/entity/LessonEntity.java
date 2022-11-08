package com.tinkoff.skipper.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;

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
    private MentorInfoEntity mentorId;

    //связь занятий с менти (UserEntity)
    @ManyToOne
    @JoinColumn(name = "mentee_id")
    private UserEntity menteeId;


    private OffsetDateTime schedule;

    @Column(name = "date_of_lesson")
    private LocalDate lessonDate;

    public enum Status {
        CANCELLED,
        FINISHED,
        PLANNED,
        IN_PROGRESS
    }
    @Enumerated(EnumType.STRING)
    private Status status;

}
