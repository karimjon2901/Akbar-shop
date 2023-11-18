package com.example.handwork.service;

import com.example.handwork.dto.ResponseDto;
import org.springframework.data.domain.Page;
import com.example.handwork.dto.ProductDto;

public interface ProductService {
    ResponseDto<ProductDto> addProduct(ProductDto productDto);
    ResponseDto<ProductDto> updateProduct(ProductDto productDto);
    ResponseDto<Page<ProductDto>> getAllProducts(Integer page, Integer size);
    ResponseDto<ProductDto> getProductById(Integer id);
}