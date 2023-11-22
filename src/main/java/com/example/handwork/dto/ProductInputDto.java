package com.example.handwork.dto;

import com.example.handwork.status.AppStatusMessage;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.nio.channels.MulticastChannel;

import static com.example.handwork.status.AppStatusMessage.EMPTY_STRING;
import static com.example.handwork.status.AppStatusMessage.NEGATIVE_VALUE;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductInputDto {
    private Integer id;
    @NotNull(message = AppStatusMessage.NULL_VALUE)
    private MultipartFile image;
    @NotBlank(message = EMPTY_STRING)
    private TranslatorDto name;
    @Positive(message = NEGATIVE_VALUE)
    private Double price;
    private Integer amount;
    @NotBlank(message = EMPTY_STRING)
    private TranslatorDto description;
    @NotNull(message = EMPTY_STRING)
    private Integer category;
    private Boolean isAvailable;
}