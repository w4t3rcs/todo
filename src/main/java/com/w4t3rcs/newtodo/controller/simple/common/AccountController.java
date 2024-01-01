package com.w4t3rcs.newtodo.controller.simple.common;

import com.w4t3rcs.newtodo.model.data.dao.UserRepository;
import com.w4t3rcs.newtodo.model.entity.authentication.User;
import com.w4t3rcs.newtodo.model.common.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/account")
@Controller
public class AccountController {
    private final UserRepository userRepository;
    private final Getter<User> currentUserGetter;

    @Autowired
    public AccountController(UserRepository userRepository, Getter<User> currentUserGetter) {
        this.userRepository = userRepository;
        this.currentUserGetter = currentUserGetter;
    }

    @GetMapping
    public String getCurrentAccountPage(Model model) {
        User checked = currentUserGetter.get();
        model.addAttribute("user", checked);
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
