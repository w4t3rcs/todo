package com.w4t3rcs.newtodo;

import com.w4t3rcs.newtodo.model.common.ServiceSender;
import com.w4t3rcs.newtodo.model.data.dao.NotificationRepository;
import com.w4t3rcs.newtodo.model.entity.message.Notification;
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
//        MessageSender notificationSender = applicationContext.getBean("notificationSender", MessageSender.class);
//        notificationSender.send();
        ServiceSender telegramSender = applicationContext.getBean("notificationHolderSender", ServiceSender.class);
        NotificationRepository notificationRepository = applicationContext.getBean(NotificationRepository.class);
        Notification notification = notificationRepository.findById(2L).orElseThrow();
        telegramSender.send(notification);
    }
}
