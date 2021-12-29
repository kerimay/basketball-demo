package com.kerimay.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/")
    public String homePage() {
        return "Home page for Basketball Demo!";
    }
}
