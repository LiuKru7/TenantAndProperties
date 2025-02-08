package com.TenantAndProperties.controller;

import com.TenantAndProperties.dto.PropertyDTO;
import com.TenantAndProperties.model.Property;
import com.TenantAndProperties.service.TenantAndPropertyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class TenantAndPropertyController {

    private final TenantAndPropertyService service;


    @PostMapping("/property")
    public ResponseEntity<PropertyDTO> addProperty(@RequestBody PropertyDTO propertyDTO) {
        final PropertyDTO property = service.addProperty(propertyDTO);
        return new ResponseEntity<>(property, HttpStatus.OK);
    }

}
