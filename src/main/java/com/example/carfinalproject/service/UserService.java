package com.example.carfinalproject.service;

import com.example.carfinalproject.dto.UpdateUserRequest;
import com.example.carfinalproject.dto.UserRequest;
import com.example.carfinalproject.dto.UserResponse;
import com.example.carfinalproject.entity.User;

public interface UserService {
    UserResponse saveUser(UserRequest request);
    UserResponse getUser(Long id);

    String deleteUser(long id);

    UserResponse updateUser(Long id, UpdateUserRequest request);

    UserResponse findByEmail(String email);

}
