package com.example.handwork.service;

import com.example.handwork.dto.ProductInputDto;
import com.example.handwork.dto.ResponseDto;
import org.springframework.data.domain.Page;
import com.example.handwork.dto.ProductDto;

public interface ProductService {
    ResponseDto<ProductDto> addProduct(ProductInputDto productInputDto);
    ResponseDto<ProductDto> updateProduct(ProductInputDto productInputDto);
    ResponseDto<Page<ProductDto>> getAllProducts(Integer page, Integer size);
    ResponseDto<ProductDto> getProductById(Integer id);

    ResponseDto<ProductDto> delete(Integer id);
}