package com.tinkoff.skipper.Entity;

import lombok.Data;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class UserEntity {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;


   private String password;
   private Integer age;
   private String description;
   private String email;
   private String phoneNumber;
   private BigDecimal balance;

   @Temporal(TemporalType.DATE)
   private Date birthdate;
   public enum Role {
       ADMIN,
       MODERATOR,
       USER
   }
   @Enumerated(EnumType.STRING)
   private Role role = Role.USER;

   private Boolean isActive;
   private Double timeZone;

   @CreationTimestamp
   private Date createdAt;
//   @OneToMany(mappedBy = "menteeId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//   private Set<LessonEntity> lessons;

   @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
   private Set<MenteeInfoEntity> menteeInfoEntity;

   @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
   private Set<MentorInfoEntity> mentorInfoEntity;
}
