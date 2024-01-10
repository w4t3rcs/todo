package com.w4t3rcs.newtodo.controller.simple.common;

import com.w4t3rcs.newtodo.model.entity.authentication.User;
import com.w4t3rcs.newtodo.model.common.container.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class HomeController {
    private final Getter<User> currentUserGetter;

    @Autowired
    public HomeController(Getter<User> currentUserGetter) {
        this.currentUserGetter = currentUserGetter;
    }

    @GetMapping
    public String getHomePage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean authenticated = authentication != null && authentication.isAuthenticated() && (!authentication.getName().equals("anonymousUser") || authentication.getName() == null);
        if (authenticated) {
            User user = currentUserGetter.get();
            return user.getRole().equals("user") ? "authenticated/home" : "admin/home";
        } else {
            return "main/home";
        }
    }
}
