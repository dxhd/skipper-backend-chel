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


    //связь занятий с менторами
    @ManyToOne
    @JoinColumn(name = "mentor_id")
    private MentorInfoEntity mentorInfo;

    //связь занятий с менти (UserEntity)
    @ManyToOne
    @JoinColumn(name = "mentee_id")
    private UserEntity menteeInfo;

    // сделать время занятия
    //private Date schedule;

    @Column(name = "date_of_lesson")
    private Date lessonDate;

}
