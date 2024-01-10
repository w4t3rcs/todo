package com.w4t3rcs.newtodo.model.data.dto;

import com.w4t3rcs.newtodo.model.entity.message.Notification;
import com.w4t3rcs.newtodo.model.entity.time.Deadline;
import com.w4t3rcs.newtodo.model.entity.authentication.User;
import com.w4t3rcs.newtodo.model.common.container.Getter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Data
public class NotificationDTO {
    private Long id;
    private User to;
    @NotNull(message = "Invalid method")
    private Notification.Method method;
    @Valid
    @NotNull(message = "Invalid schedule")
    private Deadline deadline;
    private boolean enabled;
    private String receivingAddress;

    public static NotificationDTO fromNotification(Notification notification) {
        return NotificationDTO.builder()
                .id(notification.getId())
                .to(notification.getTo())
                .method(notification.getMethod() == null ? Notification.Method.EMAIL : notification.getMethod())
                .deadline(notification.getDeadline())
                .enabled(notification.isEnabled())
                .build();
    }

    public Notification toNotification(Getter<User> currentUserGetter) {
        return Notification.builder()
                .id(this.getId())
                .to(this.getTo() == null ? currentUserGetter.get() : this.getTo())
                .method(this.getMethod())
                .deadline(this.getDeadline())
                .enabled(this.isEnabled())
                .build();
    }
}
