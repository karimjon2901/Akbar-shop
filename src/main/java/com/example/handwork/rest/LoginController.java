package com.example.handwork.rest;

import com.example.handwork.dto.ResponseDto;
import com.example.handwork.dto.UserDto;
import com.example.handwork.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;
    @PostMapping
    public ResponseDto<String> login(@RequestBody UserDto userDto){
        SecurityContextHolder.getContext().getAuthentication();
        return  loginService.login(userDto);
    }
}
