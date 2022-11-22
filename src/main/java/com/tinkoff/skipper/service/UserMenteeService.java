package com.tinkoff.skipper.service;

import com.tinkoff.skipper.dto.StatsDto;
import com.tinkoff.skipper.entity.UserEntity;
import com.tinkoff.skipper.dto.UserMenteeProfileDto;
import com.tinkoff.skipper.exception.SkipperBadRequestException;
import com.tinkoff.skipper.repository.LessonRepo;
import com.tinkoff.skipper.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMenteeService {

    private final LessonRepo lessonRepo;
    private final UserRepo userRepo;

    public UserMenteeProfileDto getMenteeUserInfoById(Long id) {
        return UserMenteeProfileDto.toModel(
                userRepo.findById(id).orElseThrow(
                    () -> new SkipperBadRequestException(" Пользователь не найден")
                ),
                lessonRepo.countAllLessons(id).orElseThrow(
                    () -> new SkipperBadRequestException("Невозможно подсчитать количество занятий"))
        );
    }
}
