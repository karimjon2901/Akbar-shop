package com.example.handwork.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Integer id;
    private String image;
    private TranslatorDto name;
    private Double price;
    private Integer amount;
    private TranslatorDto description;
    private CategoryDto category;
    private Boolean isAvailable;
}