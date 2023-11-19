package com.example.handwork.service;

import com.example.handwork.dto.ResponseDto;
import com.example.handwork.dto.UserDto;
import com.example.handwork.jdbc.JdbcConnector;
import com.example.handwork.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final JdbcConnector jdbcConnector;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public ResponseDto<String> login(UserDto userDto) {
        UserDto user = jdbcConnector.getUser(userDto.getUsername());
        if (user.getUsername() != null) {
            if (!passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
                return ResponseDto.<String>builder()
                        .message("Password is not correct")
                        .build();
            }
            return ResponseDto.<String>builder()
                    .success(true)
                    .message("OK")
                    .data(jwtService.genereteToken(user))
                    .build();
        }
        else{
            return ResponseDto.<String>builder()
                    .message("User with " + userDto.getUsername() + " not found")
                    .build();
        }
    }
}
