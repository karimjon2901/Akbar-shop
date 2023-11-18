package com.example.handwork.rest;

import com.example.handwork.dto.CategoryDto;
import com.example.handwork.dto.ResponseDto;
import com.example.handwork.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryResources {
    private final CategoryService categoryService;

    @Operation(
            method = "Add new category",
            description = "Need to send category to this endpoint to create new category",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Category info",
                    content = @Content(mediaType = "application/json")),
            summary = "add"
    )
    @SecurityRequirement(name = "Authorization")
    @PostMapping
    public ResponseDto<CategoryDto> add(@Valid @RequestBody CategoryDto categoryDto){
        return categoryService.addCategory(categoryDto);
    }

    @Operation(
            method = "Get all category",
            description = "This endpoint return all category",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Category info",
                    content = @Content(mediaType = "application/json")),
            summary = "Get all"
    )
    @GetMapping
    public ResponseDto<List<CategoryDto>> getAll(){
        return categoryService.getAll();
    }
    @Operation(
            method = "Get category by id",
            description = "Need to send id to this endpoint to get category",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Category info",
                    content = @Content(mediaType = "application/json")),
            summary = "Get by id"
    )
    @GetMapping("/{id}")
    public ResponseDto<CategoryDto> getById(@PathVariable Integer id){
        return categoryService.getById(id);
    }

    @Operation(
            method = "Delete category",
            description = "Need to send category id to this endpoint to delete this category",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Category info",
                    content = @Content(mediaType = "application/json")),
            summary = "Delete"
    )
    @SecurityRequirement(name = "Authorization")
    @DeleteMapping("/{id}")
    public ResponseDto<Void> delete(@PathVariable Integer id){
        return categoryService.delete(id);
    }

    @Operation(
            method = "Update category",
            description = "Need to send CategoryDto to this endpoint to update category",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Category info",
                    content = @Content(mediaType = "application/json")),
            summary = "Update"
    )
    @SecurityRequirement(name = "Authorization")
    @PatchMapping
    public ResponseDto<CategoryDto> update(@Valid @RequestBody CategoryDto categoryDto){
        return categoryService.update(categoryDto);
    }
}