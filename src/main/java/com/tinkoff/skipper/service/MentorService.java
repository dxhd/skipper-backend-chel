package com.tinkoff.skipper.service;

import com.tinkoff.skipper.dto.MentorDataDto;
import com.tinkoff.skipper.dto.MentorDto;
import com.tinkoff.skipper.dto.MentorProfileDto;
import com.tinkoff.skipper.entity.MentorInfoEntity;
import com.tinkoff.skipper.exception.SkipperBadRequestException;
import com.tinkoff.skipper.repository.*;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MentorService {

    private final MentorRepo mentorRepo;
    private final UserRepo userRepo;
    private final CategoryRepo categoryRepo;
    private final LessonRepo lessonRepo;
    private final TagRepo tagRepo;

    public MentorProfileDto findById(Long id) {
        lessonRepo.updateStatus();
        return MentorProfileDto.toModel(
              userRepo.findById(id).orElseThrow(
                () -> new SkipperBadRequestException("Пользователь не найден. Проверьте данные запроса.")),
              mentorRepo.findById(id).orElseThrow(
                () -> new SkipperBadRequestException("Пользователь не найден. Проверьте данные запроса.")),
              lessonRepo.countAllMentorLessons(id).orElseThrow(
                () -> new SkipperBadRequestException("Невозможно подсчитать количество занятий")
              ));
    }

    public void save(MentorDto mentor)  {
        mentorRepo.save(createMentorInfo(mentor));
    }

    public void update(Long id, MentorDataDto data) {
        MentorInfoEntity mentorInfoEntity = mentorRepo.findById(id).orElseThrow(
                () -> new SkipperBadRequestException("No such mentor")
        );
        BeanUtils.copyProperties(data, mentorInfoEntity);
        mentorRepo.save(mentorInfoEntity);
    }

    public void delete(Long id) {
        mentorRepo.deleteById(id);
    }

    private MentorInfoEntity createMentorInfo(MentorDto mentorDto) {
        if (mentorRepo.findByUserId(mentorDto.getUserId()).isPresent()) {
            throw new SkipperBadRequestException("Этот пользователь уже является ментором");
        }

        MentorInfoEntity mentorInfoEntity = new MentorInfoEntity();
        BeanUtils.copyProperties(mentorDto, mentorInfoEntity,
                "userId", "category", "tags");

        //TODO: query student number
        //TODO: вынести все проверки и конвертацию из дто в энтити в отдельный класс
        mentorInfoEntity.setUser(userRepo.findById(mentorDto.getUserId()).orElseThrow(
                () -> new SkipperBadRequestException("Невалидный пользователь.")));

        mentorInfoEntity.setCategory(categoryRepo.findByName(mentorDto.getCategory())
                .orElseThrow(
                        () -> new SkipperBadRequestException("Такой категории не существует.")
                ));

        for (String tag :
                mentorDto.getTags()) {
            mentorInfoEntity.addTag(tagRepo.findByName(tag)
                    .orElseThrow(
                            () -> new SkipperBadRequestException("Тега " + tag + " не существует.")));
        }

        return mentorInfoEntity;
    }
} 
