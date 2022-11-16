package com.tinkoff.skipper.controller;

import com.tinkoff.skipper.dto.MentorDto;
import com.tinkoff.skipper.dto.SkipperResponse;
import com.tinkoff.skipper.model.MentorProfile;
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
    public ResponseEntity<String> createMentor(@RequestBody MentorDto newMentor) {
      try {
        mentorService.save(newMentor);
        return new ResponseEntity<>("Hello, World!", HttpStatus.OK);
      }
      catch (Exception e) {
        return new ResponseEntity<>("Hello, World!", HttpStatus.I_AM_A_TEAPOT);
      }
    }

    @PutMapping(path = "{id}")
    public MentorProfile updateMentorProfile() {
      return new MentorProfile();
    }

    @DeleteMapping(path = "{id}")
    public MentorProfile deleteMentor() {
      return new MentorProfile();
    }

}
