package com.handiwork.service.impl;

import com.handiwork.dto.ProductDto;
import com.handiwork.entity.Product;
import com.handiwork.service.mapper.ProductMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class ProductMapperImpl extends ProductMapper {
    @Override
    public ProductDto toDto(Product product) {
        if (product == null){
            return null;
        }

        ProductDto productDto = new ProductDto();

        productDto.setId(product.getId());
        productDto.setAssessments(product.getAssessments());
        productDto.setImg(product.getImg());
        productDto.setProduct_info(translatorMapper.toDto(product.getProduct_info()));
        productDto.setAmount(product.getAmount());
        productDto.setName(translatorMapper.toDto(product.getName()));
        productDto.setPrice(product.getPrice());
        productDto.setStatus(translatorMapper.toDto(product.getStatus()));
        productDto.setCategory_id(product.getCategory_id().getId());
        productDto.setTags(tagTranslatorMapper.toDto(product.getTags()));

        return productDto;
    }

    @Override
    public Product toEntity(ProductDto productDto) {
        if (productDto == null){
            return null;
        }

        Product product = new Product();

        product.setImg(productDto.getImg());
        product.setStatus(translatorMapper.toEntity(productDto.getStatus()));
        product.setName(translatorMapper.toEntity(productDto.getName()));
        product.setAmount(productDto.getAmount());
        product.setPrice(productDto.getPrice());
        product.setProduct_info(translatorMapper.toEntity(productDto.getProduct_info()));
        product.setCategory_id(categoryRepository.findById(productDto.getCategory_id()).orElse(null));
        product.setId(productDto.getId());
        product.setAssessments(productDto.getAssessments());
        product.setTags(tagTranslatorMapper.toEntity(productDto.getTags()));

        return product;
    }
}
