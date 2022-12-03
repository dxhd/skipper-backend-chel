package com.tinkoff.skipper.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "categories")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category")
    private Set<TagEntity> tags;

//    @JsonIgnore
//    @OneToMany(mappedBy = "speciality")
//    @JoinColumn(name = "mentors")
//    private Set<UserEntity> mentors;

}
