package com.example.handwork.service.impl;

import com.example.handwork.service.mapper.TranslatorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.example.handwork.dto.ProductDto;
import com.example.handwork.dto.ResponseDto;
import com.example.handwork.entity.Product;
import com.example.handwork.repository.ProductRepository;
import com.example.handwork.service.ProductService;
import com.example.handwork.service.mapper.CategoryMapper;
import com.example.handwork.service.mapper.ProductMapper;

import java.util.Optional;
import static com.example.handwork.status.AppStatusMessage.*;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryMapper categoryMapper;
    private final TranslatorMapper translatorMapper;

    @Override
    public ResponseDto<ProductDto> addProduct(ProductDto productDto) {
        Product product = productMapper.toEntity(productDto);
        try{
            productRepository.save(product);

            return ResponseDto.<ProductDto>builder()
                    .success(true)
                    .data(productMapper.toDto(product))
                    .message(OK)
                    .build();
        } catch (Exception e){
            return ResponseDto.<ProductDto>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }

    }

    @Override
    public ResponseDto<ProductDto> updateProduct(ProductDto productDto) {
        if (productDto.getId() == null) {
            return ResponseDto.<ProductDto>builder()
                    .message(NULL_ID)
                    .build();
        }
        try {
            Optional<Product> optional = productRepository.findById(productDto.getId());

            if (optional.isEmpty()) {
                return ResponseDto.<ProductDto>builder()
                        .message(NOT_FOUND)
                        .build();
            }

            Product product = optional.get();

            if (productDto.getName() != null) {
                product.setName(translatorMapper.toEntity(productDto.getName()));
            }
            if (productDto.getPrice() != null) {
                product.setPrice(productDto.getPrice());
            }
            if (productDto.getAmount() != null && productDto.getAmount() > 0) {
                product.setIsAvailable(true);
                product.setAmount(productDto.getAmount());
            }
            if (productDto.getDescription() != null) {
                product.setDescription(translatorMapper.toEntity(productDto.getDescription()));
            }
            if(productDto.getCategory() != null) {
                product.setCategory(categoryMapper.toEntity(productDto.getCategory()));
            }

            productRepository.save(product);

            return ResponseDto.<ProductDto>builder()
                    .message(OK)
                    .data(productMapper.toDto(product))
                    .success(true)
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ProductDto>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<Page<ProductDto>> getAllProducts(Integer page, Integer size) {
        Long count = productRepository.count();

        PageRequest pageRequest = PageRequest.of(
                (count / size) <= page ?
                        (count % size == 0 ? (int) (count / size) - 1
                                : (int) (count / size))
                        : page,
                size);

        Page<ProductDto> products = productRepository.findAll(pageRequest)
                .map(productMapper::toDto);

        return ResponseDto.<Page<ProductDto>>builder()
                .message(OK)
                .success(true)
                .data(products)
                .build();
    }

    @Override
    public ResponseDto<ProductDto> getProductById(Integer id) {
        return productRepository.findById(id)
                .map(products -> ResponseDto.<ProductDto>builder()
                        .data(productMapper.toDto(products))
                        .success(true)
                        .message(OK)
                        .build())
                .orElse(ResponseDto.<ProductDto>builder()
                        .message(NOT_FOUND)
                        .build()
                );
    }
}