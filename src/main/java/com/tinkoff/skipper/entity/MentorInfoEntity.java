package com.tinkoff.skipper.entity;

import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "speciality", referencedColumnName = "name")
    private CategoryEntity speciality;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "mentor_tags",
            joinColumns = @JoinColumn (name = "mentor_user_id"),
            inverseJoinColumns = {
                @JoinColumn (name = "tag_id", referencedColumnName = "id"),
                @JoinColumn(name = "tag", referencedColumnName = "name")
            })
    private Set<TagEntity> tags = new HashSet<>();

    @Column(name = "number_of_students")
    private Integer studentNumber;

}