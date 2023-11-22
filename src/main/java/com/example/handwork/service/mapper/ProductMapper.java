package com.example.handwork.service.mapper;

import com.example.handwork.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.handwork.entity.Product;

@Mapper(componentModel = "spring")
public abstract class ProductMapper implements CommonMapper<ProductDto, Product> {

    @Autowired
    protected CategoryMapper categoryMapper;

    @Override
    public abstract ProductDto toDto(Product product);

    @Override
    public abstract Product toEntity(ProductDto productDto);
}