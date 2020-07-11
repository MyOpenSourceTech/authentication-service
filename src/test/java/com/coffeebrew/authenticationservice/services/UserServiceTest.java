package com.coffeebrew.authenticationservice.services;

import com.coffeebrew.authenticationservice.gateways.UserServiceGateway;
import com.coffeebrew.authenticationservice.models.User;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {
    EasyRandom random;

    @Mock
    UserServiceGateway userServiceGateway;

    @InjectMocks
    UserService target;

    @BeforeEach
    void setUp() {
        random = new EasyRandom();
    }

    @Test
    public void shouldGetUserByName() {
        String name = random.nextObject(String.class);
        User user = random.nextObject(User.class);

        when(userServiceGateway.getByName(name)).thenReturn(ResponseEntity.of(Optional.of(user)));

        User returnedUser = target.getByName(name);

        assertEquals(user, returnedUser);
    }

    @Test
    public void shouldCreateNewUser() {
        User user = random.nextObject(User.class);
        when(userServiceGateway.save(user)).thenReturn(ResponseEntity.of(Optional.of(user)));

        User returnedUser = target.save(user);

        assertEquals(user, returnedUser);
    }
}