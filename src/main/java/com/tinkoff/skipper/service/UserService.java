package com.tinkoff.skipper.service;

import com.tinkoff.skipper.dto.MentorProfileDto;
import com.tinkoff.skipper.dto.UserMenteeProfileDto;
import com.tinkoff.skipper.entity.MentorInfoEntity;
import com.tinkoff.skipper.entity.UserEntity;
import com.tinkoff.skipper.exception.SkipperBadRequestException;
import com.tinkoff.skipper.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public UserEntity findById(Long id) {
        return userRepo.findById(id).orElseThrow(
                () -> new SkipperBadRequestException("Пользователь не найден. Проверьте данные запроса."));
    }

    public UserEntity registerNewUser(UserEntity newUser) {
        if (StringUtils.isEmpty(newUser.getUsername())) {
            newUser.setUsername(newUser.getPhoneNumber());
        }
        if (userRepo.findByUsername(newUser.getUsername()).isPresent()) {
            throw new SkipperBadRequestException("Username already exists");
        }

        return userRepo.save(newUser);
        //TODO: add custom exception "PhoneNumber already exists"
        //TODO: add custom exception "Email already exists"
    }

    public UserEntity updateUserInfo(Long id, UserEntity updatedUserInfo) {
        UserEntity userInfoInDB = userRepo.findById(id)
                .orElseThrow(() -> new SkipperBadRequestException("No such user")
        );
        //проверка на существование других пользователей с таким же юзернеймом, как нововведенный
        //FIXME: проверка не должна срабатывать, если юзернейм null
        if (userRepo.findByUsername(updatedUserInfo.getUsername()).isPresent()) {
            throw new SkipperBadRequestException("Username already exists");
        }
        //FIXME: пропадает значение столбца created_at при обновлении данных
        BeanUtils.copyProperties(updatedUserInfo, userInfoInDB, "id");
        return userRepo.save(userInfoInDB);
    }

    public void deleteUser(UserEntity user) {
        userRepo.delete(user);
    }


}
