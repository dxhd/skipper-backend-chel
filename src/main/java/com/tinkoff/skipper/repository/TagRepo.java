package com.tinkoff.skipper.repository;

import com.tinkoff.skipper.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepo extends JpaRepository<TagEntity, Long> {
    Optional<TagEntity> findByName(String tagName);
}
