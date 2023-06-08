package com.tinkoff.skipper.controller;

import com.tinkoff.skipper.dto.NotificationDto;
import com.tinkoff.skipper.dto.UserProfileDto;
import com.tinkoff.skipper.dto.UserSettingsDto;
import com.tinkoff.skipper.dto.authDto.RegisterRequest;
import com.tinkoff.skipper.entity.NotificationEntity;
import com.tinkoff.skipper.service.NotificationService;
import com.tinkoff.skipper.service.UserService;
import com.tinkoff.skipper.utils.SkipperResponseBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "api/notifications", produces = "application/json")
@RequiredArgsConstructor
@CrossOrigin(origins={ "http://localhost:8080", "http://localhost:3000" })
public class NotificationController {

    private final NotificationService notificationService;
    @GetMapping
    @PageableAsQueryParam
    public Page<NotificationEntity> getNotifications(Authentication auth, Pageable pageable) {
        return notificationService.getById((Long) auth.getDetails(), false, pageable);
    }
    @GetMapping("all")
    @PageableAsQueryParam
    public Page<NotificationEntity> getAllNotifications(Authentication auth, Pageable pageable) {
        return notificationService.getById((Long) auth.getDetails(), true, pageable);
    }

    @PostMapping("send")
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<String> sendNotification(Authentication auth, @RequestBody NotificationDto notification) {
        notificationService.send((Long) auth.getDetails(), notification);
        return SkipperResponseBuilder.buildResponse(
                HttpStatus.CREATED,
                "Уведомление отправлено."
        );
    }
}
