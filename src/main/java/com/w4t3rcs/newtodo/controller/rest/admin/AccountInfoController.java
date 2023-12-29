package com.w4t3rcs.newtodo.controller.rest.admin;

import com.w4t3rcs.newtodo.model.data.dao.UserRepository;
import com.w4t3rcs.newtodo.model.entity.User;
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

    @GetMapping("/{name}")
    public ResponseEntity<User> getUserByName(@PathVariable String name) {
        return ResponseEntity.of(userRepository.findByName(name));
    }
}
