package com.example.handwork.service.mapper;

import com.example.handwork.dto.TranslatorDto;
import com.example.handwork.entity.Translator;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TranslatorMapper extends CommonMapper<TranslatorDto, Translator>{
}
