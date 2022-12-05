package com.tinkoff.skipper.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "categories")
@Data
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
