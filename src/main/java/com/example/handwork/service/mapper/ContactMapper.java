package com.example.handwork.service.mapper;

import com.example.handwork.dto.ContactDto;
import com.example.handwork.entity.Contact;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ContactMapper implements CommonMapper<ContactDto, Contact>{
    @Override
    public abstract ContactDto toDto(Contact contact);

    @Override
    public abstract Contact toEntity(ContactDto contactDto);
}
