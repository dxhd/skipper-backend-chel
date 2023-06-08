package com.tinkoff.skipper.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "categories")
@Data
public class CategoryEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @JsonIgnore
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private Set<TagEntity> tags;

}
