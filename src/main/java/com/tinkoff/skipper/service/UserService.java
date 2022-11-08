package com.tinkoff.skipper.service;

import com.tinkoff.skipper.entity.UserEntity;
import com.tinkoff.skipper.model.User;
import com.tinkoff.skipper.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public User getOneUser(Long id) throws Exception {
        UserEntity user = userRepo.findById(id).get();
        if (user == null) {
            throw new Exception("Пользователь не найден");
        }
        return User.toModel(user);
    }

    public UserEntity registerNewUser(UserEntity newUser) throws Exception {
        Optional<UserEntity> user = userRepo.findByUsername(newUser.getUsername());
        if (user.isPresent()) {
            throw new Exception("Пользователь с таким именем уже существует");
        }
        return userRepo.save(newUser);
    }

    public UserEntity updateUserInfo(UserEntity userInfoInDB, UserEntity updatedUserInfo) throws Exception {

        Optional check = userRepo.findByUsername(updatedUserInfo.getUsername());
        //проверка на существование других пользователей с таким же юзернеймом, как нововведенный
        if (check.isPresent() && ((UserEntity) check.get()).getId() != userInfoInDB.getId()) {
            throw new Exception("User with this username already exists");
        }
        BeanUtils.copyProperties(updatedUserInfo, userInfoInDB, "id");
        return userRepo.save(userInfoInDB);
    }

    public void deleteUser(UserEntity user) {
        userRepo.delete(user);
    }


}
