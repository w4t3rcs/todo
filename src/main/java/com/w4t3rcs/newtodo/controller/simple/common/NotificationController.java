package com.w4t3rcs.newtodo.controller.simple.common;

import com.w4t3rcs.newtodo.model.data.dto.NotificationDTO;
import com.w4t3rcs.newtodo.model.entity.Notification;
import com.w4t3rcs.newtodo.model.entity.TimePeriod;
import com.w4t3rcs.newtodo.model.entity.User;
import com.w4t3rcs.newtodo.model.properties.MessageProperties;
import com.w4t3rcs.newtodo.model.service.getter.Getter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/notification")
@Controller
public class NotificationController {
    private final MessageProperties messageProperties;
    private final Getter<User> currentUserGetter;

    @Autowired
    public NotificationController(MessageProperties messageProperties, Getter<User> currentUserGetter) {
        this.messageProperties = messageProperties;
        this.currentUserGetter = currentUserGetter;
    }

    @ModelAttribute("notification")
    public NotificationDTO notificationDTO() {
        return new NotificationDTO();
    }

    @ModelAttribute("notificationMethods")
    public Notification.Method[] notificationMethods() {
        return Notification.Method.values();
    }

    @ModelAttribute("timePeriods")
    public TimePeriod[] timePeriods() {
        return TimePeriod.values();
    }

    @GetMapping
    public String getNotificationPage() {
        return "authenticated/notification";
    }

    @PostMapping
    public String postNotification(@ModelAttribute("notification") @Valid NotificationDTO notificationDTO, BindingResult bindingResult) {
        Notification notification = notificationDTO.toNotification(messageProperties, currentUserGetter);
        System.out.println(notification);
        return "redirect:/account"; // TODO: 01.01.2024
    }
}
