package com.tinkoff.skipper.controller;

import com.tinkoff.skipper.dto.MentorDto;
import com.tinkoff.skipper.dto.MentorDataDto;
import com.tinkoff.skipper.dto.SkipperResponseBody;
import com.tinkoff.skipper.service.MentorService;

import com.tinkoff.skipper.utils.SkipperResponseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "api/mentor", produces = "application/json")
@RequiredArgsConstructor
public class MentorController {

    private final MentorService mentorService;

    @GetMapping(path = "{id}")
    public ResponseEntity<SkipperResponseBody<?>> getMentor(
            @PathVariable("id") Long id) {
        return SkipperResponseBuilder.buildResponse(HttpStatus.OK, mentorService.findById(id));
    }

    //TODO: убрать message "User has been created" в отдельную мапу
    @PostMapping
    public ResponseEntity<SkipperResponseBody<?>> createMentor(
            @RequestBody MentorDto newMentor) {
        mentorService.save(newMentor);
        return SkipperResponseBuilder.buildResponse(HttpStatus.CREATED, "Mentor Info has been created");
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<SkipperResponseBody<?>> updateMentorProfile(
            @RequestBody MentorDataDto data,
            @PathVariable("id") Long id) {
        mentorService.update(id, data);
        return SkipperResponseBuilder.buildResponse(HttpStatus.OK, "Mentor Info has been updated");
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<SkipperResponseBody<?>> deleteMentor(
            @PathVariable("id") Long id) {
        mentorService.delete(id);
        return SkipperResponseBuilder.buildResponse(HttpStatus.NO_CONTENT, "Mentor Info has been deleted successfully");
    }

}
