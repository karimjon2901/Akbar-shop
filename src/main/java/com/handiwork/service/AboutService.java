package com.handiwork.service;

import com.handiwork.dto.ResponseDto;
import com.handiwork.dto.AboutDto;

public interface AboutService {
    ResponseDto<AboutDto> update(AboutDto aboutDto);

    ResponseDto<AboutDto> get();
}
