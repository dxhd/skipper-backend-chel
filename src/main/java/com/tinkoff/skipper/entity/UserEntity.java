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
                columnNames = { "username", "email", "phoneNumber" }))
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
   private Double timeZone;
   private String speciality; //нужна ли специальность, ведь есть интересы?

   //добавить сущность "SubjectTag" и сделать связь @OneToMany
   private String interests;

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

}
