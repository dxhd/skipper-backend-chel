package com.tinkoff.skipper.dto;

import com.tinkoff.skipper.entity.UserEntity;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
public class NotificationDto {
    private String notificationText;
    private Boolean isChecked;
    private Boolean isNewMentorRequest;
    private Boolean isReservationRequest;
    private Long toUserId;
}
