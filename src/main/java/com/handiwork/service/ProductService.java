package com.handiwork.service;

import com.handiwork.dto.ResponseDto;
import org.springframework.data.domain.Page;
import com.handiwork.dto.ProductDto;

public interface ProductService {
    ResponseDto<ProductDto> addProduct(ProductDto productDto);
    ResponseDto<ProductDto> updateProduct(ProductDto productDto);
    ResponseDto<Page<ProductDto>> getAllProducts(Integer page, Integer size);
    ResponseDto<ProductDto> getProductById(String id);

    ResponseDto<ProductDto> delete(String id);
}