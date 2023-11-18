package com.example.handwork.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Translator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 10000)
    private String uz;
    @Column(length = 10000)
    private String en;
    @Column(length = 10000)
    private String ru;

}