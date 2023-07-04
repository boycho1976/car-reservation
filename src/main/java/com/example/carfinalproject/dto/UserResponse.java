package com.example.carfinalproject.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserResponse {
    String email;
    String firstName;
    String lastName;
}
