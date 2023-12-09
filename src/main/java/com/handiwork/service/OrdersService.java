package com.handiwork.service;

import com.handiwork.dto.OrdersDto;
import com.handiwork.dto.OrdersInputDto;
import com.handiwork.dto.ResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrdersService {
    ResponseDto<OrdersDto> add(OrdersInputDto ordersInputDto);

    ResponseDto<OrdersDto> getById(String id);

    ResponseDto<Page<OrdersDto>> getAll(Integer size, Integer page);

    ResponseDto<OrdersDto> update(OrdersInputDto ordersInputDto);

    ResponseDto<OrdersDto> delete(String id);
}
