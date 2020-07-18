package com.coffeebrew.authenticationservice.gateways;

import com.coffeebrew.authenticationservice.models.User;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "user-service")
@RibbonClient(name = "user-service")
public interface UserServiceGateway {

    @PostMapping("/users")
    public ResponseEntity<User> save(@RequestBody User user);

    @GetMapping(path = "/users/{id}")
    public ResponseEntity<User> getById(@PathVariable String id);

    @GetMapping("/users")
    public ResponseEntity<User> getByName(@RequestParam("name") String name);
}
