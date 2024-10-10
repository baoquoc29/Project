package com.example.demo.repository;

import com.example.demo.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationsRepository extends JpaRepository<Notification,Integer> {
    List<Notification> findByUserIdOrTypeNotification(long id, String type);
}
