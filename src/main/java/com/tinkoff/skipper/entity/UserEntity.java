package com.tinkoff.skipper.entity;

import lombok.Data;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "users",
        uniqueConstraints = @UniqueConstraint(
                columnNames = { "email", "phoneNumber" }))
public class UserEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Long id;
   @NotNull
   private String phoneNumber;
   @NotNull
   private String password;
   private String username;
   private String description;
   private String userPicture;
   private String email;
   private BigDecimal balance;
   private Boolean isActive = true;
   private BigDecimal timeZone;
//   private String speciality;

   @ManyToMany(fetch = FetchType.LAZY)
   @JoinTable(name = "user_interests",
   joinColumns = @JoinColumn (name = "user_id"),
   inverseJoinColumns = {
           @JoinColumn(name = "tag_id", referencedColumnName = "id"),
           @JoinColumn(name = "tag_name", referencedColumnName = "name")
   })
   private Set<TagEntity> interests;

   @Temporal(TemporalType.DATE)
   private Date birthdate;

   @Enumerated(EnumType.STRING)
   @ManyToMany(fetch = FetchType.EAGER)
   @JoinTable(name = "user_roles",
           joinColumns = @JoinColumn (name = "user_id"),
           inverseJoinColumns = @JoinColumn (name = "role"),
           uniqueConstraints = @UniqueConstraint( columnNames = { "user_id", "role" }))
   private Set<RoleEntity> roles = new HashSet<>();

   @OneToMany(mappedBy = "menteeId")
   private Set<LessonEntity> lessons;

   @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   private MentorInfoEntity mentorInfo;

   @CreationTimestamp
   private LocalDate createdAt;

   public void addRole(RoleEntity role) {
      roles.add(role);
   }

   public void removeRole(RoleEntity role) {
      roles.remove(role);
   }

   public void addInterest(TagEntity tag) {
      interests.add(tag);
   }

   public void removeInterest(TagEntity tag) {
      interests.remove(tag);
   }

}
