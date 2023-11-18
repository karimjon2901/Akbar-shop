package com.example.handwork.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TranslatorDto {
    private Long id;
    private String uz;
    private String ru;
    private String en;
}
