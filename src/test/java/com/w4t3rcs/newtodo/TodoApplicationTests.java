package com.w4t3rcs.newtodo;

import com.w4t3rcs.newtodo.model.common.executor.sender.MessageSender;
import com.w4t3rcs.newtodo.model.service.sender.message.NotificationSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class TodoApplicationTests {
    private final ApplicationContext applicationContext;
    @Autowired
    public TodoApplicationTests(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Test
    void contextLoads() {
        MessageSender sender = applicationContext.getBean(NotificationSender.class);
        sender.send();
    }
}
