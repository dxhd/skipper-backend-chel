package com.tinkoff.skipper.controller;

import com.tinkoff.skipper.entity.UserEntity;
import com.tinkoff.skipper.repository.UserRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "users", produces = "application/json")
public class UserController {

    private final UserRepo userRepo;

    @Autowired
    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("{id}")
    public UserEntity getAllUserInfo(@PathVariable("id") UserEntity userInfo) {
        return userInfo;
    }

    @PostMapping("register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserEntity registerNewUser(@RequestBody UserEntity newUser) {
        return userRepo.save(newUser);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserEntity updateUserInfo(
            @PathVariable("id")UserEntity userInfoInDB,
            @RequestBody UserEntity updatedInfo) {
        BeanUtils.copyProperties(updatedInfo, userInfoInDB, "id");
        return userRepo.save(userInfoInDB);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") UserEntity user) {
        userRepo.delete(user);
    }


}
