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
    @ManyToOne
    @JoinColumn(name = "mentor_id")
    private MentorInfoEntity mentorId;
    @ManyToOne
    @JoinColumn(name = "mentee_id")
    private UserEntity menteeId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_tepmlate", referencedColumnName = "id")
    private LessonTemplateEntity lessonTemplate;
    @Column(name = "date_and_time_of_lesson")
    private LocalDateTime lessonDateTime;
    @Enumerated(EnumType.STRING)
    private Status status;
    public enum Status {
        CANCELLED,
        FINISHED,
        PLANNED,
        IN_PROGRESS
    }
}
