package com.tinkoff.skipper.service;

import com.tinkoff.skipper.dto.MentorSettingsDto;
import com.tinkoff.skipper.dto.MentorProfileDto;
import com.tinkoff.skipper.entity.LessonEntity;
import com.tinkoff.skipper.entity.MentorInfoEntity;
import com.tinkoff.skipper.exception.SkipperBadRequestException;
import com.tinkoff.skipper.repository.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Slf4j
public class MentorService {

    private final MentorRepo mentorRepo;
    private final UserRepo userRepo;
    private final CategoryRepo categoryRepo;
    private final LessonRepo lessonRepo;
    private final TagRepo tagRepo;
    private final MentorRatingService mentorRatingService;

    public MentorProfileDto findById(Long id) {
        Long mentorId = mentorRepo.findById(id).orElseThrow(
                () -> new SkipperBadRequestException("Пользователь не найден. Проверьте данные запроса.")).getId();

        try {
            lessonRepo.updateStatus(); //TODO: посмотреть, зачем здесь занятия
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return MentorProfileDto.toModel(
                userRepo.findById(id).orElseThrow(
                        () -> new SkipperBadRequestException("Пользователь не найден. Проверьте данные запроса.")),
                mentorRepo.findById(id).orElseThrow(
                        () -> new SkipperBadRequestException("Пользователь не найден. Проверьте данные запроса.")),
                lessonRepo.countAllMentorLessons(mentorId).orElseThrow(
                        () -> new SkipperBadRequestException("Невозможно подсчитать количество занятий")),
                mentorRatingService.calculateMentorRating(id));
    }

    public void save(MentorSettingsDto mentor)  {
        mentorRepo.save(createMentorInfo(mentor));
    }

    public void update(Long id, MentorSettingsDto mentorSettingsDto, Long userId) {
        if (isEditingAllowed(id, userId)) {
            MentorInfoEntity mentorInfoEntity = mentorRepo.findById(id).orElseThrow(
                    () -> new SkipperBadRequestException("Ментора с заданным id не существует."));
            BeanUtils.copyProperties(mentorSettingsDto, mentorInfoEntity, "userId", "category", "tags");
            mentorInfoEntity.setCategory(categoryRepo.findByName(mentorSettingsDto.getCategory()).orElseThrow(
                    () -> new SkipperBadRequestException("Такой категории не существует.")));
            mentorInfoEntity.clearTags();
            for (String tag : mentorSettingsDto.getTags()) {
                mentorInfoEntity.addTag(tagRepo.findByName(tag).orElseThrow(
                        () -> new SkipperBadRequestException("Тега " + tag + " не существует.")));
            }
            mentorRepo.save(mentorInfoEntity);
        } else {
            throw new SkipperBadRequestException("Создание или редактирование запрещено.");
        }
    }

    public void delete(Long id, Long userId) {
        if (isEditingAllowed(id, userId)) {
            mentorRepo.deleteById(id);
        } else {
            throw new SkipperBadRequestException("Создание или редактирование запрещено.");
        }
    }

    public Page<LessonEntity> getLessonHistory(Long id, Pageable pageable) {
        return lessonRepo.findAllByMentorId(id, pageable);
    }

    private MentorInfoEntity createMentorInfo(MentorSettingsDto mentorSettingsDto) {
        if (mentorRepo.findByUserId(mentorSettingsDto.getUserId()).isPresent()) {
            throw new SkipperBadRequestException("Этот пользователь уже является ментором");
        }
        MentorInfoEntity mentorInfoEntity = new MentorInfoEntity();
        BeanUtils.copyProperties(mentorSettingsDto, mentorInfoEntity, "userId", "category", "tags");

        mentorInfoEntity.setUser(userRepo.findById(mentorSettingsDto.getUserId()).orElseThrow(
                () -> new SkipperBadRequestException("Невалидный пользователь.")));

        mentorInfoEntity.setCategory(categoryRepo.findByName(mentorSettingsDto.getCategory())
                .orElseThrow(
                        () -> new SkipperBadRequestException("Такой категории не существует.")
                ));

        for (String tag : mentorSettingsDto.getTags()) {
            mentorInfoEntity.addTag(tagRepo.findByName(tag)
                    .orElseThrow(
                            () -> new SkipperBadRequestException("Тега " + tag + " не существует.")));
        }

        //TODO: сделать добавление шаблонов занятий

        return mentorInfoEntity;
    }

    private Boolean isEditingAllowed(Long mentorId, Long userId) {
        MentorInfoEntity mentor = mentorRepo.findByUserId(userId).orElseThrow(
                () -> new SkipperBadRequestException("Создание или редактирование запрещено.")
        );
        if (mentor.getId() == mentorId) {
            return true;
        }
        else {
            return false;
        }
    }
} 
