package com.example.handwork.service.mapper;

import com.example.handwork.dto.CategoryDto;
import com.example.handwork.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class CategoryMapper implements CommonMapper<CategoryDto, Category>{
    @Override
    public abstract CategoryDto toDto(Category category);

    @Override
    public abstract Category toEntity(CategoryDto categoryDto);
}