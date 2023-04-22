package com.niit.NotificationService.service;

import com.niit.NotificationService.config.TrackDTO;
import com.niit.NotificationService.domain.Notification;

public interface INotificationService {
    public Notification getAllNotification(String email);
    public void saveNotification(TrackDTO trackDTO);
}
