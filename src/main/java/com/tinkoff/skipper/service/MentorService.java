package com.tinkoff.skipper.service;

import java.util.Optional;

import com.tinkoff.skipper.DTO.MentorDTO;
import com.tinkoff.skipper.DTO.StatsDTO;
import com.tinkoff.skipper.entity.MentorInfoEntity;
import com.tinkoff.skipper.model.MentorProfile;
import com.tinkoff.skipper.repository.MentorRepo;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class MentorService {

  private final MentorRepo mentorRepo;
  private final ModelMapper modelMapper;

  public MentorProfile findMentorProfileById(Long id) throws Exception {
    Optional<MentorInfoEntity> mentorInfo = mentorRepo.findById(id);
    if (!mentorInfo.isPresent()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No such user");
    }
    StatsDTO stats = new StatsDTO();
    stats.setAllLessons(12);
    stats.setCancelledLessons(13);
    stats.setAllLessonsPastMonth(1);
    return MentorProfile.toModel(mentorInfo.get(), stats);
  }

  public MentorProfile save(MentorDTO mentor) {
    MentorInfoEntity mentorEntity = this.modelMapper.map(mentor, MentorInfoEntity.class);
    mentorRepo.save(mentorEntity);
    return new MentorProfile();
  } 

} 
