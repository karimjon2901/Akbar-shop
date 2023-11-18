package com.example.handwork.service;

import com.example.handwork.dto.ResponseDto;
import com.example.handwork.dto.AboutDto;

public interface AboutService {
    ResponseDto<AboutDto> update(AboutDto aboutDto);

    ResponseDto<AboutDto> get();
}
