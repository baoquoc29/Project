package com.example.demo.service.notificationservice;

import com.example.demo.dto.NotificationsDTO;
import com.example.demo.entity.Notification;

import java.util.List;
import java.util.Optional;

public interface NotificationService {
    NotificationsDTO saveNotification(NotificationsDTO notification);
    List<NotificationsDTO> findByIdOrType(long id, String type);
    NotificationsDTO deleteNotification(int id);

}
