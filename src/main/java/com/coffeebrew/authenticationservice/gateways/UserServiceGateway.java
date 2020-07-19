package com.coffeebrew.authenticationservice.gateways;

import com.coffeebrew.authenticationservice.models.User;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "gateway-server")
@RibbonClient(name = "user-service")
public interface UserServiceGateway {

    @PostMapping("/user-service/users")
    public ResponseEntity<User> save(@RequestBody User user);

    @GetMapping(path = "/user-service/users/{id}")
    public ResponseEntity<User> getById(@PathVariable String id);

    @GetMapping("/user-service/users")
    public ResponseEntity<User> getByName(@RequestParam("name") String name);
}
