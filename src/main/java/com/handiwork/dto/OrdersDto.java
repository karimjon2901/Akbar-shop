package com.handiwork.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDto {
    private String id;
    private List<ProductOrderDto> products;
    private Double totalPrice;
}
