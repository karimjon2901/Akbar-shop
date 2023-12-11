package com.handiwork.service.impl;

import com.handiwork.dto.CategoryDto;
import com.handiwork.dto.ResponseDto;
import com.handiwork.entity.Category;
import com.handiwork.entity.Product;
import com.handiwork.repository.CategoryRepository;
import com.handiwork.repository.ProductRepository;
import com.handiwork.service.CategoryService;
import com.handiwork.service.IdGenerator;
import com.handiwork.service.mapper.CategoryMapper;
import com.handiwork.service.mapper.TranslatorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.handiwork.status.AppStatusMessage.*;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final TranslatorMapper translatorMapper;
    private final IdGenerator idGenerator;
    private final ProductRepository productRepository;
    @Override
    public ResponseDto<CategoryDto> addCategory(CategoryDto categoryDto) {
        try {
            categoryDto.setId(idGenerator.generate());
            return ResponseDto.<CategoryDto>builder()
                    .data(categoryMapper.toDto(
                            categoryRepository.save(
                                    categoryMapper.toEntity(categoryDto)
                            )
                    ))
                    .message(OK)
                    .success(true)
                    .build();
        }catch (Exception e){
            return ResponseDto.<CategoryDto>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .data(categoryDto)
                    .build();
        }
    }

    @Override
    public ResponseDto<CategoryDto> getById(String id) {
        if (id == null){
            return ResponseDto.<CategoryDto>builder()
                    .message(NULL_ID)
                    .build();
        }
        try {
            return categoryRepository.findById(id)
                    .map(u -> ResponseDto.<CategoryDto>builder()
                            .data(categoryMapper.toDto(u))
                            .success(true)
                            .message(OK)
                            .build())
                    .orElse(ResponseDto.<CategoryDto>builder()
                            .message(NOT_FOUND)
                            .build());
        }catch (Exception e){
            return ResponseDto.<CategoryDto>builder()
                    .message(e.getMessage())
                    .success(true)
                    .build();
        }
    }

    @Override
    public ResponseDto<List<CategoryDto>> getAll() {
        try{
            return ResponseDto.<List<CategoryDto>>builder()
                    .message(OK)
                    .success(true)
                    .data(categoryRepository.findAll().stream().map(categoryMapper::toDto).toList())
                    .build();
        }catch (Exception e){
            return ResponseDto.<List<CategoryDto>>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<Void> delete(String id) {
        if (id == null){
            return ResponseDto.<Void>builder()
                    .message(NULL_ID)
                    .build();
        }
        try {
            Optional<Category> byId = categoryRepository.findById(id);
            if (byId.isEmpty()){
                return ResponseDto.<Void>builder()
                        .message(NOT_FOUND)
                        .build();
            }
            deleteCategory(id);
            return ResponseDto.<Void>builder()
                    .message(OK)
                    .success(true)
                    .build();
        }catch (Exception e){
            return ResponseDto.<Void>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<CategoryDto> update(CategoryDto categoryDto) {
        if (categoryDto.getId() == null){
            return ResponseDto.<CategoryDto>builder()
                    .message(NULL_ID)
                    .build();
        }
        try {
            Optional<Category> byId = categoryRepository.findById(categoryDto.getId());
            if (byId.isEmpty()){
                return ResponseDto.<CategoryDto>builder()
                        .message(NOT_FOUND)
                        .build();
            }
            Category category = byId.get();

            if (categoryDto.getName() != null) category.setName(translatorMapper.toEntity(categoryDto.getName()));
            if (categoryDto.getDescription() != null) category.setDescription(translatorMapper.toEntity(categoryDto.getDescription()));

            categoryRepository.save(category);
            return ResponseDto.<CategoryDto>builder()
                    .message(OK)
                    .success(true)
                    .build();
        }catch (Exception e){
            return ResponseDto.<CategoryDto>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }
    private void deleteCategory(String categoryId) {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        if (category != null) {
            List<Product> products = productRepository.findAllByCategoryId(category.getId());
            products.forEach(product -> product.setCategory(null));
            productRepository.saveAll(products);
            categoryRepository.delete(category);
        }
    }
}