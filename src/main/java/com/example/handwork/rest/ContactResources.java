package com.example.handwork.rest;

import com.example.handwork.dto.ContactDto;
import com.example.handwork.dto.ResponseDto;
import com.example.handwork.service.ContactService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contact")
@RequiredArgsConstructor
public class ContactResources {
    private final ContactService service;


    @PostMapping
    public ResponseDto<ContactDto> add(@RequestBody ContactDto contactDto){
        return service.add(contactDto);
    }

    @SecurityRequirement(name = "Authorization")
    @GetMapping
    public ResponseDto<List<ContactDto>> getAll(){
        return service.getAll();
    }

    @PutMapping
    public ResponseDto<ContactDto> update(@RequestBody ContactDto contactDto){
        return service.update(contactDto);
    }

    @GetMapping("/{id}")
    public ResponseDto<?> getById(@PathVariable Integer id){
        return service.getById(id);
    }
    @DeleteMapping("/{id}")
    public ResponseDto<?> delete(@PathVariable Integer id){
        return service.delete(id);
    }
}
