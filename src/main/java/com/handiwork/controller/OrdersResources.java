package com.handiwork.controller;

import com.handiwork.dto.OrdersDto;
import com.handiwork.dto.OrdersInputDto;
import com.handiwork.dto.ResponseDto;
import com.handiwork.service.OrdersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrdersResources {
    private final OrdersService ordersService;

    @Operation(
            method = "Add new order",
            description = "Need to send OrderDto to this endpoint to create new order",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Order info",
                    content = @Content(mediaType = "application/json"))
    )
    @PostMapping
    public ResponseDto<OrdersDto> add(@Valid @RequestBody OrdersInputDto ordersInputDto){
        return ordersService.add(ordersInputDto);
    }

    @Operation(
            method = "Get order by id",
            description = "Need to send id to this endpoint to get order",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Order info",
                    content = @Content(mediaType = "application/json"))
    )
    @GetMapping("/{id}")
    public ResponseDto<OrdersDto> getById(@PathVariable String id){
        return ordersService.getById(id);
    }

    @Operation(
            method = "Get all orders",
            description = "This endpoint return Page of orders. Need to send size and page to get orders",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Order info",
                    content = @Content(mediaType = "application/json"))
    )
    @GetMapping
    public ResponseDto<Page<OrdersDto>> getAll(@RequestParam(defaultValue = "10") Integer size,
                                               @RequestParam(defaultValue = "0") Integer page){
        return ordersService.getAll(size, page);
    }

    @Operation(
            method = "Update order",
            description = "Need to send OrderDto to this endpoint to update order",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Order info",
                    content = @Content(mediaType = "application/json"))
    )
    @PatchMapping
    public ResponseDto<OrdersDto> update(@Valid @RequestBody OrdersInputDto ordersInputDto){
        return ordersService.update(ordersInputDto);
    }

    @Operation(
            method = "Delete order by id",
            description = "Need to send id to this endpoint to delete order",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Order info",
                    content = @Content(mediaType = "application/json"))
    )
    @DeleteMapping("/{id}")
    public ResponseDto<OrdersDto> delete(@PathVariable String id){
        return ordersService.delete(id);
    }
}
