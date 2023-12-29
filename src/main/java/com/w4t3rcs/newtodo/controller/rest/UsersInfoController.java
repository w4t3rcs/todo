package com.w4t3rcs.newtodo.controller.rest;

import com.w4t3rcs.newtodo.model.data.dao.UserRepository;
import com.w4t3rcs.newtodo.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("http://localhost:8080")
@RequestMapping(path = "/api/user", produces = "application/json")
@RestController
public class UsersInfoController {
    private final UserRepository userRepository;

    @Autowired
    public UsersInfoController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }
}
