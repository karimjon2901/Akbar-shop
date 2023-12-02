package com.handiwork.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TagTranslatorDto {
    private Long id;
    private List<String> uz;
    private List<String> ru;
    private List<String> en;
}