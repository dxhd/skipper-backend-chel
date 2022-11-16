package com.tinkoff.skipper.service;

import java.util.Optional;

import com.tinkoff.skipper.DTO.MentorDTO;
import com.tinkoff.skipper.DTO.StatsDTO;
import com.tinkoff.skipper.entity.MentorInfoEntity;
import com.tinkoff.skipper.entity.UserEntity;
import com.tinkoff.skipper.model.MentorProfile;
import com.tinkoff.skipper.repository.MentorRepo;

import com.tinkoff.skipper.repository.UserRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MentorService {

    private final MentorRepo mentorRepo;
    private final UserRepo userRepo;

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


    //FIXME: rename to MentorDto
    public void save(MentorDTO mentor) throws Exception {
        mentorRepo.save(createMentorInfo(mentor));
    }

    private MentorInfoEntity createMentorInfo(MentorDTO mentor) throws Exception{
        MentorInfoEntity mentorInfoEntity = new MentorInfoEntity();
        BeanUtils.copyProperties(mentor, mentorInfoEntity, "userId");
        Optional<UserEntity> user = userRepo.findById(mentor.getUserId());
        if (!user.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No such user");
        }
        // TODO: query student number
        mentorInfoEntity.setUser(user.get());
        return mentorInfoEntity;
    }

} 
