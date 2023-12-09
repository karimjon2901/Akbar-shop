package com.handiwork.service.mapper;

import com.handiwork.dto.TagTranslatorDto;
import com.handiwork.entity.Translator;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Arrays;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class TagTranslatorMapper implements CommonMapper<TagTranslatorDto, Translator>{
    @Override
//    @Mapping(target = "uz", expression = "java(toList(translator.getUz()))")
//    @Mapping(target = "en", expression = "java(toList(translator.getEn()))")
//    @Mapping(target = "ru", expression = "java(toList(translator.getRu()))")
    public TagTranslatorDto toDto(Translator translator){
        if ( translator == null ) {
            return null;
        }

        TagTranslatorDto tagTranslatorDto = new TagTranslatorDto();

        tagTranslatorDto.setId( translator.getId() );

        tagTranslatorDto.setUz( toList(translator.getUz()) );
        tagTranslatorDto.setEn( toList(translator.getEn()) );
        tagTranslatorDto.setRu( toList(translator.getRu()) );

        return tagTranslatorDto;
    }

    @Override
//    @Mapping(target = "uz", expression = "java(toStr(tagTranslatorDto.getUz()))")
//    @Mapping(target = "en", expression = "java(toStr(tagTranslatorDto.getEn()))")
//    @Mapping(target = "ru", expression = "java(toStr(tagTranslatorDto.getRu()))")
    public Translator toEntity(TagTranslatorDto tagTranslatorDto){
        if ( tagTranslatorDto == null ) {
            return null;
        }

        Translator translator = new Translator();

        translator.setId( tagTranslatorDto.getId() );

        translator.setUz( toStr(tagTranslatorDto.getUz()) );
        translator.setEn( toStr(tagTranslatorDto.getEn()) );
        translator.setRu( toStr(tagTranslatorDto.getRu()) );

        return translator;
    }

    protected String toStr(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String s: list) sb.append(s).append("/split/");
        return sb.toString();
    }

    protected List<String> toList(String s){
        return Arrays.stream(s.split("/split/")).toList();
    }
}
