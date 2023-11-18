package com.example.handwork.service.mapper;

import com.example.handwork.dto.CartDto;
import org.mapstruct.Mapper;
import com.example.handwork.entity.Cart;

@Mapper(componentModel = "spring")
public interface CartMapper extends CommonMapper<CartDto, Cart> {
}