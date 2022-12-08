package com.tinkoff.skipper.controller;

import com.tinkoff.skipper.dto.LessonReservationDto;
import com.tinkoff.skipper.service.ReservationService;
import com.tinkoff.skipper.utils.SkipperResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/reserve", produces = "application/json")
@RequiredArgsConstructor
@CrossOrigin(origins={ "http://localhost:8090", "http://localhost:3000" })
public class ReservationController {

    private final ReservationService reservationService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("{mentorId}")
    public ResponseEntity<String> createMentor(
            @PathVariable Long mentorId,
            @RequestBody LessonReservationDto lessonReservation
            ) {
        lessonReservation.setMentorId(mentorId);
        reservationService.reserveLesson(lessonReservation);
        return SkipperResponseBuilder.buildResponse(
                HttpStatus.CREATED,
                "Занятие забронировано."
        );
    }

}
