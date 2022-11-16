package com.tinkoff.skipper.service;

import java.util.Optional;

import com.tinkoff.skipper.dto.MentorDto;
import com.tinkoff.skipper.entity.MentorInfoEntity;
import com.tinkoff.skipper.entity.UserEntity;
import com.tinkoff.skipper.exception.SkipperNotFoundException;
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

    public MentorProfile findById(Long id) {
      Optional<MentorInfoEntity> mentorEntity = mentorRepo.findById(id);
      return MentorProfile.toModel(mentorEntity.orElseThrow(
            () -> new SkipperNotFoundException("Пользователь не найден. Проверьте данные запроса.")));
    }

    public void save(MentorDto mentor) throws Exception {
        mentorRepo.save(createMentorInfo(mentor));
    }
    private MentorInfoEntity createMentorInfo(MentorDto mentor) throws Exception{
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
