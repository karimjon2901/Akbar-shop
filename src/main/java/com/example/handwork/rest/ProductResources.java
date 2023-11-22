package com.example.handwork.rest;

import com.example.handwork.dto.ProductInputDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.example.handwork.dto.ProductDto;
import com.example.handwork.dto.ResponseDto;
import com.example.handwork.service.ProductService;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductResources {

    private final ProductService productService;

    @Operation(
            method = "Add new product",
            description = "Need to send ProductDto to this endpoint to create new product",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Product info",
                    content = @Content(mediaType = "multipart/form-data")),
            summary = "add"
    )
    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseDto<ProductDto> addProduct(@ModelAttribute @Valid ProductInputDto productInputDto){
        return productService.addProduct(productInputDto);
    }

    @Operation(
            method = "Update product",
            description = "Need to send ProductInputDto to this endpoint to update product",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Product info",
                    content = @Content(mediaType = "multipart/form-data")),
            summary = "Update"
    )
    @PatchMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseDto<ProductDto> updateProduct(@Valid @ModelAttribute ProductInputDto productInputDto){
        return productService.updateProduct(productInputDto);
    }

    @Operation(
            method = "Get all products",
            description = "This endpoint return all products",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Product info",
                    content = @Content(mediaType = "application/json")),
            summary = "Get all"
    )
    @GetMapping
    public ResponseDto<Page<ProductDto>> getAllProducts(@RequestParam(defaultValue = "10") Integer size,
                                                        @RequestParam(defaultValue = "0") Integer page){
        return productService.getAllProducts(page, size);
    }
    @Operation(
            method = "Get product by id",
            description = "Need to send id to this endpoint to get product",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Product info",
                    content = @Content(mediaType = "application/json")),
            summary = "Get by id"
    )
    @GetMapping("/by-id/{id}")
    public ResponseDto<ProductDto> getProductById(@PathVariable Integer id){
        return productService.getProductById(id);
    }

    @Operation(
            method = "Delete product",
            description = "Need to send product id to this endpoint to delete this product",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Product info",
                    content = @Content(mediaType = "application/json")),
            summary = "Delete"
    )
    @DeleteMapping("/{id}")
    public ResponseDto<ProductDto> delete(@PathVariable Integer id){
        return productService.delete(id);
    }

}
