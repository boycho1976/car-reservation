package com.example.carfinalproject.converter;

import com.example.carfinalproject.dto.UserRequest;
import com.example.carfinalproject.dto.UserResponse;
import com.example.carfinalproject.entity.User;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
public class UserConverter {
    public User toUser(UserRequest request){
        return User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(request.getPassword())
                .registerDate(Date.from(Instant.now()))
                .build();
    }
    public UserResponse toResponse(User user){
        return UserResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }
}
