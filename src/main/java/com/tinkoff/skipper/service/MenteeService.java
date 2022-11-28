package com.tinkoff.skipper.service;

import com.tinkoff.skipper.dto.MenteeProfileDto;
import com.tinkoff.skipper.exception.SkipperBadRequestException;
import com.tinkoff.skipper.repository.LessonRepo;
import com.tinkoff.skipper.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenteeService {

    private final LessonRepo lessonRepo;
    private final UserRepo userRepo;

    public MenteeProfileDto getMenteeUserInfoById(Long id) {
        return MenteeProfileDto.toModel(
                userRepo.findById(id).orElseThrow(
                    () -> new SkipperBadRequestException(" Пользователь не найден")
                ),
                lessonRepo.countAllLessons(id).orElseThrow(
                    () -> new SkipperBadRequestException("Невозможно подсчитать количество занятий"))
        );
    }
}
