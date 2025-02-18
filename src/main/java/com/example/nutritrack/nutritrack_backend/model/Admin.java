package com.example.nutritrack.nutritrack_backend.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "admins")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;

    @Column(name = "Name")
    private String name;

    @Column(name = "Password", nullable = false)
    private String password;

    @Column(name = "email_id", nullable = false, unique = true)
    private String email;

    @Column(name = "recent_activity")
    private String recentActivity;
}
