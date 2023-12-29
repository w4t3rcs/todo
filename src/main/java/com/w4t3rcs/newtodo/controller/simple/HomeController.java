package com.w4t3rcs.newtodo.controller.simple;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class HomeController {
    @GetMapping
    public String getHomePage() {
        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        boolean authenticated = user != null && user.isAuthenticated() && (!user.getName().equals("anonymousUser") || user.getName() == null);
        return authenticated ? "authenticated/home" : "main/home";
    }
}
