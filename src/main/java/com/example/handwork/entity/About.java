package com.example.handwork.entity;

import com.example.handwork.entity.Translator;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class About {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Translator title;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Translator description;

    @UpdateTimestamp
    private LocalDateTime createdAt;
}