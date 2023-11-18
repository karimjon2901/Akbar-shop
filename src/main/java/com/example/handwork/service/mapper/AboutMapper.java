package com.example.handwork.service.mapper;

import com.example.handwork.dto.AboutDto;
import com.example.handwork.entity.About;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AboutMapper extends CommonMapper<AboutDto, About>{
}
