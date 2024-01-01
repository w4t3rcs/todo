package com.w4t3rcs.newtodo.controller.rest.admin;

import com.w4t3rcs.newtodo.model.data.dao.UserRepository;
import com.w4t3rcs.newtodo.model.entity.authentication.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:8080")
@RequestMapping(path = "/api/account", produces = "application/json")
@RestController
public class AccountInfoController {
    private final UserRepository userRepository;

    @Autowired
    public AccountInfoController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @GetMapping(params = "admin")
    public List<User> getAllAdmins() {
        return userRepository.findAllByRole("admin");
    }

    @GetMapping(params = "default")
    public List<User> getAllDefaultUsers() {
        return userRepository.findAllByRole("user");
    }

    @GetMapping(params = "username")
    public List<String> getAllUsernames() {
        return userRepository.findAllUsernames();
    }

    @GetMapping(params = "email")
    public List<String> getAllEmails() {
        return userRepository.findAllEmails();
    }

    @GetMapping("/{name}")
    public ResponseEntity<User> getUserByName(@PathVariable String name) {
        return ResponseEntity.of(userRepository.findByName(name));
    }
}
