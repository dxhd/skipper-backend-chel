package com.tinkoff.skipper.service;

import com.tinkoff.skipper.dto.RegisterRequest;
import com.tinkoff.skipper.entity.RoleEntity;
import com.tinkoff.skipper.entity.UserEntity;
import com.tinkoff.skipper.exception.SkipperBadRequestException;
import com.tinkoff.skipper.exception.SkipperNotFoundException;
import com.tinkoff.skipper.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public UserEntity getById(Long id) {
        return userRepo.findById(id).orElseThrow(
                () -> new SkipperBadRequestException("Пользователь не найден. Проверьте данные запроса."));
    }

    public UserEntity getByUsername(String username) {
        return userRepo.findByUsername(username).orElseThrow(
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
        return userRepo.save(user);
    }

    //TODO: Каждый пользователь может изменять только свою информацию по айдишнику
    public UserEntity updateUserInfo(Long id, UserEntity updatedUserInfo) {
        UserEntity userInfoInDB = userRepo.findById(id)
                .orElseThrow(() -> new SkipperNotFoundException("Такого пользователя не существует")
        );
        //проверка на существование других пользователей с таким же юзернеймом, как нововведенный
        //FIXME: проверка не должна срабатывать, если юзернейм null
        if (userRepo.findByUsername(updatedUserInfo.getUsername()).isPresent()) {
            throw new SkipperBadRequestException("Такое имя пользователя уже занято.");
        }
        //FIXME: пропадает значение столбца created_at при обновлении данных
        BeanUtils.copyProperties(updatedUserInfo, userInfoInDB, "id");
        return userRepo.save(userInfoInDB);
    }

    public void deleteUser(UserEntity user) {
        userRepo.delete(user);
    }


}
