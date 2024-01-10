package com.w4t3rcs.newtodo.controller.simple.common;

import com.w4t3rcs.newtodo.model.common.container.Getter;
import com.w4t3rcs.newtodo.model.data.dao.NotificationHolderRepository;
import com.w4t3rcs.newtodo.model.data.dao.NotificationRepository;
import com.w4t3rcs.newtodo.model.entity.authentication.User;
import com.w4t3rcs.newtodo.model.entity.message.Notification;
import com.w4t3rcs.newtodo.model.exception.NotificationNotEnabledException;
import com.w4t3rcs.newtodo.model.properties.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping
    public String getNotificationsPage(Model model) {
        try {
            User currentUser = currentUserGetter.get();
            Notification notification = notificationRepository.findByTo(currentUser).orElseThrow(NotificationNotEnabledException::new);
            model.addAttribute("notification", notification);
            model.addAttribute("notifications", notificationHolderRepository.findAllByNotification(notification));
            model.addAttribute("notificationText", notification.getMessageBody(messageProperties));
            return "authenticated/notifications";
        } catch (NotificationNotEnabledException e) {
            return "authenticated/notifications-empty";
        }
    }
}
