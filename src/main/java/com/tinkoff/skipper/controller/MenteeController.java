package com.tinkoff.skipper.controller;


import com.tinkoff.skipper.entity.MenteeInfoEntity;
import com.tinkoff.skipper.entity.UserEntity;
import com.tinkoff.skipper.repository.MenteeRepo;
import com.tinkoff.skipper.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "users/{userId}/mentee_info", produces = "application/json")
public class MenteeController {

    private final UserRepo userRepo;
    private final MenteeRepo menteeRepo;

    @Autowired
    public MenteeController(UserRepo userRepo, MenteeRepo menteeRepo) {
        this.userRepo = userRepo; this.menteeRepo = menteeRepo;
    }

    @GetMapping("{id}")
    public MenteeInfoEntity getMenteeInfo(
            @PathVariable("id") MenteeInfoEntity menteeInfo) {
        return menteeInfo;
    }

    @PostMapping("new")
    @ResponseStatus(HttpStatus.CREATED)
    public MenteeInfoEntity addMenteeInfo(
            @RequestBody MenteeInfoEntity newMenteeInfo,
            @PathVariable("id")UserEntity user) {
        newMenteeInfo.setUser(user);
        return menteeRepo.save(newMenteeInfo);
    }
}
