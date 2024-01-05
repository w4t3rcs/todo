package com.w4t3rcs.newtodo.model.service.sender;

import com.w4t3rcs.newtodo.model.common.MessageSender;
import com.w4t3rcs.newtodo.model.common.ServiceSender;
import com.w4t3rcs.newtodo.model.data.dao.NotificationRepository;
import com.w4t3rcs.newtodo.model.data.dao.UserRepository;
import com.w4t3rcs.newtodo.model.entity.message.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service("notificationSender")
public class NotificationSender implements MessageSender {
    private final ApplicationContext applicationContext;
    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationSender(ApplicationContext applicationContext, UserRepository userRepository, NotificationRepository notificationRepository) {
        this.applicationContext = applicationContext;
        this.userRepository = userRepository;
        this.notificationRepository = notificationRepository;
    }

    @Override
    @Scheduled(cron = "0 0 12 ? * FRI")
    public void send() {
        log.debug("Sending notifications has been started!");
        userRepository.findAll().forEach(user ->
                notificationRepository.findByTo(user).ifPresentOrElse(this::send, () -> log.warn("{} hasn't any notification settings", user))
        );
    }

    private void send(Notification notification) {
        if (notification.getDeadline().isEnded() && !notification.isEnabled()) return;

        ServiceSender serviceSender = getServiceSenderByMethod(notification.getMethod());
        serviceSender.send(notification);
    }

    private ServiceSender getServiceSenderByMethod(Notification.Method method) {
        return switch (method) {
            case EMAIL -> applicationContext.getBean("emailSender", ServiceSender.class);
        };
    }
}
