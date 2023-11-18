package com.example.handwork.service.impl;

import com.example.handwork.dto.ResponseDto;
import com.example.handwork.dto.ContactDto;
import com.example.handwork.repository.ContactRepository;
import com.example.handwork.service.ContactService;
import com.example.handwork.service.mapper.ContactMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.handwork.status.AppStatusMessage.*;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
    private final ContactRepository repository;
    private final ContactMapper mapper;

    @Override
    public ResponseDto<ContactDto> add(ContactDto contactDto) {
        try {
            repository.save(mapper.toEntity(contactDto));

            return ResponseDto.<ContactDto>builder()
                    .message(OK)
                    .success(true)
                    .data(contactDto)
                    .build();
        } catch (Exception e){
            return ResponseDto.<ContactDto>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<List<ContactDto>> getAll() {
        try {
            return ResponseDto.<List<ContactDto>>builder()
                    .message(OK)
                    .success(true)
                    .data(repository.findAll().stream().map(mapper::toDto).toList())
                    .build();
        } catch (Exception e){
            return ResponseDto.<List<ContactDto>>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }
}
