package com.kerimay.basketball.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

    @GetMapping("/home")
    public String homeUser() {
        return "Welcome to basketball demo! You need to login to continue.";
    }

    @GetMapping("/login")
    public String loginUser() {
        return "You're logged in!";
    }
}
