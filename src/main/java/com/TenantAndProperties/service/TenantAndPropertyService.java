package com.TenantAndProperties.service;

import com.TenantAndProperties.dto.PropertyDTO;
import com.TenantAndProperties.dto.TenantDTO;
import com.TenantAndProperties.repository.PropertyRepository;
import com.TenantAndProperties.repository.TenantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TenantAndPropertyService {


    PropertyDTO addProperty(PropertyDTO propertyDTO);

    TenantDTO addTenant(Long propertyId, TenantDTO tenantDTO);

    List<PropertyDTO> getAllProperties();

    List<TenantDTO> getAllTenants();
}
