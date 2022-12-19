package com.tinkoff.skipper.service;

import com.tinkoff.skipper.dto.MenteeProfileDto;
import com.tinkoff.skipper.exception.SkipperBadRequestException;
import com.tinkoff.skipper.repository.LessonRepo;
import com.tinkoff.skipper.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MenteeService {

    private final LessonRepo lessonRepo;
    private final UserRepo userRepo;

    public MenteeProfileDto getMenteeUserInfoById(Long id) {
        try {
            lessonRepo.updateStatus();
        } catch (Exception e) { log.error(e.getMessage());}
        return MenteeProfileDto.toModel(
                userRepo.findById(id).orElseThrow(
                    () -> new SkipperBadRequestException(" Пользователь не найден")
                ),
                lessonRepo.countAllMenteeLessons(id).orElseThrow(
                    () -> new SkipperBadRequestException("Невозможно подсчитать количество занятий"))
        );
    }
}
