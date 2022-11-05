package com.tinkoff.skipper.controller;


import com.tinkoff.skipper.entity.MenteeInfoEntity;
import com.tinkoff.skipper.entity.UserEntity;
import com.tinkoff.skipper.service.MenteeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "users/{userId}/mentee_info", produces = "application/json")
public class MenteeController {

    private final MenteeService menteeService;

    @Autowired
    public MenteeController(MenteeService menteeService) {
        this.menteeService = menteeService;
    }

    @GetMapping("{id}")
    public ResponseEntity getMenteeInfo(@PathVariable Long userId,
                                        @PathVariable Long id) {
        try {
            return ResponseEntity.ok(menteeService.getMenteeInfo(userId, id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Менти с таким id не найдено");
        } //catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Пользователя с таким id не найдено");
//        }
    }

    @PostMapping("new")
    public ResponseEntity addMenteeInfo(
            @RequestBody MenteeInfoEntity newMenteeInfo,
            @PathVariable("userId") UserEntity user) throws Exception {
        menteeService.addMenteeInfo(newMenteeInfo, user);
        return ResponseEntity.status(HttpStatus.CREATED).body("Информация менти успешно добавлена");
    }
}
