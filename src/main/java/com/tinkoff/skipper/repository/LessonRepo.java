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
            "count (*) filter(where mentee_id = :userId) as allLessons,  \n" +
            "count (*) filter(where mentee_id = :userId and date_of_lesson > current_date - interval '1 month') as allLessonsPastMonth,\n" +
            "count (*) filter(where mentee_id = :userId and date_of_lesson > current_date - interval '3 month') as allLessonsPast3Month,\n" +
            "count (*) filter(where mentee_id = :userId and status = 'cancelled') as cancelledLessons,  \n" +
            "count (*) filter(where mentee_id = :userId and date_of_lesson > current_date - interval '1 month' and status = 'cancelled') as cancelledLessonsPastMonth,\n" +
            "count (*) filter(where mentee_id = :userId and date_of_lesson > current_date - interval '3 month' and status = 'cancelled') as cancelledLessonsPast3Month\n" +
            "from lessons",
            nativeQuery = true)
    Optional<UserMenteeStatsDTO> countAllLessons(@Param("userId") Long menteeId);

}

