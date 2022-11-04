package com.tinkoff.skipper.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Data
@Table (name = "mentor_info")
public class MentorInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private String subject;
    private BigDecimal price;
    private String description;
    private String education;
    private String workExperience;
    private Integer rating;
    private Integer attendance;
    private Integer studentNumber;

    @Column(name = "number_of_lessons")
    private Integer lessonNumber;
    @Column(name = "number_of_cancelled_lessons")
    private Integer cancelledLessonsNumber;

    @ManyToMany(mappedBy = "mentorInfoEntities", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<LessonEntity> lessons;

}
