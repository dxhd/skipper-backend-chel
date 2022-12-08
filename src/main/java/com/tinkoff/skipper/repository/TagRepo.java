package com.tinkoff.skipper.repository;

import com.tinkoff.skipper.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface TagRepo extends JpaRepository<TagEntity, Long> {
    Optional<TagEntity> findByName(String tagName);

    @Query (value = "select name from tags where category = :category",
        nativeQuery = true)
    Collection<String> findAllByCategory(String category);
}

