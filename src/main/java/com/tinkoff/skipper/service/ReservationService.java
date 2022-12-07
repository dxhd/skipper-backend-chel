package com.tinkoff.skipper.service;

import com.tinkoff.skipper.dto.LessonReservationDto;
import com.tinkoff.skipper.dto.MentorDto;
import com.tinkoff.skipper.entity.LessonEntity;
import com.tinkoff.skipper.entity.MentorInfoEntity;
import com.tinkoff.skipper.exception.SkipperBadRequestException;
import com.tinkoff.skipper.repository.LessonRepo;
import com.tinkoff.skipper.repository.MentorRepo;
import com.tinkoff.skipper.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final LessonRepo lessonRepo;
    private final MentorRepo mentorRepo;
    private final UserRepo userRepo;

    public LessonEntity reserveLesson(LessonReservationDto lessonReservationDto) {
        return lessonRepo.save(createLessonEntity(lessonReservationDto));
    }

    public LessonEntity createLessonEntity(LessonReservationDto lessonDto) {
        LessonEntity lessonEntity = new LessonEntity();
        lessonEntity.setLessonType(lessonDto.getLessonType());
        lessonEntity.setLessonCost(lessonDto.getLessonCost());
        lessonEntity.setLessonLength(lessonDto.getLessonLength());
        lessonEntity.setLessonDateTime(lessonDto.getLessonDateTime());
        lessonEntity.setMentorId(
                mentorRepo.findById(lessonDto.getMentorId()).orElseThrow(
                        () -> new SkipperBadRequestException("Ментора с таким id не существует."))
        );
        lessonEntity.setMenteeId(
                userRepo.findById(lessonDto.getMentorId()).orElseThrow(
                        () -> new SkipperBadRequestException("Менти с таким id не существует."))
        );
        lessonEntity.setStatus(LessonEntity.Status.PLANNED);
        return lessonEntity;
    }

}
