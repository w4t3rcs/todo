package com.w4t3rcs.newtodo.model.service.sender.service;

import com.w4t3rcs.newtodo.model.common.ServiceSender;
import com.w4t3rcs.newtodo.model.data.dao.NotificationHolderRepository;
import com.w4t3rcs.newtodo.model.entity.holder.NotificationHolder;
import com.w4t3rcs.newtodo.model.entity.message.Notification;
import com.w4t3rcs.newtodo.model.entity.message.TextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationHolderSender implements ServiceSender {
    private final NotificationHolderRepository notificationHolderRepository;

    @Autowired
    public NotificationHolderSender(NotificationHolderRepository notificationHolderRepository) {
        this.notificationHolderRepository = notificationHolderRepository;
    }

    @Override
    public void send(TextMessage textMessage) {
        Notification message = (Notification) textMessage;
        NotificationHolder holder = NotificationHolder.builder()
                .notification(message)
                .sentDate(LocalDateTime.now())
                .build();
        notificationHolderRepository.save(holder);
    }
}
