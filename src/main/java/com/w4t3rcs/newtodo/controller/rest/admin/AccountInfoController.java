package com.w4t3rcs.newtodo.controller.rest.admin;

import com.w4t3rcs.newtodo.model.data.dao.UserRepository;
import com.w4t3rcs.newtodo.model.entity.authentication.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@CrossOrigin("http://localhost:8080")
@RequestMapping(path = "/api/account", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class AccountInfoController {
    private final UserRepository userRepository;

    @Autowired
    public AccountInfoController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getAllUsers() {
        List<User> users = (List<User>) userRepository.findAll();
        addLinks(users);
        return users;
    }

    @GetMapping(params = "admin")
    public List<User> getAllAdmins() {
        List<User> admins = userRepository.findAllByRole("admin");
        addLinks(admins);
        return admins;
    }

    @GetMapping(params = "default")
    public List<User> getAllDefaultUsers() {
        List<User> users = userRepository.findAllByRole("user");
        addLinks(users);
        return users;
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

    private void addLinks(Iterable<User> users) {
        users.forEach(user -> user.add(linkTo(AccountInfoController.class).slash(user.getName()).withSelfRel()));
    }
}
