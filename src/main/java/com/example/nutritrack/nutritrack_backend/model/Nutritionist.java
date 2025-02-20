package com.example.nutritrack.nutritrack_backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "nutritionists")
public class Nutritionist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nutritionistId;

    @Column(nullable = false,length = 100)
    private String name;

    @Column(nullable = false,unique = true,length = 100)
    private String email;

    @Column(nullable = false,length = 100)
    private String password;

    @Column(length = 100)
    private String specialization;

    @OneToMany(mappedBy = "assignedByNutritionist", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<User> assignedUsers;

    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MealPlan> mealPlans;
}
