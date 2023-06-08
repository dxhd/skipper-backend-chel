package com.tinkoff.skipper.service;

import com.tinkoff.skipper.dto.LessonReservationDto;
import com.tinkoff.skipper.dto.MentorSettingsDto;
import com.tinkoff.skipper.dto.NotificationDto;
import com.tinkoff.skipper.entity.MentorInfoEntity;
import com.tinkoff.skipper.entity.NotificationEntity;
import com.tinkoff.skipper.entity.UserEntity;
import com.tinkoff.skipper.exception.SkipperBadRequestException;
import com.tinkoff.skipper.exception.SkipperNotFoundException;
import com.tinkoff.skipper.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepo notificationRepo;
    private final UserRepo userRepo;
    private final MentorRepo mentorRepo;

    public Page<NotificationEntity> getById(Long toUserId, boolean allowChecked, Pageable pageable) {
        UserEntity toUser = userRepo.findById(toUserId).orElseThrow(
                () -> new SkipperBadRequestException("Пользователь не найден.")
        );
        Page<NotificationEntity> notifications = notificationRepo.findByToUserAndAndIsChecked(toUser, allowChecked, pageable);
        for (NotificationEntity n: notifications) {
            n.setIsChecked(true);
            notificationRepo.save(n);
        }
        return notifications;
    }

    public void send(Long fromUserId, NotificationDto notification) {
        NotificationEntity notificationEntity = new NotificationEntity();
        notificationEntity.setNotificationText(notification.getNotificationText());
        notificationEntity.setFromUser(userRepo.getReferenceById(fromUserId));
        notificationEntity.setToUser(userRepo.getReferenceById(notification.getToUserId()));
        notificationRepo.save(notificationEntity);
    }

    public void send(Long fromUserId, Long toUserId, String text) {
        NotificationEntity notificationEntity = new NotificationEntity();
        notificationEntity.setFromUser(userRepo.getReferenceById(fromUserId));
        notificationEntity.setToUser(userRepo.getReferenceById(toUserId));
        notificationEntity.setNotificationText(text);
        notificationRepo.save(notificationEntity);
    }

    public void sendCreationMentorRequest(Long fromUserId, MentorSettingsDto newMentor) {
        NotificationEntity notificationEntity = new NotificationEntity();
        notificationEntity.setToUser(userRepo.findByRole("ADMIN").orElseThrow(() -> new SkipperNotFoundException("А админов нет")));
        notificationEntity.setFromUser(userRepo.getReferenceById(fromUserId)); //TODO: проверить отличие метода getReferenceById
        notificationEntity.setNotificationText("Запрос на менторство. -> " + newMentor.toString());
        notificationEntity.setIsNewMentorRequest(true);
        notificationRepo.save(notificationEntity);
    }

    public void sendReservationRequest(Long fromUserId, LessonReservationDto lessonReservationDto) {
        NotificationEntity notificationEntity = new NotificationEntity();
        MentorInfoEntity toMentor = mentorRepo.findById(lessonReservationDto.getMentorId()).orElseThrow(
                () -> new SkipperBadRequestException("Ментор не найден.")
        );
        notificationEntity.setToUser(toMentor.getUser());
        notificationEntity.setFromUser(userRepo.getReferenceById(fromUserId));
        notificationEntity.setNotificationText("Запрос на бронирование. -> " + lessonReservationDto.toString());
        notificationEntity.setIsReservationRequest(true);
        notificationRepo.save(notificationEntity);
    }

    public void sendAcceptReservation(Long fromUserId, LessonReservationDto lessonReservationDto) {
        send(fromUserId, lessonReservationDto.getMenteeId(), lessonReservationDto.toString());
    }
}
