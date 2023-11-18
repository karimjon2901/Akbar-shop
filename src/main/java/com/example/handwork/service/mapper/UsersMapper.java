package com.example.handwork.service.mapper;

import com.example.handwork.dto.UsersDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.example.handwork.entity.Users;

@Mapper(componentModel = "spring")
public abstract class UsersMapper implements CommonMapper<UsersDto, Users>{
    @Override
    @Mapping(target = "isActive", expression = "java((short) 1)")
    @Mapping(target = "enabled",expression = "java(true)")
    // @Mapping(target= "role",expression = "java(\"USER\")")
    abstract public Users toEntity(UsersDto dto);
    @Override
    abstract public UsersDto toDto(Users entity);

    abstract public Users toEntityPassword(UsersDto usersDto);
}