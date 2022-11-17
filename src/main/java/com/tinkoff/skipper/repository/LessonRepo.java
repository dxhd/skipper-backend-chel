package com.tinkoff.skipper.repository;

import com.tinkoff.skipper.entity.LessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LessonRepo extends JpaRepository<LessonEntity, Long> {

//    @Query("select mentor_id, count(*) as allLessons from lessons where mentee_id = ?1 group by mentor_id")
//    Optional<UserMenteeStatsDTO> countAllLessons(Long menteeId);

}
