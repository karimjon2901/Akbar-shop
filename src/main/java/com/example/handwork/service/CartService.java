package com.example.handwork.service;

import com.example.handwork.dto.CartDto;
import com.example.handwork.dto.ProductDto;
import com.example.handwork.dto.ResponseDto;
import com.example.handwork.entity.Cart;
import com.example.handwork.entity.Product;
import com.example.handwork.entity.Users;

import java.util.List;

public interface CartService {
    Boolean createCart(Users user);
    ResponseDto<CartDto> addToCart(Integer cartId, Integer productId);
    void removeFromCart(Cart cart, Product product);
    void clearCart(Cart cart);
    ResponseDto<List<ProductDto>> getUserCart(Integer userId);
}