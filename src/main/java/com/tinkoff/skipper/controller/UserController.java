package com.tinkoff.skipper.controller;

import com.tinkoff.skipper.entity.UserEntity;
import com.tinkoff.skipper.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/users", produces = "application/json")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

//    @GetMapping("{id}")
//    public ResponseEntity getAllUserInfo(@PathVariable Long id) {
//        try {
//            return ResponseEntity.ok(userService.getOneUser(id));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND.ordinal()).body("Такого пользователя не сущестсвует");
//        }
//    }

    @PostMapping("register")
    public ResponseEntity registerNewUser(@RequestBody UserEntity newUser) {
        try {
            userService.registerNewUser(newUser);
            return ResponseEntity.status(HttpStatus.CREATED).body("Пользователь зарегистрирован"); //поменять ответ на ResponseEntity.Created(...)
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка регистрации");
        }
    }

    @PutMapping("{id}/settings")
    public ResponseEntity updateUserInfo(
            @PathVariable("id")UserEntity userInfoInDB,
            @RequestBody UserEntity updatedInfo) {
        try {
            userService.updateUserInfo(userInfoInDB, updatedInfo);
            return ResponseEntity.ok().body("Пользователь успешно сохранён");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Пользователь с таким именем уже существует");
        }
    }

    @DeleteMapping("{id}/settings")
    public ResponseEntity deleteUser(@PathVariable("id") UserEntity user) {
        userService.deleteUser(user);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Пользователь успешно удалён");
    }


}
