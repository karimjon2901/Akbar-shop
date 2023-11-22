package com.example.handwork.service.impl;

import com.example.handwork.config.S3File;
import com.example.handwork.dto.ProductDto;
import com.example.handwork.dto.ProductInputDto;
import com.example.handwork.dto.ResponseDto;
import com.example.handwork.entity.Product;
import com.example.handwork.repository.CategoryRepository;
import com.example.handwork.repository.ProductRepository;
import com.example.handwork.service.ProductService;
import com.example.handwork.service.mapper.ProductMapper;
import com.example.handwork.service.mapper.TranslatorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.handwork.status.AppStatusMessage.*;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final TranslatorMapper translatorMapper;
    private final CategoryRepository categoryRepository;
    private final S3File s3File;

    @Override
    public ResponseDto<ProductDto> addProduct(ProductInputDto productInputDto) {
        try {
            Product product = new Product();


            product.setCategory(categoryRepository.findById(productInputDto.getCategory()).get());
            product.setImage(s3File.postFile(productInputDto.getImage()));
            product.setDescription(translatorMapper.toEntity(productInputDto.getDescription()));
            product.setAmount(productInputDto.getAmount());
            product.setName(translatorMapper.toEntity(productInputDto.getName()));
            product.setPrice(productInputDto.getPrice());

            productRepository.save(product);

            return ResponseDto.<ProductDto>builder()
                    .message(OK)
                    .success(true)
                    .data(productMapper.toDto(product))
                    .build();
        } catch (Exception e){
            return ResponseDto.<ProductDto>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }

    }

    @Override
    public ResponseDto<ProductDto> updateProduct(ProductInputDto productInputDto) {
        if (productInputDto.getId() == null) {
            return ResponseDto.<ProductDto>builder()
                    .message(NULL_ID)
                    .build();
        }
        try {
            Optional<Product> byId = productRepository.findById(productInputDto.getId());

            if (byId.isEmpty()){
                return ResponseDto.<ProductDto>builder()
                        .message(NOT_FOUND)
                        .build();
            }

            Product product = byId.get();

            if (productInputDto.getImage() != null){
                product.setImage(s3File.postFile(productInputDto.getImage()));
            }

            product.setName(productInputDto.getName() != null ? translatorMapper.toEntity(productInputDto.getName()) : product.getName());
            product.setDescription(productInputDto.getDescription() != null ? translatorMapper.toEntity(productInputDto.getDescription()) : product.getDescription());
            product.setAmount(productInputDto.getAmount() != null ? productInputDto.getAmount() : product.getAmount());
            product.setCategory(productInputDto.getCategory() != null ? categoryRepository.findById(productInputDto.getId()).get() : product.getCategory());
            product.setPrice(productInputDto.getPrice() != null ? productInputDto.getPrice() : product.getPrice());

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

    @Override
    public ResponseDto<ProductDto> delete(Integer id) {
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