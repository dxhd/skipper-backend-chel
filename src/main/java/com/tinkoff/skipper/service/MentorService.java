package com.tinkoff.skipper.service;

import com.tinkoff.skipper.dto.MentorDataDto;
import com.tinkoff.skipper.dto.MentorDto;
import com.tinkoff.skipper.dto.MentorProfileDto;
import com.tinkoff.skipper.entity.MentorInfoEntity;
import com.tinkoff.skipper.exception.SkipperBadRequestException;
import com.tinkoff.skipper.repository.MentorRepo;

import com.tinkoff.skipper.repository.UserRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MentorService {

    private final MentorRepo mentorRepo;
    private final UserRepo userRepo;

    public MentorProfileDto findById(Long id) {
      return MentorProfileDto.toModel(
          userRepo.findById(id).orElseThrow(
            () -> new SkipperBadRequestException("Пользователь не найден. Проверьте данные запроса.")),
          mentorRepo.findById(id).orElseThrow(
            () -> new SkipperBadRequestException("Пользователь не найден. Проверьте данные запроса.")),
          null);
    }

    public void save(MentorDto mentor)  {
        mentorRepo.save(createMentorInfo(mentor));
    }

    public void update(Long id, MentorDataDto data) {
        MentorInfoEntity mentorEntity = mentorRepo.findById(id).orElseThrow(
                () -> new SkipperBadRequestException("No such mentor")
        );
        BeanUtils.copyProperties(data, mentorEntity);
        mentorRepo.save(mentorEntity);
    }

    public void delete(Long id) {
        mentorRepo.deleteById(id);
    }

    private MentorInfoEntity createMentorInfo(MentorDto mentor) {
        MentorInfoEntity mentorInfoEntity = new MentorInfoEntity();
        BeanUtils.copyProperties(mentor, mentorInfoEntity, "userId");
        // TODO: query student number
        mentorInfoEntity.setUser(userRepo.findById(mentor.getUserId()).orElseThrow(
                () -> new SkipperBadRequestException("Невалидный пользователь.")));
        return mentorInfoEntity;
    }

} 
