package com.tinkoff.skipper.service;

import com.tinkoff.skipper.dto.UserProfileDto;
import com.tinkoff.skipper.dto.UserSettingsDto;
import com.tinkoff.skipper.dto.authDto.RegisterRequest;
import com.tinkoff.skipper.entity.MentorInfoEntity;
import com.tinkoff.skipper.entity.RoleEntity;
import com.tinkoff.skipper.entity.TagEntity;
import com.tinkoff.skipper.entity.UserEntity;
import com.tinkoff.skipper.exception.SkipperBadRequestException;
import com.tinkoff.skipper.exception.SkipperNotFoundException;
import com.tinkoff.skipper.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final MentorRepo mentorRepo;

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final TagRepo tagRepo;
    private final LessonRepo lessonRepo;

    public UserEntity getById(Long id) {
        return userRepo.findById(id).orElseThrow(
                () -> new SkipperBadRequestException("Пользователь не найден. Проверьте данные запроса."));
    }

    public UserEntity getByUsername(String username) {
        return userRepo.findByUsername(username).orElseThrow(
                () -> new SkipperBadRequestException("Пользователь не найден. Проверьте данные запроса."));
    }

    public UserEntity getByPhoneNumber(String phoneNumber) {
        return userRepo.findByPhoneNumber(phoneNumber).orElseThrow(
                () -> new SkipperBadRequestException("Пользователь не найден. Проверьте данные запроса."));
    }

    public UserEntity getByEmail(String email) {
        return userRepo.findByEmail(email).orElseThrow(
                () -> new SkipperBadRequestException("Пользователь не найден. Проверьте данные запроса."));
    }

    public UserProfileDto getProfileInfoById(Long id) {
        try {
            lessonRepo.updateStatus();
        } catch (Exception e) { log.error(e.getMessage());}
        return UserProfileDto.toModel(
                userRepo.findById(id).orElseThrow(
                        () -> new SkipperBadRequestException(" Пользователь не найден")
                ),
                lessonRepo.countAllMenteeLessons(id).orElseThrow(
                        () -> new SkipperBadRequestException("Невозможно подсчитать количество занятий"))
        );
    }

    public UserEntity registerNewUser(RegisterRequest newUser) {

        if (userRepo.findByPhoneNumber(newUser.getPhoneNumber()).isPresent()) {
            throw new SkipperBadRequestException("Пользователь с таким номером телефона уже существует.");
        }
        UserEntity user = new UserEntity();
        user.setUsername(newUser.getPhoneNumber());
        user.setPhoneNumber(newUser.getPhoneNumber());
        user.setPassword(newUser.getPassword());
        RoleEntity roleEntity = roleRepo.findById(RoleEntity.Role.USER).orElseThrow(
                () -> new SkipperNotFoundException("Такой роли не существует.")
        );
        user.addRole(roleEntity);
        return userRepo.save(user);
    }

    //TODO: протестировать метод полностью
    public UserEntity updateUserInfo(Long id, UserSettingsDto updatedUserInfo) {
        UserEntity userInfoInDB = userRepo.findById(id)
                .orElseThrow(() -> new SkipperNotFoundException("Такого пользователя не существует")
        );

        BeanUtils.copyProperties(updatedUserInfo, userInfoInDB,
                "id", "createdAt", "phoneNumber", "email", "password", "roles", "isActive", "interests");
        userInfoInDB.clearInterests();
        for (String interest: updatedUserInfo.getInterests()) {
            TagEntity tag = tagRepo.findByName(interest)
                    .orElseThrow(() -> new SkipperNotFoundException("Тега " + interest + " не существует."));
            userInfoInDB.addInterest(tag);
        }
        log.info("Updated user info: " + userInfoInDB.toString());
        return userRepo.save(userInfoInDB);
    }

    public void deleteUser(UserEntity user) {
        userRepo.delete(user);
    }


    public void addFavourite(Long userId, Long mentorId) {
        UserEntity user = userRepo.findById(userId).orElseThrow(
                () -> new SkipperBadRequestException("Такого пользователя не существует.")
        );
        MentorInfoEntity mentor = mentorRepo.findById(mentorId).orElseThrow(
                () -> new SkipperBadRequestException("Такого ментора не существует.")
        );
        user.addFavourite(mentor);
        userRepo.save(user);
    }

    public void deleteFavourite(Long userId, Long mentorId) {
        UserEntity user = userRepo.findById(userId).orElseThrow(
                () -> new SkipperBadRequestException("Такого пользователя не существует.")
        );
        MentorInfoEntity mentor = mentorRepo.findById(mentorId).orElseThrow(
                () -> new SkipperBadRequestException("Такого ментора не существует.")
        );
        user.deleteFavourite(mentor);
        userRepo.save(user);
    }

    public void clearFavourites(Long userId) {
        UserEntity user = userRepo.findById(userId).orElseThrow(
                () -> new SkipperBadRequestException("Такого пользователя не существует.")
        );
        user.clearFavourites();
        userRepo.save(user);
    }
}
