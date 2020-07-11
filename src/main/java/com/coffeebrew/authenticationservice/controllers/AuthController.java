package com.coffeebrew.authenticationservice.controllers;

import com.coffeebrew.authenticationservice.models.AuthRequest;
import com.coffeebrew.authenticationservice.models.User;
import com.coffeebrew.authenticationservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User user) throws Exception {
        try {
            String token = userService.signUp(user);
            return new ResponseEntity<>(token, HttpStatus.CREATED);
        } catch (Exception e) {
            throw e;
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<String> login(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            String token = userService.signIn(authRequest.getName(), authRequest.getPassword());
            return new ResponseEntity<>(token, HttpStatus.OK);
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping("/token-verify")
    public ResponseEntity<String> verifyToken() {
        return new ResponseEntity<>("token is verified", HttpStatus.OK);
    }

    @GetMapping("/whitelist-verify")
    public ResponseEntity<String> verifyWhiteList() {
        return new ResponseEntity<>("This is a whitelisted endpoint", HttpStatus.OK);
    }
}
