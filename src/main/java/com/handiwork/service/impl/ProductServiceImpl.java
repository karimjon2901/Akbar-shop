package com.handiwork.service.impl;

import com.handiwork.dto.ProductDto;
import com.handiwork.dto.ResponseDto;
import com.handiwork.entity.Product;
import com.handiwork.repository.CategoryRepository;
import com.handiwork.repository.ProductRepository;
import com.handiwork.service.IdGenerator;
import com.handiwork.service.ProductService;
import com.handiwork.service.mapper.ProductMapper;
import com.handiwork.service.mapper.TranslatorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.handiwork.status.AppStatusMessage.*;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final TranslatorMapper translatorMapper;
    private final CategoryRepository categoryRepository;
    private final IdGenerator idGenerator;
    @Override
    public ResponseDto<ProductDto> addProduct(ProductDto productDto) {
        try {
            productDto.setId(idGenerator.generate());
            productRepository.save(productMapper.toEntity(productDto));

            return ResponseDto.<ProductDto>builder()
                    .message(OK)
                    .success(true)
                    .data(productDto)
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
            Optional<Product> byId = productRepository.findById(productDto.getId());

            if (byId.isEmpty()){
                return ResponseDto.<ProductDto>builder()
                        .message(NOT_FOUND)
                        .build();
            }

            Product product = byId.get();
            product.setName(productDto.getName() != null ? translatorMapper.toEntity(productDto.getName()) : product.getName());
            product.setProduct_info(productDto.getProduct_info() != null ? translatorMapper.toEntity(productDto.getProduct_info()) : product.getProduct_info());
            product.setAmount(productDto.getAmount() != null ? productDto.getAmount() : product.getAmount());
            product.setCategory_id(productDto.getCategory_id() != null ? categoryRepository.findById(productDto.getId()).get() : product.getCategory_id());
            product.setPrice(productDto.getPrice() != null ? productDto.getPrice() : product.getPrice());

            productRepository.save(product);

            return ResponseDto.<ProductDto>builder()
                    .message(OK)
                    .success(true)
                    .data(productMapper.toDto(product))
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
        for (ProductDto product : products) {
            System.out.println(product.getTags().getClass());
        }
        return ResponseDto.<Page<ProductDto>>builder()
                .message(OK)
                .success(true)
                .data(products)
                .build();
    }

    @Override
    public ResponseDto<ProductDto> getProductById(String id) {
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

    @Override
    public ResponseDto<ProductDto> delete(String  id) {
        if (id == null){
            return ResponseDto.<ProductDto>builder()
                    .message(NULL_ID)
                    .build();
        }
        try {
            Optional<Product> byId = productRepository.findById(id);

            if (byId.isEmpty()){
                return ResponseDto.<ProductDto>builder()
                        .message(NOT_FOUND)
                        .build();
            }

            productRepository.deleteById(id);

            return ResponseDto.<ProductDto>builder()
                    .message(OK)
                    .success(true)
                    .data(productMapper.toDto(byId.get()))
                    .build();
        } catch (Exception e){
            return ResponseDto.<ProductDto>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }
}