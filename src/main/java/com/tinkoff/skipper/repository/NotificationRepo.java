package com.tinkoff.skipper.repository;

import com.tinkoff.skipper.entity.NotificationEntity;
import com.tinkoff.skipper.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NotificationRepo extends JpaRepository<NotificationEntity, Long> {
    Page<NotificationEntity> findByToUserAndAndIsChecked(UserEntity userTo, boolean isChecked, Pageable pageable);


}