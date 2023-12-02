package com.handiwork.service;

import com.handiwork.dto.ContactDto;
import com.handiwork.dto.ResponseDto;

import java.util.List;

public interface ContactService {
    ResponseDto<ContactDto> add(ContactDto contactDto);

    ResponseDto<List<ContactDto>> getAll();
    ResponseDto<ContactDto> update(ContactDto contactDto);
    ResponseDto<?> delete(String id);
    ResponseDto<?> getById(String id);
}
