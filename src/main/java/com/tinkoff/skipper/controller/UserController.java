package com.tinkoff.skipper.controller;

import com.tinkoff.skipper.dto.SkipperResponse;
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

    @GetMapping("{id}")
    public ResponseEntity<SkipperResponse> getAllUserInfo(@PathVariable Long id) {
        return SkipperResponse.buildResponse(HttpStatus.OK, userService.findById(id));
    }

    @PostMapping("register")
    public ResponseEntity<SkipperResponse> registerNewUser(@RequestBody UserEntity newUser) {
            userService.registerNewUser(newUser);
            return SkipperResponse.buildResponse(HttpStatus.CREATED, "User has been created");
    }

    @PutMapping("{id}/settings")
    public ResponseEntity<SkipperResponse> updateUserInfo(
            @PathVariable("id")Long id,
            @RequestBody UserEntity updatedInfo) {
        userService.updateUserInfo(id, updatedInfo);
        return SkipperResponse.buildResponse(HttpStatus.OK, "User Info has been updated");
    }

    @DeleteMapping("{id}/settings")
    public ResponseEntity<SkipperResponse> deleteUser(@PathVariable("id") UserEntity user) {
        userService.deleteUser(user);
        return SkipperResponse.buildResponse(HttpStatus.NO_CONTENT, "User has been deleted successfully");
    }


}
