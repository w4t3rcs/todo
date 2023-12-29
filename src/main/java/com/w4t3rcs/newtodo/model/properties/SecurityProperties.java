package com.w4t3rcs.newtodo.model.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "todo.security")
@Component
public class SecurityProperties {
    private String[] patternsForAll;
    private String[] patternsForUsers;
    private String[] patternsForAdmins;
}
