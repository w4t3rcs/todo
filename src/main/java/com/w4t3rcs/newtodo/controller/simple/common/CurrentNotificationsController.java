package com.w4t3rcs.newtodo.controller.simple.common;

import com.w4t3rcs.newtodo.model.common.Getter;
import com.w4t3rcs.newtodo.model.data.dao.NotificationHolderRepository;
import com.w4t3rcs.newtodo.model.data.dao.NotificationRepository;
import com.w4t3rcs.newtodo.model.entity.authentication.User;
import com.w4t3rcs.newtodo.model.entity.holder.NotificationHolder;
import com.w4t3rcs.newtodo.model.entity.message.Notification;
import com.w4t3rcs.newtodo.model.exception.NotificationNotEnabledException;
import com.w4t3rcs.newtodo.model.properties.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/notifications")
@Controller
public class CurrentNotificationsController {
    private final NotificationRepository notificationRepository;
    private final NotificationHolderRepository notificationHolderRepository;
    private final MessageProperties messageProperties;
    private final Getter<User> currentUserGetter;

    @Autowired
    public CurrentNotificationsController(NotificationRepository notificationRepository, NotificationHolderRepository notificationHolderRepository, MessageProperties messageProperties, Getter<User> currentUserGetter) {
        this.notificationRepository = notificationRepository;
        this.notificationHolderRepository = notificationHolderRepository;
        this.messageProperties = messageProperties;
        this.currentUserGetter = currentUserGetter;
    }

    @ModelAttribute("notification")
    public Notification notification() {
        User currentUser = currentUserGetter.get();
        return notificationRepository.findByTo(currentUser).orElseThrow(NotificationNotEnabledException::new);
    }

    @ModelAttribute("notifications")
    public Iterable<NotificationHolder> notifications(@ModelAttribute Notification notification) {
        return notificationHolderRepository.findAllByNotification(notification);
    }

    @ModelAttribute("notificationText")
    public String notificationText(@ModelAttribute Notification notification) {
        return notification.getMessageBody(messageProperties);
    }

    @GetMapping
    public String getNotificationsPage() {
        return "authenticated/notifications";
    }
}
