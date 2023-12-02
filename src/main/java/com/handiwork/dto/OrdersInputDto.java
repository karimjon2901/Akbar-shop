package com.handiwork.dto;

import com.handiwork.status.AppStatusMessage;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrdersInputDto {
    private String id;
    @NotNull(message = AppStatusMessage.NULL_VALUE)
    private List<ProductOrderDto> products;
}