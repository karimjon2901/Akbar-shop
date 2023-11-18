package com.example.handwork.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.handwork.dto.ResponseDto;
import com.example.handwork.dto.UsersDto;
import com.example.handwork.entity.Users;
import com.example.handwork.repository.UsersRepository;
import com.example.handwork.service.CartService;
import com.example.handwork.service.UsersService;
import com.example.handwork.service.mapper.UsersMapper;

import java.util.Optional;

import static com.example.handwork.status.AppStatusMessage.*;
@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final UsersMapper userMapper;
    private final UsersRepository usersRepository;
    private final CartService cartService;
    @Override
    public ResponseDto<UsersDto> addUser(UsersDto dto) {
        try {
            Users users = userMapper.toEntity(dto);
            usersRepository.save(users);
            cartService.createCart(users);
            return ResponseDto.<UsersDto>builder()
                    .success(true)
                    .data(userMapper.toDto(users))
                    .message(OK)
                    .build();
        } catch (Exception e) {
            return ResponseDto.<UsersDto>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<UsersDto> updateUser(UsersDto usersDto) {
        if (usersDto.getId() == null){
            return ResponseDto.<UsersDto>builder()
                    .message(NULL_VALUE)
                    .data(usersDto)
                    .build();
        }
        try {
            Optional<Users> userOptional = usersRepository.findByIdAndIsActive(usersDto.getId(), (short) 1);

            if (userOptional.isEmpty()) {
                return ResponseDto.<UsersDto>builder()
                        .message(NOT_FOUND)
                        .data(usersDto)
                        .build();
            }
            Users user = userOptional.get();
            if (usersDto.getFirstName() != null) {
                user.setFirstName(usersDto.getFirstName());
            }
            if (usersDto.getLastName() != null) {
                user.setLastName(usersDto.getLastName());
            }
            usersRepository.save(user);

            return ResponseDto.<UsersDto>builder()
                    .data(userMapper.toDto(user))
                    .success(true)
                    .message(OK)
                    .build();
        }catch (Exception e){
            return ResponseDto.<UsersDto>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<UsersDto> getUserByPhoneNumber(String phoneNumber) {
        try {
            return usersRepository.findFirstByPhoneNumberAndIsActive(phoneNumber, (short) 1)
                    .map(u -> ResponseDto.<UsersDto>builder()
                            .data(userMapper.toDto(u))
                            .success(true)
                            .message(OK)
                            .build())
                    .orElse(ResponseDto.<UsersDto>builder()
                            .message(NOT_FOUND)
                            .build());
        }catch (Exception e){
            return ResponseDto.<UsersDto>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<UsersDto> getById(Integer id) {
        try {
            return usersRepository.findByIdAndIsActive(id, (short) 1)
                    .map(u -> ResponseDto.<UsersDto>builder()
                            .data(userMapper.toDto(u))
                            .success(true)
                            .message(OK)
                            .build())
                    .orElse(ResponseDto.<UsersDto>builder()
                            .message(NOT_FOUND)
                            .build());
        }catch (Exception e){
            return ResponseDto.<UsersDto>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }
    @Override
    public ResponseDto<UsersDto> deleteUser(Integer id) {
        try {
            Optional<Users> user=usersRepository.findByIdAndIsActive(id,(short)1);
            if(user.isEmpty()) {
                return (ResponseDto.<UsersDto>builder()
                        .message(NOT_FOUND)
                        .build());
            }
            Users delUser= user.get();
            delUser.setIsActive((short) 0);
            usersRepository.save(delUser);
            return ResponseDto.<UsersDto>builder()
                    .success(true)
                    .message(OK)
                    .data(userMapper.toDto(delUser))
                    .build();

        }catch (Exception e){
            return ResponseDto.<UsersDto>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }
}