package com.tinkoff.skipper.controller;


import com.tinkoff.skipper.dto.UserMenteeProfileDto;
import com.tinkoff.skipper.service.UserMenteeService;
import com.tinkoff.skipper.utils.SkipperResponseBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "api/users", produces = "application/json")
@RequiredArgsConstructor
public class UserMenteeInfoController {

    private final UserMenteeService userMenteeService;

    @GetMapping("{id}/mentee_profile")
    public ResponseEntity<UserMenteeProfileDto> getMenteeUserInfo(@PathVariable Long id) {
        return SkipperResponseBuilder.buildResponse(
                HttpStatus.OK,
                userMenteeService.getMenteeUserInfoById(id)
        );
    }

}
