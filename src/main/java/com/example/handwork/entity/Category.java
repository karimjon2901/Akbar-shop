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
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    private Category parentId;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Translator name;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Translator description;
    @CreatedDate
    @CreationTimestamp
    private LocalDateTime createdAt;
}