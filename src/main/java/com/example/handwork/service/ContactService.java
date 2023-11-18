package com.example.handwork.service;

import com.example.handwork.dto.ContactDto;
import com.example.handwork.dto.ResponseDto;

import java.util.List;

public interface ContactService {
    ResponseDto<ContactDto> add(ContactDto contactDto);

    ResponseDto<List<ContactDto>> getAll();
}
