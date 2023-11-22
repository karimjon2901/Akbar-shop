package com.example.handwork.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String image;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Translator name;
    private Double price;
    private Integer amount;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Translator description;
    @ManyToOne
    private Category category;
    private Boolean isAvailable;
    @CreatedDate
    private LocalDateTime createdAt;
}