package com.w4t3rcs.newtodo.controller;

import com.w4t3rcs.newtodo.model.data.dao.UserRepository;
import com.w4t3rcs.newtodo.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/account")
@Controller
public class AccountController {
    private final UserRepository userRepository;

    @Autowired
    public AccountController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String getCurrentAccountPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = getUserByUsername(username);
        model.addAttribute(user);
        return "authenticated/current";
    }

    @GetMapping("/{username}")
    public String getAccountPage(@PathVariable String username, Model model) {
        User user = getUserByUsername(username);
        model.addAttribute(user);
        return "authenticated/user";
    }

    private User getUserByUsername(String username) {
        return userRepository.findByName(username).orElseThrow(() -> new UsernameNotFoundException("No account with this username!"));
    }
}
