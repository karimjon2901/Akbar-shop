package com.example.handwork.rest;


import com.example.handwork.dto.ResponseDto;
import com.example.handwork.dto.AboutDto;
import com.example.handwork.service.AboutService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/about")
@RequiredArgsConstructor
public class AboutResources {
    private final AboutService aboutService;
    @SecurityRequirement(name = "Authorization")
    @Operation(
            method = "Update about of school",
            description = "Need to send AboutOfSchoolDto to this endpoint to update",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "AboutOfSchool info",
                    content = @Content(mediaType = "application/json")),
            summary = "Update"
    )
    @PostMapping
    public ResponseDto<AboutDto> update(@Valid @RequestBody AboutDto aboutDto){
        return aboutService.update(aboutDto);
    }

    @Operation(
            method = "Get about of school",
            description = "This endpoint return aboutOfSchoolDto",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "AboutOfSchool info",
                    content = @Content(mediaType = "application/json")),
            summary = "Get information"
    )
    @GetMapping
    public ResponseDto<AboutDto> get(){
        return aboutService.get();
    }
}
