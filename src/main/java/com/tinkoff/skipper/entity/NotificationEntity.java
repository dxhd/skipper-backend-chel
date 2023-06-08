package com.tinkoff.skipper.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "notifications")
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String notificationText;
    private Boolean isChecked = false;
    private Boolean isNewMentorRequest = false;
    private Boolean isReservationRequest = false;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_user_id", referencedColumnName = "id")
    private UserEntity fromUser;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_user_id", referencedColumnName = "id")
    private UserEntity toUser;

}
