package com.handiwork.service.mapper;

import com.handiwork.dto.ProductOrderDto;
import com.handiwork.entity.ProductOrder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ProductOrderMapper implements CommonMapper<ProductOrderDto, ProductOrder>{
    @Override
    public abstract ProductOrderDto toDto(ProductOrder productOrder);

    @Override
    public abstract ProductOrder toEntity(ProductOrderDto productOrderDto);
}
