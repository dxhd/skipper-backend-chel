package com.tinkoff.skipper.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    private String lessonType;
    private Integer lessonLength;
    private BigDecimal lessonCost;

    @Column(name = "date_and_time_of_lesson")
    private LocalDateTime lessonDateTime;

    public enum Status {
        CANCELLED,
        FINISHED,
        PLANNED,
        IN_PROGRESS
    }
    @Enumerated(EnumType.STRING)
    private Status status;

}
