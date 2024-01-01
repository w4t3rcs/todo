package com.w4t3rcs.newtodo;

import com.w4t3rcs.newtodo.model.data.dao.NotificationRepository;
import com.w4t3rcs.newtodo.model.data.dao.UserRepository;
import com.w4t3rcs.newtodo.model.entity.message.Notification;
import com.w4t3rcs.newtodo.model.entity.time.Schedule;
import com.w4t3rcs.newtodo.model.entity.time.TimePeriod;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootTest
class TodoApplicationTests {
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    @Autowired
    public TodoApplicationTests(NotificationRepository notificationRepository, UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    @Test
    void contextLoads() {
        Notification notification = new Notification();
//        notification.setId(0L);
        notification.setTo(userRepository.findByName("admin").orElseThrow());
        notification.setMethod(Notification.Method.EMAIL);
        notification.setSchedule(new Schedule(TimePeriod.MONTH, 5, LocalTime.MIDNIGHT, LocalDate.now()));

        Notification save = notificationRepository.save(notification);
        System.out.println(save);
    }
}
