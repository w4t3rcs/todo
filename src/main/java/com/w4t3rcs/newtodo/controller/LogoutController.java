package com.w4t3rcs.newtodo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequestMapping("/exit")
@Controller
public class LogoutController {
    @GetMapping
    public String getLogoutPage() {
        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        log.info("{} - want to leave", user.getName());;
        return "authenticated/exit";
    }

    @PostMapping
    public String logout() {
        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        log.info("{} - has left", user);;
        return "redirect:/";
    }
}
