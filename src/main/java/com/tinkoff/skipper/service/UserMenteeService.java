package com.tinkoff.skipper.service;

import com.tinkoff.skipper.dto.StatsDto;
import com.tinkoff.skipper.entity.UserEntity;
import com.tinkoff.skipper.dto.UserMenteeProfileDto;
import com.tinkoff.skipper.repository.LessonRepo;
import com.tinkoff.skipper.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMenteeService {

    private final LessonRepo lessonRepo;
    private final UserRepo userRepo;

    public UserMenteeProfileDto getMenteeUserInfo(Long id) throws Exception {

        UserEntity user = userRepo.findById(id).get();
        //UserMenteeStatsDTO userStats = lessonRepo.countAllLessons(id).get();
        StatsDto userStats = null;

        if (user == null || userStats == null) {
            throw new Exception("Пользователь не найден");
        }
        return UserMenteeProfileDto.toModel(user, userStats);
    }
}
