package com.handiwork.service.mapper;

import com.handiwork.dto.UsersDto;
import com.handiwork.entity.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public abstract class UsersMapper implements CommonMapper<UsersDto, Users>{
    @Autowired
    protected PasswordEncoder passwordEncoder;
    @Override
    public abstract UsersDto toDto(Users users);

    @Override
    @Mapping(target = "password", expression = "java(passwordEncoder.encode(usersDto.getPassword()))")
    public abstract Users toEntity(UsersDto usersDto);
}
