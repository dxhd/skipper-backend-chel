package com.tinkoff.skipper.service;

import com.tinkoff.skipper.dto.UserMenteeProfileDto;
import com.tinkoff.skipper.entity.UserEntity;
import com.tinkoff.skipper.exception.SkipperBadRequestException;
import com.tinkoff.skipper.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public UserMenteeProfileDto findById(Long id) {
        return UserMenteeProfileDto.toModel(userRepo.findById(id).orElseThrow(
                () -> new SkipperBadRequestException("No such user")
        ), null);
    }

    public UserEntity registerNewUser(UserEntity newUser) {
        Optional<UserEntity> user = userRepo.findByUsername(newUser.getUsername());
        if (user.isPresent()) {
            throw new SkipperBadRequestException("Another user with this username already exist");
        }
        return userRepo.save(newUser);
    }

    public UserEntity updateUserInfo(UserEntity userInfoInDB, UserEntity updatedUserInfo) {

        Optional<UserEntity> user = userRepo.findByUsername(updatedUserInfo.getUsername());
        //проверка на существование других пользователей с таким же юзернеймом, как нововведенный
        if (user.isPresent() && (user.get()).getId() != userInfoInDB.getId()) {
            throw new SkipperBadRequestException("Another user with this username already exist");
        }
        BeanUtils.copyProperties(updatedUserInfo, userInfoInDB, "id");
        return userRepo.save(userInfoInDB);
    }

    public void deleteUser(UserEntity user) {
        userRepo.delete(user);
    }

}
