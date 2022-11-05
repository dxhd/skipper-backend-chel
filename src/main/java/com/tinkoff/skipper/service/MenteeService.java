package com.tinkoff.skipper.service;

import com.tinkoff.skipper.entity.MenteeInfoEntity;
import com.tinkoff.skipper.entity.UserEntity;
import com.tinkoff.skipper.model.UserAsMentee;
import com.tinkoff.skipper.repository.MenteeRepo;
import com.tinkoff.skipper.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

@Service
public class MenteeService {

    private final UserService userService;
    private final MenteeRepo menteeRepo;

    @Autowired
    public MenteeService(UserService userService, MenteeRepo menteeRepo) {
        this.userService = userService; this.menteeRepo = menteeRepo;
    }

    public UserAsMentee getMenteeInfo(Long userId, Long menteeId) throws Exception //must throw 2 different exception
    {
        Set<MenteeInfoEntity> set = userService.getOneUser(userId).getMenteeInfo();
        Stream<MenteeInfoEntity> str = set.stream();
        str = str.filter(menteeInfoEntity -> menteeInfoEntity.getId() == menteeId);
        Optional<MenteeInfoEntity> opt = str.findFirst();
        MenteeInfoEntity menteeInfo = opt.get();


//        MenteeInfoEntity menteeInfo = userService.getOneUser(userId).getMenteeInfo().stream()
//                    .filter(menteeInfoEntity -> menteeInfoEntity.getId() == menteeId)
//                    .findFirst().get();



            if (menteeInfo == null) {
                throw new Exception("Менти с заданным id не найден"); //add exception class
            }
            return UserAsMentee.toModel(menteeInfo);
    }

    public MenteeInfoEntity addMenteeInfo(MenteeInfoEntity newMenteeInfo, UserEntity user) {
        newMenteeInfo.setUser(user);
        return menteeRepo.save(newMenteeInfo);
    }




}
