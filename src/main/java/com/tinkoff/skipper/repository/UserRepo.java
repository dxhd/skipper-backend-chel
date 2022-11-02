package com.tinkoff.skipper.repository;

import com.tinkoff.skipper.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepo extends CrudRepository<UserEntity, Long> {
}
