package com.handiwork.service.mapper;

import com.handiwork.dto.TranslatorDto;
import com.handiwork.entity.Translator;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TranslatorMapper extends CommonMapper<TranslatorDto, Translator>{
}
