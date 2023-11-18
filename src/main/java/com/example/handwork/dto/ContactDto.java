package com.example.handwork.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactDto {
    private Integer id;
    private String location;
    private String phoneNumber;
    private String email;
}
