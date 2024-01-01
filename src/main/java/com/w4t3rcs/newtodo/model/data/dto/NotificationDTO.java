package com.w4t3rcs.newtodo.model.data.dto;

import com.w4t3rcs.newtodo.model.entity.Notification;
import com.w4t3rcs.newtodo.model.entity.Schedule;
import com.w4t3rcs.newtodo.model.entity.User;
import com.w4t3rcs.newtodo.model.properties.MessageProperties;
import com.w4t3rcs.newtodo.model.service.getter.Getter;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NotificationDTO {
    @NotNull(message = "Invalid method")
    private Notification.Method method;
    @NotNull(message = "Invalid schedule")
    private Schedule schedule;

    public Notification toNotification(MessageProperties messageProperties, Getter<User> currentUserGetter) {
        User currentUser = currentUserGetter.get();
        return new Notification(getNotificationText(messageProperties, currentUser), currentUser, this.method, this.schedule);
    }

    public String getNotificationText(MessageProperties messageProperties, User user) {
        return String.format(messageProperties.getNotificationText(), user);
    }
}
