package com.tinkoff.skipper.entity;

import lombok.Data;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "users",
        uniqueConstraints = @UniqueConstraint(
                columnNames = { "username", "email", "phoneNumber" }))
public class UserEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Long id;
   @NotNull
   private String password;
   @NotNull
   private String username;
   private String description;
   private String userPicture;
   @NotNull
   private String email;
   @NotNull
   private String phoneNumber;
   private BigDecimal balance;
   private Boolean isActive = true;
   private Double timeZone;

   //добавить сущность "SubjectTag" и сделать связь @OneToMany
   private String interests;

   @Temporal(TemporalType.DATE)
   private Date birthdate;

   @ManyToMany(fetch = FetchType.EAGER)
   @JoinTable(name = "user_roles",
               joinColumns = @JoinColumn(name = "user_id"),
               inverseJoinColumns = @JoinColumn(name = "role_id"))
   private Set<RoleEntity> roles = new HashSet<>();

   @OneToMany(mappedBy = "menteeId")
   private Set<LessonEntity> lessons;

   @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   private Set<MentorInfoEntity> mentorInfo;

   @CreationTimestamp
   private Date createdAt;
}