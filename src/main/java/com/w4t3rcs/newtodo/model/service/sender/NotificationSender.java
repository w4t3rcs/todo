package com.w4t3rcs.newtodo.model.service.sender;

import com.w4t3rcs.newtodo.model.common.Getter;
import com.w4t3rcs.newtodo.model.common.MessageSender;
import com.w4t3rcs.newtodo.model.common.ServiceSender;
import com.w4t3rcs.newtodo.model.data.dao.NotificationRepository;
import com.w4t3rcs.newtodo.model.entity.authentication.User;
import com.w4t3rcs.newtodo.model.entity.message.Notification;
import com.w4t3rcs.newtodo.model.properties.MessageProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationSender implements MessageSender {
    private final ApplicationContext applicationContext;
    private final NotificationRepository notificationRepository;
    private final MessageProperties messageProperties;
    private final Getter<User> currentUserGetter;

    @Autowired
    public NotificationSender(ApplicationContext applicationContext, NotificationRepository notificationRepository, MessageProperties messageProperties, Getter<User> currentUserGetter) {
        this.applicationContext = applicationContext;
        this.notificationRepository = notificationRepository;
        this.messageProperties = messageProperties;
        this.currentUserGetter = currentUserGetter;
    }

    @Override
    @Scheduled(cron = "0 0 15 ? * SUN")
    public void send() {
        User currentUser = currentUserGetter.get();
        notificationRepository.findByTo(currentUser).ifPresentOrElse(this::send, () -> log.warn("{} hasn't any notification settings", currentUser));
    }

    public void send(Notification notification) {
        if (notification.getDeadline().isEnded() && !notification.isEnabled()) return;

        String subject = messageProperties.getNotificationSubject();
        String message = notification.getMessage(messageProperties);
        ServiceSender serviceSender = switch (notification.getMethod()) {
            case EMAIL -> applicationContext.getBean("emailSender", ServiceSender.class);
        };
        serviceSender.send(null, currentUserGetter.get(), subject, message);
    }
}
