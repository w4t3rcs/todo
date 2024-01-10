package com.w4t3rcs.newtodo.model.service.sender.message;

import com.w4t3rcs.newtodo.model.common.MessageSender;
import com.w4t3rcs.newtodo.model.common.ServiceSender;
import com.w4t3rcs.newtodo.model.common.SoundPropertiesPlayer;
import com.w4t3rcs.newtodo.model.data.dao.NotificationRepository;
import com.w4t3rcs.newtodo.model.data.dao.UserRepository;
import com.w4t3rcs.newtodo.model.entity.message.Notification;
import com.w4t3rcs.newtodo.model.properties.SoundProperties;
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
    private final SoundPropertiesPlayer player;
    private final SoundProperties soundProperties;

    @Autowired
    public NotificationSender(ApplicationContext applicationContext, UserRepository userRepository, NotificationRepository notificationRepository, SoundPropertiesPlayer player, SoundProperties soundProperties) {
        this.applicationContext = applicationContext;
        this.userRepository = userRepository;
        this.notificationRepository = notificationRepository;
        this.player = player;
        this.soundProperties = soundProperties;
    }

    @Override
    @Scheduled(cron = "0 0 12 ? * FRI")
    public void send() {
        log.debug("Sending notifications has been started!");
        player.play(soundProperties);
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
            case MESSAGE -> applicationContext.getBean("notificationHolderSender", ServiceSender.class);
        };
    }
}
