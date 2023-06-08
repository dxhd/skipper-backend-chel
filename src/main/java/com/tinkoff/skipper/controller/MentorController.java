package com.tinkoff.skipper.controller;

import com.tinkoff.skipper.dto.MentorSettingsDto;
import com.tinkoff.skipper.dto.MentorProfileDto;
import com.tinkoff.skipper.dto.NotificationDto;
import com.tinkoff.skipper.entity.LessonEntity;
import com.tinkoff.skipper.service.MentorRatingService;
import com.tinkoff.skipper.service.MentorService;

import com.tinkoff.skipper.service.NotificationService;
import com.tinkoff.skipper.service.UserService;
import com.tinkoff.skipper.utils.SkipperResponseBuilder;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RestController
@RequestMapping(path = "api/mentor", produces = "application/json")
@RequiredArgsConstructor
@CrossOrigin(origins={ "http://localhost:8080", "http://localhost:3000" })
public class MentorController {

    private final MentorService mentorService;
    private final NotificationService notificationService;
    private final UserService userService;
    private final MentorRatingService mentorRatingService;

    @GetMapping(path = "{id}")
    public ResponseEntity<MentorProfileDto> getMentor(
            @PathVariable("id") Long id) {
        return SkipperResponseBuilder.buildResponse(
                HttpStatus.OK,
                mentorService.findById(id)
        );
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<String> createMentor(Authentication auth,
            @RequestBody MentorSettingsDto newMentor) {
        mentorService.save(newMentor);
//        notificationService.sendCreationMentorRequest((Long)auth.getDetails(), newMentor);
        return SkipperResponseBuilder.buildResponse(
                HttpStatus.CREATED,
                "Запрос на менторство отправлен админу."
        );
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping(path = "{id}")
    public ResponseEntity<String> updateMentorProfile(
            @RequestBody MentorSettingsDto updatedMentor,
            @PathVariable("id") Long id, Authentication auth) {
        mentorService.update(id, updatedMentor, (Long)auth.getDetails());
        return SkipperResponseBuilder.buildResponse(
                HttpStatus.OK,
                "Информация о менторе изменена."
        );
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping(path = "{id}")
    public ResponseEntity<String> deleteMentor(
            @PathVariable("id") Long id, Authentication auth) {
        mentorService.delete(id, (Long)auth.getDetails());
        return SkipperResponseBuilder.buildResponse(
                HttpStatus.NO_CONTENT,
                "Информация о менторе успешно удалена."
        );
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(path = "{id}")
    public ResponseEntity<String> addToFavourites(
            @PathVariable("id") Long id, Authentication auth) {
        userService.addFavourite(id, (Long)auth.getDetails());
        return SkipperResponseBuilder.buildResponse(
                HttpStatus.OK,
                "Ментор добавлен в избранное."
        );
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(path = "{id}/rate")
    public ResponseEntity<String> rate(
            @PathVariable("id") Long id,
            @RequestBody BigDecimal rate, Authentication auth) {
        mentorRatingService.updateMentorRating(id, rate, (Long)auth.getDetails());
        return SkipperResponseBuilder.buildResponse(
                HttpStatus.OK,
                "Оценка поставлена."
        );
    }

    @GetMapping(path = "{id}/lessonHistory")
    @PageableAsQueryParam
    public Page<LessonEntity> getLessonHistory(
            @PathVariable("id") Long id, Pageable pageable) {
        return mentorService.getLessonHistory(id, pageable);
    }

}
