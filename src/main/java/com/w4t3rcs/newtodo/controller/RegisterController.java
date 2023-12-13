package com.w4t3rcs.newtodo.controller;

import com.w4t3rcs.newtodo.model.data.dao.UserRepository;
import com.w4t3rcs.newtodo.model.data.dto.UserDTO;
import com.w4t3rcs.newtodo.model.entity.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/register")
@Controller
public class RegisterController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegisterController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @ModelAttribute("user")
    public UserDTO userDTO() {
        return new UserDTO();
    }

    @GetMapping
    public String getRegistrationPage() {
        return "main/register";
    }

    @PostMapping
    public String postRegistration(@ModelAttribute("user") @Valid UserDTO userDTO, BindingResult bindingResult) {
        System.out.println(userDTO);
        if (bindingResult.hasErrors()) return "main/register";

        User user = userDTO.toUser(passwordEncoder);
        userRepository.save(user);
        return "redirect:/login";
    }
}
