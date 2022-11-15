package com.tinkoff.skipper.controller;

import com.tinkoff.skipper.DTO.MentorDTO;
import com.tinkoff.skipper.model.MentorProfile;
import com.tinkoff.skipper.service.MentorService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "mentor", produces = "application/json")
@RequiredArgsConstructor
public class MentorController {

  private final MentorService mentorService;

  @GetMapping(path = "{id}")
  public MentorProfile getMentorProfile(@PathVariable("id") Long id) {
    try {
      return new MentorProfile();
    }
    catch (Exception e) {
      return new MentorProfile();
    }
  }

  @PostMapping
  public MentorProfile createMentor(@RequestBody MentorDTO newMentor) {
    mentorService.save(newMentor);
    System.out.println(newMentor);
    return new MentorProfile();
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
