package com.tinkoff.skipper.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
public class MentorInfoEntity {
    @Id
    @GeneratedValue
    private Long id;
    private Long userId;
//    @OneToMany
//    private Set<String> subjects;
    private BigDecimal price;
    private String description;
    private String education;
    private String workExperience;
    private Integer rating;
    private Integer attendance;
    private Integer studentNumber;
    private Integer lessonNumber;
    private Integer cancelledLessonsNumber;
}
