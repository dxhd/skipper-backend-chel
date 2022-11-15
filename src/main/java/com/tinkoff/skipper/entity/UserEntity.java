package com.tinkoff.skipper.entity;

import lombok.Data;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
public class UserEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Long id;

   private String fullname;
   private String speciality;
   private String password;
   private String username;
   private Double rating;
   private String description;
   private String email;
   private String phoneNumber;
   private BigDecimal balance;
   private Boolean isActive;
   private Double timeZone;
   private String userPicture;

   public enum NetStatus {
     ONLINE,
     OFFLINE
   }
   @Enumerated(EnumType.STRING)
   private NetStatus netStatus = NetStatus.OFFLINE;

   @Temporal(TemporalType.DATE)
   private Date birthdate;

   public enum Role {
       ADMIN,
       MODERATOR,
       USER
   }
   @Enumerated(EnumType.STRING)
   @Column(name = "user_role")
   private Role role = Role.USER;


   @OneToMany(mappedBy = "menteeId")
   private Set<LessonEntity> lessons;

   // @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   // private MentorInfoEntity mentorInfo;

   @CreationTimestamp
   private Date createdAt;
}
