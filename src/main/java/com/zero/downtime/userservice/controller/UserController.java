package com.zero.downtime.userservice.controller;

import com.zero.downtime.userservice.entity.User;
import com.zero.downtime.userservice.repository.UserRepository;

import io.getunleash.Unleash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Unleash unleash;

    @Value("${app.version}")
    private String appVersion;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
    }

//    @GetMapping
//    public List<User> getAllUsers() {
//        return userRepository.findAll();
//    }
    @GetMapping
    public List<User> getAllUsers() {
        boolean showStatus = unleash.isEnabled("showNewStatusField");

        List<User> users = userRepository.findAll();

        if (!showStatus) {
            for (User user : users) {
                user.setStatus(null); // Hide status field dynamically
            }
        }

        return users;
    }
    @GetMapping("/version")
    public String getVersion() {
        return "Current Version: " + appVersion;
    }
}
