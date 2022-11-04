package com.tinkoff.skipper.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@Table (name = "lessons")
public class LessonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "mentor_lesson",
        joinColumns = @JoinColumn(name = "mentor_id"),
        inverseJoinColumns = @JoinColumn(name = "lesson_id"))
    private Set<MentorInfoEntity> mentorInfoEntities;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)

    @JoinTable(name = "mentee_lesson",
            joinColumns = @JoinColumn(name = "mentee_id"),
            inverseJoinColumns = @JoinColumn(name = "lesson_id"))
    private Set<MenteeInfoEntity> menteeInfoEntities;

    // interval
    private Date schedule;

}
