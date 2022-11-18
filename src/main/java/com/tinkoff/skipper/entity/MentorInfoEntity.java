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

    @OneToOne(fetch = FetchType.LAZY)
    private UserEntity user;

    private String subjects;
    private String username;
    private BigDecimal price;
    private String description;
    private BigDecimal rating;
    private String workExperience;
    private String certificates;
    private String education;

    @Column(name = "number_of_students")
    private Integer studentNumber;

    //заменить каскад
    /* @OneToMany(mappedBy = "mentorId")//, cascade = CascadeType.ALL, fetch = FetchType.LAZY) */
    /* private Set<LessonEntity> lessons; */
}
