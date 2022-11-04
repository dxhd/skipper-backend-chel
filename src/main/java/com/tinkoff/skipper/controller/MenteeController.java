package com.tinkoff.skipper.controller;


import com.tinkoff.skipper.entity.MenteeInfoEntity;
import com.tinkoff.skipper.entity.UserEntity;
import com.tinkoff.skipper.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "users/{userId}/mentee_info", produces = "application/json")
public class MenteeController {

    private final UserRepo userRepo;

    @Autowired
    public MenteeController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("{id}")
    public MenteeInfoEntity getMenteeInfo(
            @PathVariable("userId") UserEntity user,
            @PathVariable("id") MenteeInfoEntity menteeInfo) {
        return menteeInfo;
    }

}
