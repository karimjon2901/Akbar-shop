package com.handiwork.service.mapper;

import com.handiwork.dto.CategoryDto;
import com.handiwork.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class CategoryMapper implements CommonMapper<CategoryDto, Category>{
    @Override
    public abstract CategoryDto toDto(Category category);

    @Override
    public abstract Category toEntity(CategoryDto categoryDto);
}