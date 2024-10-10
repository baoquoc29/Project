package com.example.demo.service.notificationservice;

import com.example.demo.dto.NotificationsDTO;
import com.example.demo.entity.Notification;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.repository.NotificationsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    NotificationsRepository notificationsRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public NotificationsDTO saveNotification(NotificationsDTO notificationsDTO) {
        // Chuyển đổi từ DTO sang entity
        Notification notification = modelMapper.map(notificationsDTO, Notification.class);

        // Lưu notification vào cơ sở dữ liệu
        Notification savedNotification = notificationsRepository.save(notification);

        // Chuyển đổi từ entity đã lưu trở lại DTO
        return modelMapper.map(savedNotification, NotificationsDTO.class);
    }



    @Override
    public List<NotificationsDTO> findByIdOrType(long id, String type) {
        if(!type.equals("personal") && !type.equals("system")) {
            throw new AppException(ErrorCode.INVALID_TYPE);
        }
        List<Notification> notifications = notificationsRepository.findByUserIdOrTypeNotification(id, type);

        if (notifications == null || notifications.isEmpty()) {
            throw new AppException(ErrorCode.NOT_FOUND_TOPIC);
        }

        return notifications.stream()
                .map(notification -> modelMapper.map(notification, NotificationsDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public NotificationsDTO deleteNotification(int id) {
        Notification notification = notificationsRepository.findById(id).orElse(null);
        notificationsRepository.delete(notification);
        return modelMapper.map(notification, NotificationsDTO.class);
    }
}
