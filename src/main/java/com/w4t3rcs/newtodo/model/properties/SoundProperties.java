package com.w4t3rcs.newtodo.model.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "todo.sound")
@Component
public class SoundProperties {
    private Notification notification;

    @Data
    public static class Notification {
        private String path;
    }
}