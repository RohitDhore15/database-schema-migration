package com.zero.downtime.userservice.controller;

import com.zero.downtime.userservice.entity.User;
import com.zero.downtime.userservice.repository.UserRepository;
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

    @Value("${app.version}")
    private String appVersion;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/version")
    public String getVersion() {
        return "Current Version: " + appVersion;
    }
}
