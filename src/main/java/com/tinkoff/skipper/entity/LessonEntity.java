package com.tinkoff.skipper.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class LessonEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private Long mentorId;
    @Column(unique = true)
    private Long menteeId;
    // interval
    private Date schedule;
}
