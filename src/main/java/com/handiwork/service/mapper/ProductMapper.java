package com.handiwork.service.mapper;

import com.handiwork.dto.ProductDto;
import com.handiwork.repository.CategoryRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.handiwork.entity.Product;
import org.springframework.stereotype.Component;

@Component
public abstract class ProductMapper implements CommonMapper<ProductDto, Product> {

    @Autowired
    protected CategoryMapper categoryMapper;
    @Autowired
    protected TagTranslatorMapper tagTranslatorMapper;
    @Autowired
    protected TranslatorMapper translatorMapper;
    @Autowired
    protected CategoryRepository categoryRepository;

    @Override
    public abstract ProductDto toDto(Product product);

    @Override
    public abstract Product toEntity(ProductDto productDto);
}