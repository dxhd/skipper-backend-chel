package com.tinkoff.skipper.repository;

import com.tinkoff.skipper.DTO.UserMenteeStatsDTO;
import com.tinkoff.skipper.entity.LessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LessonRepo extends JpaRepository<LessonEntity, Long> {


    @Query(value = "select \n" +
            "sum(case when mentee_id = :userId then 1 else 0 end) as allLessons, \n" +
            "sum(case when mentee_id = :userId and status = 'CANCELLED' then 1 else 0 end) as cancelledLessons, \n" +
            "sum(case when mentee_id = :userId and date_of_lesson > current_date - INTERVAL '1' MONTH then 1 else 0 end) as allLessonsPastMonth, \n" +
            "sum(case when mentee_id = :userId and date_of_lesson > current_date - INTERVAL '1' MONTH and status = 'CANCELLED' then 1 else 0 end) as cancelledLessonsPastMonth, \n" +
            "sum(case when mentee_id = :userId and date_of_lesson > current_date - INTERVAL '3' MONTH then 1 else 0 end) as allLessonsPast3Month, \n" +
            "sum(case when mentee_id = :userId and date_of_lesson > current_date - INTERVAL '3' MONTH and status = 'CANCELLED' then 1 else 0 end) as cancelledLessonsPast3Month \n" +
            "from lessons",
            nativeQuery = true)
    Optional<UserMenteeStatsDTO> countAllLessons(@Param("userId") Long menteeId);

}

