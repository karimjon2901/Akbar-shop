package com.example.handwork.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.handwork.dto.CartDto;
import com.example.handwork.dto.ProductDto;
import com.example.handwork.dto.ResponseDto;
import com.example.handwork.service.impl.CartServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartResources {
    private final CartServiceImpl cartService;
    @GetMapping("/{id}")
    public ResponseDto<List<ProductDto>> getCartUser(@PathVariable Integer id){
        return cartService.getUserCart(id);
    }
    @PostMapping
    public ResponseDto<CartDto> addToCart(@RequestParam Integer cartId, @RequestParam Integer productId){
        return cartService.addToCart(cartId, productId);
    }
}