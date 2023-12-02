package com.handiwork.dto;

import com.handiwork.status.AppStatusMessage;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private String id;
    private CategoryDto parentId;
    @NotNull(message = AppStatusMessage.NULL_VALUE)
    private TranslatorDto name;
    @NotNull(message = AppStatusMessage.NULL_VALUE)
    private TranslatorDto description;
}
