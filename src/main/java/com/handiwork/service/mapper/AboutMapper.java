package com.handiwork.service.mapper;

import com.handiwork.dto.AboutDto;
import com.handiwork.entity.About;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AboutMapper extends CommonMapper<AboutDto, About>{
}
