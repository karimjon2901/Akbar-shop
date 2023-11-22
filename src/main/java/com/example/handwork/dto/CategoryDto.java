package com.example.handwork.dto;

import com.example.handwork.status.AppStatusMessage;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private Integer id;
    private CategoryDto parentId;
    @NotNull(message = AppStatusMessage.NULL_VALUE)
    private TranslatorDto name;
    @NotNull(message = AppStatusMessage.NULL_VALUE)
    private TranslatorDto description;
}
