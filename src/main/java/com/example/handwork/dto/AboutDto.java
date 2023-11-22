package com.example.handwork.dto;

import com.example.handwork.status.AppStatusMessage;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AboutDto {
    @NotNull(message = AppStatusMessage.NULL_VALUE)
    private TranslatorDto title;
    @NotNull(message = AppStatusMessage.NULL_VALUE)
    private TranslatorDto description;
}