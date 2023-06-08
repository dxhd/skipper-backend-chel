package com.tinkoff.skipper.controller;

import com.tinkoff.skipper.dto.LessonReservationDto;
import com.tinkoff.skipper.service.NotificationService;
import com.tinkoff.skipper.service.ReservationService;
import com.tinkoff.skipper.utils.SkipperResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/reserve", produces = "application/json")
@RequiredArgsConstructor
@CrossOrigin(origins={ "http://localhost:8080", "http://localhost:3000" })
public class ReservationController {

    private final NotificationService notificationService;
    private final ReservationService reservationService;
    @PreAuthorize("isAuthenticated()")
    @PostMapping("{mentorId}")
    public ResponseEntity<String> sendReservationRequest(
            @PathVariable Long mentorId,
            @RequestBody LessonReservationDto lessonReservation, Authentication auth
            ) {
        lessonReservation.setMentorId(mentorId);
        notificationService.sendReservationRequest((Long) auth.getDetails(), lessonReservation);
        return SkipperResponseBuilder.buildResponse(
                HttpStatus.CREATED,
                "Запрос на бронирование отправлен."
        );
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping()
    public ResponseEntity<String> acceptReservation(
            @RequestBody LessonReservationDto lessonReservation, Authentication auth
            ) {
        notificationService.sendAcceptReservation((Long) auth.getDetails(), lessonReservation);
        reservationService.reserveLesson(lessonReservation);
        return SkipperResponseBuilder.buildResponse(
                HttpStatus.CREATED,
                "Занятие забронировано."
        );
    }
}
