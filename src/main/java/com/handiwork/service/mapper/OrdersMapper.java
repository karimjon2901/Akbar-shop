package com.handiwork.service.mapper;

import com.handiwork.dto.OrdersDto;
import com.handiwork.entity.Orders;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class OrdersMapper implements CommonMapper<OrdersDto, Orders>{
    @Override
    public abstract OrdersDto toDto(Orders orders);

    @Override
    public abstract Orders toEntity(OrdersDto ordersDto);
}
