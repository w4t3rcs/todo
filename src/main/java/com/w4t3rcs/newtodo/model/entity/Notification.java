package com.w4t3rcs.newtodo.model.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    @NotEmpty(message = "Invalid message")
    private String text;
    private User to;
    @NotNull(message = "Invalid method")
    private Method method;
    @NotNull(message = "Invalid schedule")
    private Schedule schedule;

    public enum Method {
        EMAIL
    }
}
