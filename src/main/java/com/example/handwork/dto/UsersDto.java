package com.example.handwork.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersDto{
    private Integer id;
    private String phoneNumber;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}