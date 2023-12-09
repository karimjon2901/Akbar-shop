package com.handiwork.dto;

import com.handiwork.status.AppStatusMessage;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private String id;
    @NotNull(message = AppStatusMessage.NULL_VALUE)
    private String img;
    @NotNull(message = AppStatusMessage.NULL_VALUE)
    private TranslatorDto name;
    @NotNull(message = AppStatusMessage.NULL_VALUE)
    private Double price;
    @NotNull(message = AppStatusMessage.NULL_VALUE)
    private Integer amount;
    @NotNull(message = AppStatusMessage.NULL_VALUE)
    private TranslatorDto product_info;
    @NotNull(message = AppStatusMessage.NULL_VALUE)
    private String category_id;
    @NotNull(message = AppStatusMessage.NULL_VALUE)
    private TranslatorDto status;
    private Integer assessments;
    @NotNull(message = AppStatusMessage.NULL_VALUE)
    private TagTranslatorDto tags;
}