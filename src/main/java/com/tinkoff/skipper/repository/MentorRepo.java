package com.tinkoff.skipper.repository;

import java.util.Optional;

import com.tinkoff.skipper.entity.MentorInfoEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentorRepo extends JpaRepository<MentorInfoEntity, Long> {
}
