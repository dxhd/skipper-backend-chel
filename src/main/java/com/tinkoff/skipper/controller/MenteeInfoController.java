package com.tinkoff.skipper.controller;


import com.tinkoff.skipper.dto.MenteeProfileDto;
import com.tinkoff.skipper.service.MenteeService;
import com.tinkoff.skipper.utils.SkipperResponseBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "api/users", produces = "application/json")
@RequiredArgsConstructor
@CrossOrigin(origins={ "http://localhost:8090", "http://localhost:3000" })
public class MenteeInfoController {

    private final MenteeService menteeService;

    @GetMapping("{id}/mentee_profile")
    public ResponseEntity<MenteeProfileDto> getMenteeUserInfo(@PathVariable Long id) {
        return SkipperResponseBuilder.buildResponse(
                HttpStatus.OK,
                menteeService.getMenteeUserInfoById(id)
        );
    }

}
