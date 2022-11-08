package com.tinkoff.skipper.model;

import com.tinkoff.skipper.entity.MentorInfoEntity;
import com.tinkoff.skipper.entity.UserEntity;
import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Data
public class User {

    private Long id;
    private String username;
    private String description;
    private String email;
    private String phoneNumber;
    private BigDecimal balance;
    private Boolean isActive;
    private Double timeZone;
    @Temporal(TemporalType.DATE)
    private Date birthdate;
    private UserEntity.Role role;
    private Set<MentorInfoEntity> mentorInfo;

    public static User toModel(UserEntity entity) {
        User model = new User();
        model.setId(entity.getId());
        model.setUsername(entity.getUsername());
        model.setDescription(entity.getDescription());
        model.setEmail(entity.getEmail());
        model.setPhoneNumber(entity.getPhoneNumber());
        model.setBalance(entity.getBalance());
        model.setIsActive(entity.getIsActive());
        model.setTimeZone(entity.getTimeZone());
        model.setBirthdate(entity.getBirthdate());
        model.setRole(entity.getRole());
        model.setMentorInfo(entity.getMentorInfoEntity());
        return model;
    }

}
