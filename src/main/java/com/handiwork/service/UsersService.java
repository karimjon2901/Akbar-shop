package com.handiwork.service;

import com.handiwork.dto.LoginDto;
import com.handiwork.dto.ResponseDto;
import com.handiwork.dto.UsersDto;

import java.util.List;

public interface UsersService {
    ResponseDto<String> add(UsersDto usersDto);
    ResponseDto<List<UsersDto>> getAll();
    ResponseDto<UsersDto> update(UsersDto usersDto);

    ResponseDto<String> loginUser(LoginDto loginDto);
}
