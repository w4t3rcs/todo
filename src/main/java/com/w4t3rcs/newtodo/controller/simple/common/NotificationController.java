package com.w4t3rcs.newtodo.controller.simple.common;

import com.w4t3rcs.newtodo.model.data.dao.NotificationRepository;
import com.w4t3rcs.newtodo.model.data.dto.NotificationDTO;
import com.w4t3rcs.newtodo.model.entity.message.Notification;
import com.w4t3rcs.newtodo.model.entity.authentication.User;
import com.w4t3rcs.newtodo.model.common.Getter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/notification")
@Controller
public class NotificationController {
    private final NotificationRepository notificationRepository;
    private final Getter<User> currentUserGetter;

    @Autowired
    public NotificationController(NotificationRepository notificationRepository, Getter<User> currentUserGetter) {
        this.notificationRepository = notificationRepository;
        this.currentUserGetter = currentUserGetter;
    }

    @ModelAttribute("notification")
    public NotificationDTO notificationDTO() {
        Notification userNotificationFromDb = notificationRepository.findByTo(currentUserGetter.get()).orElse(new Notification());
        return NotificationDTO.fromNotification(userNotificationFromDb);
    }

    @ModelAttribute("notificationMethods")
    public Notification.Method[] notificationMethods() {
        return Notification.Method.values();
    }

    @GetMapping
    public String getNotificationPage() {
        return "authenticated/notification";
    }

    @PutMapping
    public String putNotification(@ModelAttribute("notification") @Valid NotificationDTO notificationDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "authenticated/notification";
        Notification notification = notificationDTO.toNotification(currentUserGetter);
        notificationRepository.save(notification);
        return "redirect:/account";
    }
}
