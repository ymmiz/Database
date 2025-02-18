package com.example.nutritrack.nutritrack_backend.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO{

    private String name;
    private String email;
    private String password;
    private int age;
    private double weight;
    private String goal;
    private Long nutritionistId;
}
