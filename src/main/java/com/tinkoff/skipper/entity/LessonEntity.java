package com.tinkoff.skipper.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Data
public class LessonEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_id")
    private Set<MentorInfoEntity> mentorInfoEntities;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "mentee_id")
    private Set<MenteeInfoEntity> menteeInfoEntities;

    // interval
    @Column(name = "schedule")
    private Date schedule;
}
