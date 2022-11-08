package com.tinkoff.skipper.service;

import com.tinkoff.skipper.DTO.UserMenteeStatsDTO;
import com.tinkoff.skipper.model.UserMenteeStats;
import com.tinkoff.skipper.repository.LessonRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMenteeService {

    private final LessonRepo lessonRepo;

    public UserMenteeStats getMenteeUserInfo(Long id) throws Exception {


        //UserMenteeStatsDTO userStats = lessonRepo.countAllLessons(id).get();
        UserMenteeStatsDTO userStats = null;

        if (userStats == null) {
            throw new Exception("Пользователь не найден");
        }

        return UserMenteeStats.toModel(userStats);
    }


}
