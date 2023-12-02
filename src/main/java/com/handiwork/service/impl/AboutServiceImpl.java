package com.handiwork.service.impl;

import com.handiwork.dto.AboutDto;
import com.handiwork.dto.ResponseDto;
import com.handiwork.entity.About;
import com.handiwork.repository.AboutRepository;
import com.handiwork.service.AboutService;
import com.handiwork.service.mapper.AboutMapper;
import com.handiwork.service.mapper.TranslatorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.handiwork.status.AppStatusMessage.*;

@Service
@RequiredArgsConstructor
public class AboutServiceImpl implements AboutService {
    private final AboutRepository repository;
    private final AboutMapper mapper;
    private final TranslatorMapper translatorMapper;

    @Override
    public ResponseDto<AboutDto> update(AboutDto aboutDto) {
        try {
            Optional<About> byId = repository.findById(1);

            if (byId.isEmpty()){
                repository.save(mapper.toEntity(aboutDto));
            }

            About about = byId.get();

            if (aboutDto.getTitle() != null) about.setTitle(translatorMapper.toEntity(aboutDto.getTitle()));
            if (aboutDto.getDescription() != null) about.setDescription(translatorMapper.toEntity(aboutDto.getDescription()));

            repository.save(about);

            return ResponseDto.<AboutDto>builder()
                    .success(true)
                    .message(OK)
                    .data(mapper.toDto(about))
                    .build();
        } catch (Exception e){
            return ResponseDto.<AboutDto>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }


    @Override
    public ResponseDto<AboutDto> get() {
        try {
            Optional<About> byId = repository.findById(1);
            if (byId.isEmpty()){
                return ResponseDto.<AboutDto>builder()
                        .message(NOT_FOUND)
                        .build();
            }
            return ResponseDto.<AboutDto>builder()
                    .success(true)
                    .message(OK)
                    .data(mapper.toDto(byId.get()))
                    .build();
        } catch (Exception e){
            return ResponseDto.<AboutDto>builder()
                    .message(e.getMessage())
                    .build();
        }
    }
}
