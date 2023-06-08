package com.tinkoff.skipper.entity;

import lombok.Data;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.ZoneId;
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
   private String userPictureURL;
   private String email;
   private Boolean isActive = true;
   private ZoneId timeZone;
   @Temporal(TemporalType.DATE)
   private Date birthdate;
   @CreationTimestamp
   private LocalDate createdAt;

   @ManyToMany(fetch = FetchType.LAZY)
   @JoinTable(name = "user_interests",
   joinColumns = @JoinColumn (name = "user_id"),
   inverseJoinColumns = {
           @JoinColumn(name = "tag_id", referencedColumnName = "id")
   })
   private Set<TagEntity> interests;

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

   @ManyToMany(fetch = FetchType.LAZY)
   @JoinTable(name = "user_favourites",
           joinColumns = @JoinColumn (name = "user_id"),
           inverseJoinColumns = {
                   @JoinColumn(name = "mentor_id", referencedColumnName = "id")
           })
   private Set<MentorInfoEntity> favouriteMentors;


   public void addFavourite(MentorInfoEntity mentor) {
      favouriteMentors.add(mentor);
   }
   public void deleteFavourite(MentorInfoEntity mentor) {
      favouriteMentors.remove(mentor);
   }
   public void clearFavourites() {
      favouriteMentors.clear();
   }

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
   public void clearInterests() {
      interests.clear();
   }

}
