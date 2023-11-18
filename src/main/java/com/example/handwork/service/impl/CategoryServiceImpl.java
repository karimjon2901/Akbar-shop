package com.example.handwork.service.impl;

import com.example.handwork.dto.CategoryDto;
import com.example.handwork.dto.ResponseDto;
import com.example.handwork.entity.Category;
import com.example.handwork.repository.CategoryRepository;
import com.example.handwork.service.CategoryService;
import com.example.handwork.service.mapper.CategoryMapper;
import com.example.handwork.service.mapper.TranslatorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.handwork.status.AppStatusMessage.*;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final TranslatorMapper translatorMapper;
    @Override
    public ResponseDto<CategoryDto> addCategory(CategoryDto categoryDto) {
        try {
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
    public ResponseDto<CategoryDto> getById(Integer id) {
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
    public ResponseDto<Void> delete(Integer id) {
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
            categoryRepository.deleteById(id);
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
}