package com.example.handwork.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.example.handwork.status.AppStatusMessage.EMPTY_STRING;
import static com.example.handwork.status.AppStatusMessage.NEGATIVE_VALUE;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Integer id;
    @NotBlank(message = EMPTY_STRING)
    private TranslatorDto name;
    @Positive(message = NEGATIVE_VALUE)
    private Double price;
    private Integer amount;
    @NotBlank(message = EMPTY_STRING)
    private TranslatorDto description;
    private CategoryDto category;
    private Boolean isAvailable;
}