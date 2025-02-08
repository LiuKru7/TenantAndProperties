package com.TenantAndProperties.controller;

import com.TenantAndProperties.dto.PropertyDTO;
import com.TenantAndProperties.dto.TenantDTO;
import com.TenantAndProperties.service.TenantAndPropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/tenant/{propertyId}")
    public ResponseEntity<TenantDTO> addTenant(@PathVariable Long propertyId, @RequestBody TenantDTO tenantDTO){
        final TenantDTO tenant = service.addTenant(propertyId, tenantDTO);
        return new ResponseEntity<>(tenant, HttpStatus.OK);
    }
    @GetMapping("/property")
    public ResponseEntity<List<PropertyDTO>> getAllProperties(){
        final List<PropertyDTO> propertyDTOS = service.getAllProperties();
        return new ResponseEntity<>(propertyDTOS, HttpStatus.OK);
    }
    @GetMapping("/tenant")
    public ResponseEntity<List<TenantDTO>> getAllTenants(){
        final List<TenantDTO> tenantDTOS = service.getAllTenants();
        return new ResponseEntity<>(tenantDTOS, HttpStatus.OK);
    }




}
