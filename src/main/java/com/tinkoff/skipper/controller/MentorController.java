package com.tinkoff.skipper.controller;

import com.tinkoff.skipper.dto.MentorDto;
import com.tinkoff.skipper.dto.MentorDataDto;
import com.tinkoff.skipper.dto.SkipperResponse;
import com.tinkoff.skipper.service.MentorService;

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
    public ResponseEntity<SkipperResponse> getMentor(@PathVariable("id") Long id) {
        return SkipperResponse.buildResponse(HttpStatus.OK, mentorService.findById(id));
    }

    @PostMapping
    public ResponseEntity<SkipperResponse> createMentor(@RequestBody MentorDto newMentor) {
        mentorService.save(newMentor);
        return SkipperResponse.buildResponse(HttpStatus.CREATED, "User has been created");
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<SkipperResponse> updateMentorProfile(@RequestBody MentorDataDto data,
                                                               @PathVariable("id") Long id) {
        mentorService.update(id, data);
        return SkipperResponse.buildResponse(HttpStatus.OK, "User has been updated");
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<SkipperResponse> deleteMentor(@PathVariable("id") Long id) {
        mentorService.delete(id);
        return SkipperResponse.buildResponse(HttpStatus.OK, "Mentor has been deleted successfully");
    }

}
