package com.tinkoff.skipper.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
public class MentorInfoEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
//    @OneToMany
//    private Set<String> subjects;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "description")
    private String description;

    @Column(name = "education")
    private String education;

    @Column(name = "work_experience")
    private String workExperience;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "attendance")
    private Integer attendance;

    @Column(name = "number_of_students")
    private Integer studentNumber;

    @Column(name = "number_of_lessons")
    private Integer lessonNumber;

    @Column(name = "number_of_cancelled_lessons")
    private Integer cancelledLessonsNumber;
}
