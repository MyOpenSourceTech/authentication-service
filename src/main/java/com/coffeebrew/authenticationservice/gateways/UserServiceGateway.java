package com.coffeebrew.authenticationservice.gateways;

import com.coffeebrew.authenticationservice.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient(name = "user-service", url = "localhost:3001")
public interface UserServiceGateway {

    @PostMapping("/users")
    public ResponseEntity<User> save(@RequestBody User user);

    @GetMapping(path = "/users/{id}")
    public ResponseEntity<User> getById(@PathVariable String id);

    @GetMapping("/users")
    public ResponseEntity<User> getByName(@RequestParam("name") String name);
}
