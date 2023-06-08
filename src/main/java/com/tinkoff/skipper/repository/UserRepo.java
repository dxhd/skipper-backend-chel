package com.tinkoff.skipper.repository;

import com.tinkoff.skipper.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByPhoneNumber(String phoneNumber);
    Optional<UserEntity> findByEmail(String email);

    @Query(value = "select u.* from users u join user_roles ur on ur.role = :role where u.id = ur.user_id limit 1", nativeQuery = true)
    Optional<UserEntity> findByRole(String role);

}
