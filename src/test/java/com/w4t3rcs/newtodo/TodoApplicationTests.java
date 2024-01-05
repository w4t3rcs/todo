package com.w4t3rcs.newtodo;

import com.w4t3rcs.newtodo.model.common.ServiceSender;
import com.w4t3rcs.newtodo.model.data.dao.NotificationRepository;
import com.w4t3rcs.newtodo.model.data.dao.TaskRepository;
import com.w4t3rcs.newtodo.model.data.dao.TodoRepository;
import com.w4t3rcs.newtodo.model.data.dao.UserRepository;
import com.w4t3rcs.newtodo.model.data.dto.TaskDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class TodoApplicationTests {
    private final ApplicationContext applicationContext;
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    @Autowired
    public TodoApplicationTests(ApplicationContext applicationContext, NotificationRepository notificationRepository, UserRepository userRepository) {
        this.applicationContext = applicationContext;
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    @Test
    void contextLoads() {
        ServiceSender bean = applicationContext.getBean("emailSender", ServiceSender.class);
        bean.send(null, "uimanovmaks@gmail.com", "hello", ":)");
    }
}
