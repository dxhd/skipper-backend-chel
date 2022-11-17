package com.tinkoff.skipper.service;

import com.tinkoff.skipper.DTO.UserMenteeStatsDTO;
import com.tinkoff.skipper.entity.UserEntity;
import com.tinkoff.skipper.model.UserMenteeProfile;
import com.tinkoff.skipper.repository.LessonRepo;
import com.tinkoff.skipper.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserMenteeService {

    private final LessonRepo lessonRepo;
    private final UserRepo userRepo;

    public UserMenteeProfile getMenteeUserInfo(Long id) throws Exception {

        UserEntity user = userRepo.findById(id).get();
        log.info("Got user info from Users by id: {}", id);
        UserMenteeStatsDTO userStats = lessonRepo.countAllLessons(id).get();
        log.info("Got mentee stats from Lessons by user_id: {}", id);

        if (user == null || userStats == null) {
            throw new Exception("Пользователь не найден");
        }

        return UserMenteeProfile.toModel(user, userStats);
    }


}
