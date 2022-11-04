package com.tinkoff.skipper.repository;

import com.tinkoff.skipper.entity.MenteeInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MenteeRepo extends JpaRepository<MenteeInfoEntity, Long> {
}
