package com.tinkoff.skipper.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


public interface UserMenteeStatsDTO {

    Integer getAllLessons();
    Integer getCancelledLessons();

    Integer getAllLessonsPastMonth();
     Integer getCancelledLessonsPastMonth();

    Integer getAllLessonsPast3Month();
    Integer getCancelledLessonsPast3Month();

}
