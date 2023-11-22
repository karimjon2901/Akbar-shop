package com.example.handwork.service;

import com.example.handwork.dto.LoginDto;
import com.example.handwork.dto.ResponseDto;
import com.example.handwork.dto.UsersDto;

import java.util.List;

public interface UsersService {
    ResponseDto<String> add(UsersDto usersDto);
    ResponseDto<List<UsersDto>> getAll();
    ResponseDto<UsersDto> update(UsersDto usersDto);

    ResponseDto<String> loginUser(LoginDto loginDto);
}
