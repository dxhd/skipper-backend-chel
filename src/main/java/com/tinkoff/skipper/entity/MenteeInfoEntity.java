package com.tinkoff.skipper.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class MenteeInfoEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private Integer attendance;
    private Integer lessonsNumber;
    private Integer cancelledLessonsNumber;
}
