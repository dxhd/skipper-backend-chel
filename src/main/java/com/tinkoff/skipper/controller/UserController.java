package com.tinkoff.skipper.controller;

import com.tinkoff.skipper.dto.authDto.RegisterRequest;
import com.tinkoff.skipper.entity.UserEntity;
import com.tinkoff.skipper.service.UserService;
import com.tinkoff.skipper.utils.SkipperResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/users", produces = "application/json")
@RequiredArgsConstructor
@CrossOrigin(origins={ "http://localhost:8090", "http://localhost:3000" })
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')") //FIXME: исправить проблему в проверкой ролей
    @GetMapping("{id}")
    public ResponseEntity<UserEntity> getAllUserInfo(@PathVariable Long id) {
        return SkipperResponseBuilder.buildResponse(
                HttpStatus.OK,
                userService.getById(id)
        );
    }

    @PostMapping("register")
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<String> registerNewUser(@RequestBody RegisterRequest newUser) {
            userService.registerNewUser(newUser);
            return SkipperResponseBuilder.buildResponse(
                    HttpStatus.CREATED,
                    "Пользователь зарегистрирован."
            );
    }

    //TODO: Передавать параметром дто-шку
    //TODO: Переделать в Patch-запрос
    @PreAuthorize("isAuthenticated() and (authentication.details == #id)")
    @PutMapping("{id}/settings")
    public ResponseEntity<String> updateUserInfo(
            @PathVariable("id")Long id,
            @RequestBody UserEntity updatedInfo) {
        userService.updateUserInfo(id, updatedInfo);
        return SkipperResponseBuilder.buildResponse(
                HttpStatus.OK,
                "Информация пользователя обновлена."
        );
    }


    @PreAuthorize("isAuthenticated() and (authentication.details == #id)")
    @PatchMapping(value = "{id}/settings", produces = "image/jpg")
    public ResponseEntity<String> updateUserImage(
            @PathVariable("id")Long id,
            @RequestBody UserEntity updatedInfo) {
        userService.updateUserInfo(id, updatedInfo);
        return SkipperResponseBuilder.buildResponse(
                HttpStatus.OK,
                "Информация пользователя обновлена."
        );
    }


    @PreAuthorize("isAuthenticated() and (authentication.details == #id)")
    @DeleteMapping("{id}/settings")
    public ResponseEntity<String> deleteUser(@PathVariable("id") UserEntity user) {
        userService.deleteUser(user);
        return SkipperResponseBuilder.buildResponse(
                HttpStatus.NO_CONTENT,
                "Пользователь успешно удален."
        );
    }


}
