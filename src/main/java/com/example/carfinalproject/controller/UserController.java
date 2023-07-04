package com.example.carfinalproject.controller;

import com.example.carfinalproject.dto.UpdateUserRequest;
import com.example.carfinalproject.dto.UserRequest;
import com.example.carfinalproject.dto.UserResponse;
import com.example.carfinalproject.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> userRegistration(@Valid @RequestBody UserRequest request){
        UserResponse response = userService.saveUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(userService.getUser(id));
    }
    @DeleteMapping(path = "/{id}")
    public HttpStatus deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return HttpStatus.ACCEPTED;
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id,
                                                   @RequestBody UpdateUserRequest request){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.updateUser(id,request));
    }
    @GetMapping("/find")
    public ResponseEntity<UserResponse> findUserByEmail(@RequestParam String email){
        return ResponseEntity.status(HttpStatus.FOUND).body(userService.findByEmail(email));
    }

}
