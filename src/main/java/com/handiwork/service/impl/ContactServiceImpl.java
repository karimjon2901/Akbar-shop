package com.handiwork.service.impl;

import com.handiwork.dto.ResponseDto;
import com.handiwork.dto.ContactDto;
import com.handiwork.entity.Contact;
import com.handiwork.repository.ContactRepository;
import com.handiwork.service.ContactService;
import com.handiwork.service.mapper.ContactMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.handiwork.status.AppStatusMessage.*;

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
        } catch (Exception e) {
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
        } catch (Exception e) {
            return ResponseDto.<List<ContactDto>>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<ContactDto> update(ContactDto contactDto) {
        if (contactDto.getId() == null) {
            return ResponseDto.<ContactDto>builder()
                    .message(NULL_ID)
                    .build();
        }
        try {
            Optional<Contact> optional = repository.findById(contactDto.getId());

            if (optional.isEmpty()) {
                return ResponseDto.<ContactDto>builder()
                        .message(NOT_FOUND)
                        .build();
            }

            Contact contact = optional.get();
            contact.setMessage(contactDto.getMessage() != null ? mapper.toEntity(contactDto).getMessage() : contact.getMessage());
            contact.setEmail(contactDto.getEmail() != null ? mapper.toEntity(contactDto).getEmail() : contact.getEmail());
            contact.setFirstName(contactDto.getFirstName() != null ? mapper.toEntity(contactDto).getFirstName() : contact.getFirstName());
            contact.setLastName(contactDto.getLastName() != null ? mapper.toEntity(contactDto).getLastName() : contact.getLastName());
            contact.setPhoneNumber(contactDto.getPhoneNumber() != null ? mapper.toEntity(contactDto).getPhoneNumber() : contact.getPhoneNumber());

            repository.save(contact);

            return ResponseDto.<ContactDto>builder()
                    .message(OK)
                    .data(mapper.toDto(contact))
                    .success(true)
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ContactDto>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<?> delete(String id) {
        if (id == null) {
            return ResponseDto.builder()
                    .message(NULL_ID)
                    .build();
        }
        Optional<Contact> optional = repository.findById(id);
        if (optional.isEmpty()) {
            return ResponseDto.builder()
                    .message(NOT_FOUND)
                    .build();
        }
        repository.delete(optional.get());
        return ResponseDto.<ContactDto>builder()
                .data(mapper.toDto(optional.get()))
                .message(OK)
                .success(true)
                .build();
    }

    @Override
    public ResponseDto<?> getById(String id) {
        return ResponseDto.builder()
                .success(true)
                .data(repository.findById(id).isPresent() ? repository.findById(id).get() : NOT_FOUND)
                .message(OK)
                .build();
    }
}
