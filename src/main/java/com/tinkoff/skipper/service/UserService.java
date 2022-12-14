package com.tinkoff.skipper.service;

import com.tinkoff.skipper.dto.authDto.RegisterRequest;
import com.tinkoff.skipper.entity.RoleEntity;
import com.tinkoff.skipper.entity.UserEntity;
import com.tinkoff.skipper.exception.SkipperBadRequestException;
import com.tinkoff.skipper.exception.SkipperNotFoundException;
import com.tinkoff.skipper.repository.RoleRepo;
import com.tinkoff.skipper.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

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
    public UserEntity updateUserInfo(Long id, UserEntity updatedUserInfo) {
        UserEntity userInfoInDB = userRepo.findById(id)
                .orElseThrow(() -> new SkipperNotFoundException("Такого пользователя не существует")
        );

        BeanUtils.copyProperties(updatedUserInfo, userInfoInDB,
                "id", "createdAt", "phoneNumber", "email", "password", "roles", "isActive");
        log.info("Updated user info: " + userInfoInDB.toString());
        return userRepo.save(userInfoInDB);
    }

    public void deleteUser(UserEntity user) {
        userRepo.delete(user);
    }


}
