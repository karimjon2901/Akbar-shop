package com.example.handwork.service;

import com.example.handwork.dto.ResponseDto;
import com.example.handwork.dto.UsersDto;

public interface UsersService {
    ResponseDto<UsersDto> addUser(UsersDto dto);
    ResponseDto<UsersDto> updateUser(UsersDto usersDto);
    ResponseDto<UsersDto> getUserByPhoneNumber(String phoneNumber);
    ResponseDto<UsersDto> getById(Integer id);
    ResponseDto<UsersDto> deleteUser(Integer id);
}