package com.example.handwork.service.impl;

import com.example.handwork.config.JwtService;
import com.example.handwork.dto.LoginDto;
import com.example.handwork.dto.ResponseDto;
import com.example.handwork.dto.UsersDto;
import com.example.handwork.entity.Users;
import com.example.handwork.repository.UsersRepository;
import com.example.handwork.service.UsersService;
import com.example.handwork.service.mapper.UsersMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.handwork.status.AppStatusMessage.*;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;
    private final UsersMapper usersMapper;
    private final JwtService jwtService;
    private final PasswordEncoder encoder;
    @Override
    public ResponseDto<String> add(UsersDto usersDto) {
        try{
            Optional<Users> users = usersRepository.findFirstByUsername(usersDto.getUsername());
            if (users.isEmpty()){
                Users user = usersMapper.toEntity(usersDto);
                usersRepository.save(user);

                return ResponseDto.<String >builder()
                        .message(OK)
                        .success(true)
                        .data(jwtService.generateToken(user))
                        .build();
            }
            else {
                return ResponseDto.<String>builder()
                        .message("This username already exists!")
                        .build();
            }

        }catch (Exception e){
            return ResponseDto.<String>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<List<UsersDto>> getAll() {
        try{
            return ResponseDto.<List<UsersDto>>builder()
                    .message(OK)
                    .success(true)
                    .data(usersRepository.findAll().stream().map(usersMapper::toDto).toList())
                    .build();
        }catch (Exception e){
            return ResponseDto.<List<UsersDto>>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<UsersDto> update(UsersDto usersDto) {


        Optional<Users> userOptional = usersRepository.findFirstByUsername(usersDto.getUsername());

        if (userOptional.isEmpty()){
            return ResponseDto.<UsersDto>builder()
                    .message(NOT_FOUND)
                    .data(usersDto)
                    .build();
        }
        Users user = userOptional.get();
        if (usersDto.getPassword() != null) {
            user.setPassword(usersDto.getPassword());
        }
        if (usersDto.getUsername() != null) {
            Optional<Users> user1 = usersRepository.findFirstByUsername(usersDto.getUsername());
            if (user1.isEmpty()){
                user.setUsername(usersDto.getUsername());
            }
            else {
                return ResponseDto.<UsersDto>builder()
                        .message("This username already exists!")
                        .build();
            }
        }

        try {
            usersRepository.save(user);

            return ResponseDto.<UsersDto>builder()
                    .data(usersMapper.toDto(user))
                    .success(true)
                    .message(OK)
                    .build();
        }catch (Exception e){
            return ResponseDto.<UsersDto>builder()
                    .data(usersMapper.toDto(user))
                    .message(DATABASE_ERROR + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<String> loginUser(LoginDto loginDto) {
        Users users = loadUserByUsername(loginDto.getUsername());
        if (!encoder.matches(loginDto.getPassword(),users.getPassword())){
            return ResponseDto.<String>builder()
                    .message("Password is not correct "+users.getPassword())
                    .build();
        }

        return ResponseDto.<String>builder()
                .success(true)
                .message(OK)
                .data(jwtService.generateToken(users))
                .build();
    }

    private Users loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> users = usersRepository.findFirstByUsername(username);
        if (users.isEmpty()) throw new UsernameNotFoundException("User with username: " + username + " is not found");
        return users.get();
    }
}