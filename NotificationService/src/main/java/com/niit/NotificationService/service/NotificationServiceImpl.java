package com.niit.NotificationService.service;

import com.niit.NotificationService.config.TrackDTO;
import com.niit.NotificationService.domain.Notification;
import com.niit.NotificationService.repository.NotificationRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements INotificationService{

    NotificationRepository notificationRepository;
@Autowired
    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public Notification getAllNotification(String email) {
       return notificationRepository.findById(email).get();
    }

@RabbitListener(queues = "UserProductQueue")
    @Override
    public void saveNotification(TrackDTO trackDTO) {
Notification notification=new Notification();
String email= trackDTO.getJsonObject().get("email").toString();
if(notificationRepository.findById(email).isEmpty())
{
    notification.setEmail(email);
}
notification.setEmail(email);
notification.setNotificationMassage("List Of Not Played Tracks");
notification.setJsonObject(trackDTO.getJsonObject());
notificationRepository.save(notification);
    }
}
