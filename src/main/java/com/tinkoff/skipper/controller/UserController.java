package com.tinkoff.skipper.controller;

import com.tinkoff.skipper.dto.SkipperResponseBody;
import com.tinkoff.skipper.entity.UserEntity;
import com.tinkoff.skipper.service.UserService;
import com.tinkoff.skipper.utils.SkipperResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/users", produces = "application/json")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("{id}")
    public ResponseEntity<SkipperResponseBody<?>> getAllUserInfo(@PathVariable Long id) {
        return SkipperResponseBuilder.buildResponse(HttpStatus.OK, userService.findById(id));
    }

    //TODO: Передавать параметром дто-шку
    @PostMapping("register")
    public ResponseEntity<SkipperResponseBody<?>> registerNewUser(@RequestBody UserEntity newUser) {
            userService.registerNewUser(newUser);
            return SkipperResponseBuilder.buildResponse(HttpStatus.CREATED, "User has been created");
    }

    //TODO: Передавать параметром дто-шку
    @PutMapping("{id}/settings")
    public ResponseEntity<SkipperResponseBody<?>> updateUserInfo(
            @PathVariable("id")Long id,
            @RequestBody UserEntity updatedInfo) {
        userService.updateUserInfo(id, updatedInfo);
        return SkipperResponseBuilder.buildResponse(HttpStatus.OK, "User Info has been updated");
    }

    @DeleteMapping("{id}/settings")
    public ResponseEntity<SkipperResponseBody<?>> deleteUser(@PathVariable("id") UserEntity user) {
        userService.deleteUser(user);
        return SkipperResponseBuilder.buildResponse(HttpStatus.NO_CONTENT, "User has been deleted successfully");
    }


}
