package com.tinkoff.skipper.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "mentee_stats")
public class MenteeStatsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private Integer finishedLessons;
    private Integer attendance;
    private Integer cancelledLessons;
    private Integer finishedLessonsPastMonth;
    private Integer attendancePastMonth;
    private Integer cancelledLessonsPastMonth;
    private Integer finishedLessonsPast3Month;
    private Integer attendancePast3Month;
    private Integer cancelledLessonsPast3Month;
}
