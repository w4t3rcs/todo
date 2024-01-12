package com.w4t3rcs.newtodo;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(TodoApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }
}
