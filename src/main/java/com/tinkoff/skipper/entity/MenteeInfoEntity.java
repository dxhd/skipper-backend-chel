package com.tinkoff.skipper.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table (name = "mentee_info")
public class MenteeInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    private Integer attendance;
    @Column(name = "number_of_lessons")
    private Integer lessonsNumber;

    @Column(name = "number_of_cancelled_lessons")
    private Integer cancelledLessonsNumber;

    @ManyToMany(mappedBy = "menteeInfoEntities", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<LessonEntity> lessons;
}
