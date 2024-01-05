package com.w4t3rcs.newtodo.model.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "todo.message")
@Component
public class MessageProperties {
    private String notificationSubject;
    private String notificationText;
}
