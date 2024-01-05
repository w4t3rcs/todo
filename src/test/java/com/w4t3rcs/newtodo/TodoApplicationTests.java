package com.w4t3rcs.newtodo;

import com.w4t3rcs.newtodo.model.common.MessageSender;
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
        //:D
        MessageSender notificationSender = applicationContext.getBean("notificationSender", MessageSender.class);
        notificationSender.send();
    }
}
