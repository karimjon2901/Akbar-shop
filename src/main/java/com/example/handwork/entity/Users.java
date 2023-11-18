package com.example.handwork.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String phoneNumber;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Short isActive;
    private boolean enabled;
    @Column(columnDefinition = "text default('USER')")
    private String role;
    @CreatedDate
    @CreationTimestamp
    private LocalDateTime createdAt;
}