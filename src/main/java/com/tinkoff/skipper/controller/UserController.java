package com.tinkoff.skipper.controller;

import com.tinkoff.skipper.entity.UserEntity;
import com.tinkoff.skipper.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepo repo;

    @GetMapping
    public List<UserEntity> getUsers() {
        UserEntity u = new UserEntity();
        u.setEmail("something@cool.com");
        u.setBalance(new BigDecimal(100000.123132));
        u.setPhoneNumber("89088234626");
        u.setDescription("Dr. Livesey");
        u.setBirthdate(new Date());
        repo.save(u);
        return List.of(new UserEntity(), new UserEntity());
    }

}
