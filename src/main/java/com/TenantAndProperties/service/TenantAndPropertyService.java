package com.TenantAndProperties.service;

import com.TenantAndProperties.dto.PropertyDTO;
import com.TenantAndProperties.dto.TenantDTO;

import java.util.List;


public interface TenantAndPropertyService {


    PropertyDTO addProperty(PropertyDTO propertyDTO);

    TenantDTO addTenant(Long propertyId, TenantDTO tenantDTO);

    List<PropertyDTO> getAllProperties();

    List<TenantDTO> getAllTenants();

    void deleteTenant(Long id);

    void deleteProperty(Long id);
}
