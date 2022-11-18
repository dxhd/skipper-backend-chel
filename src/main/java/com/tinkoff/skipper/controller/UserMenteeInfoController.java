package com.tinkoff.skipper.controller;


import com.tinkoff.skipper.dto.SkipperResponse;
import com.tinkoff.skipper.service.UserMenteeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/users", produces = "application/json")
@RequiredArgsConstructor
public class UserMenteeInfoController {

    private final UserMenteeService userMenteeService;

    @GetMapping("{id}/mentee_profile")
    public ResponseEntity<SkipperResponse> getMenteeUserInfo(@PathVariable Long id) {
        return SkipperResponse.buildResponse(HttpStatus.OK, userMenteeService.findById(id));
    }

}
