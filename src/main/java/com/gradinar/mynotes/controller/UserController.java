package com.gradinar.mynotes.controller;

import com.gradinar.mynotes.entity.User;
import com.gradinar.mynotes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> userRegistration(@Valid @RequestBody User user) {
        return userService.registration(user);
    }

}
