package com.coffeebrew.authenticationservice.services;

import com.coffeebrew.authenticationservice.gateways.UserServiceGateway;
import com.coffeebrew.authenticationservice.models.Role;
import com.coffeebrew.authenticationservice.models.User;
import com.coffeebrew.authenticationservice.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserServiceGateway userServiceGateway;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;


    public User getByName(String name) {
        ResponseEntity<User> responseEntity;
        try {
            responseEntity = userServiceGateway.getByName(name);
            return responseEntity.getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public User save(User user) {
        return userServiceGateway.save(user).getBody();
    }

    public String signUp(User user) throws Exception {
        User userDetails = getByName(user.getName());
        if (userDetails == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userServiceGateway.save(user);
            return jwtTokenProvider.createToken(user.getName(), user.getRoles());
        } else {
            throw new Exception("Username is already in use");
        }
    }

    public String signIn(String username, String password) throws Exception {
        try {
            List<Role> roles = userServiceGateway.getByName(username).getBody().getRoles();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return jwtTokenProvider.createToken(username, roles);
        } catch (AuthenticationException e) {
            throw new Exception("Invalid username/password supplied");
        }
    }
}
