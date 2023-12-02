package com.handiwork.service.mapper;

import com.handiwork.dto.ContactDto;
import com.handiwork.entity.Contact;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ContactMapper implements CommonMapper<ContactDto, Contact>{
    @Override
    public abstract ContactDto toDto(Contact contact);

    @Override
    public abstract Contact toEntity(ContactDto contactDto);
}
