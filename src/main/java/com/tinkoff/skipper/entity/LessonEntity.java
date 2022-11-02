package com.tinkoff.skipper.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class LessonEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "mentor_id", unique = true)
    private Long mentorId;
    @Column(name = "mentee_id", unique = true)
    private Long menteeId;
    // interval
    @Column(name = "schedule")
    private Date schedule;
}
