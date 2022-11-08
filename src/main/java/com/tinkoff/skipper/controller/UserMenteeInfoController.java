package com.tinkoff.skipper.controller;


import com.tinkoff.skipper.service.UserMenteeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "users", produces = "application/json")
@RequiredArgsConstructor
public class UserMenteeInfoController {

    private final UserMenteeService userMenteeService;

    @GetMapping("{id}/mentee_info")
    public ResponseEntity getMenteeUserInfo(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userMenteeService.getMenteeUserInfo(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND.ordinal()).body("Такого пользователя не сущестсвует");
        }
    }

}
