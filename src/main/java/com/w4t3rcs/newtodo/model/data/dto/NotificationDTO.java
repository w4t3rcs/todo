package com.w4t3rcs.newtodo.model.data.dto;

import com.w4t3rcs.newtodo.model.entity.message.Notification;
import com.w4t3rcs.newtodo.model.entity.time.Schedule;
import com.w4t3rcs.newtodo.model.entity.authentication.User;
import com.w4t3rcs.newtodo.model.common.Getter;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NotificationDTO {
    @NotNull(message = "Invalid method")
    private Notification.Method method;
    @NotNull(message = "Invalid schedule")
    private Schedule schedule;

    public Notification toNotification(Getter<User> currentUserGetter) {
        User currentUser = currentUserGetter.get();
        Notification notification = new Notification();
        notification.setTo(currentUser);
        notification.setMethod(this.method);
        notification.setSchedule(this.schedule);
        return notification;
    }
}
