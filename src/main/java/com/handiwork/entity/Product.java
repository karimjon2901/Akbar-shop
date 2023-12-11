package com.handiwork.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.w3c.dom.stylesheets.LinkStyle;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    private String id;
    private String img;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Translator name;
    private Double price;
    private Integer amount;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Translator product_info;
    @ManyToOne
    private Category category;
    private Integer assessments;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Translator tags;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Translator status;
    @CreationTimestamp
    private LocalDateTime createdAt;
}