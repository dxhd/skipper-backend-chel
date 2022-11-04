package com.tinkoff.skipper.entity;

import lombok.Data;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity
@Data
public class UserEntity {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Long id;

   @Column(name = "password")
   private String password;

   @Column(name = "age")
   private Integer age;

   @Column(name = "description")
   private String description;

   @Column(name = "email")
   private String email;

   @Column(name = "phone_number")
   private String phoneNumber;

   @Column(name = "balance")
   private BigDecimal balance;

   @Temporal(TemporalType.DATE)
   @Column(name = "birthdate")
   private Date birthdate;

   public enum Role {
       ADMIN,
       MODERATOR,
       USER
   }
   @Enumerated(EnumType.STRING)
   @Column(name = "user_role")
   private Role role = Role.USER;

   @Column(name = "is_active")
   private Boolean isActive;

   @Column(name = "time_zone")
   private Double timeZone;

   @CreationTimestamp
   @Column(name = "created_at")
   private Date createdAt;

   @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   @Column(name = "mentee_info")
   private Set<MenteeInfoEntity> menteeInfoEntity;

   @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   @Column(name = "mentor_info")
   private Set<MentorInfoEntity> mentorInfoEntity;
}
