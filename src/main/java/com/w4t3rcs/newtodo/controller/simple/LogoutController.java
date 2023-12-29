package com.w4t3rcs.newtodo.controller.simple;

import com.w4t3rcs.newtodo.model.entity.User;
import com.w4t3rcs.newtodo.model.service.getter.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final Getter<User> currentUserGetter;

    @Autowired
    public LogoutController(Getter<User> currentUserGetter) {
        this.currentUserGetter = currentUserGetter;
    }

    @GetMapping
    public String getLogoutPage() {
        User checked = currentUserGetter.get();
        log.info("{} - wants to leave", checked.getName());
        return "authenticated/exit";
    }

    @PostMapping
    public String logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("{} - has left", authentication);
        return "redirect:/";
    }
}
