package com.w4t3rcs.newtodo.model.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "todo.security")
@Component
public class SecurityProperties {
    private Pattern pattern;

    @Data
    public static class Pattern {
        private String[] all;
        private String[] users;
        private String[] admins;
    }
}
