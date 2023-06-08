package com.tinkoff.skipper.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "lesson_templates")
public class LessonTemplateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double lenght;
    private BigDecimal price;
    private String communicationMethod;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_id", referencedColumnName = "id")
    private MentorInfoEntity mentor;

}
