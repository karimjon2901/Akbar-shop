package com.handiwork.controller;

import com.handiwork.dto.LoginDto;
import com.handiwork.dto.ResponseDto;
import com.handiwork.dto.UsersDto;
import com.handiwork.service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UsersResources {
    private final UsersService usersService;

//    @Operation(
//            method = "Add new user",
//            description = "Need to send UsersDto to this endpoint to create new user",
//            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Users info",
//                    content = @Content(mediaType = "application/json"))
//    )
//    @PostMapping
//    public ResponseDto<String> add(@RequestBody UsersDto usersDto){
//        return usersService.add(usersDto);
//    }

    @Operation(
            method = "Update user",
            description = "Need to send UsersDto to this endpoint to update user",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Users info",
                    content = @Content(mediaType = "application/json"))
    )
    @PatchMapping
    public ResponseDto<UsersDto> updateUser(@Valid @RequestBody UsersDto usersDto){
        return usersService.update(usersDto);
    }

    @Operation(
            method = "Login user",
            description = "Need to send username and password to this endpoint to login user. You can get token.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Users info",
                    content = @Content(mediaType = "application/json"))
    )
    @PostMapping("/login")
    public ResponseDto<String> loginUser(@Valid @RequestBody LoginDto loginDto) throws NoSuchMethodException {
        return usersService.loginUser(loginDto);
    }

//    @Operation(
//            method = "Get all user",
//            description = "This endpoint return all users",
//            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Users info",
//                    content = @Content(mediaType = "application/json"))
//    )
//    @GetMapping
//    public ResponseDto<List<UsersDto>> getAll(){
//        return usersService.getAll();
//    }
}
