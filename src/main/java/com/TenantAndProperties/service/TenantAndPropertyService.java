package com.TenantAndProperties.service;

import com.TenantAndProperties.dto.PropertyDTO;
import com.TenantAndProperties.repository.PropertyRepository;
import com.TenantAndProperties.repository.TenantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


public interface TenantAndPropertyService {


    PropertyDTO addProperty(PropertyDTO propertyDTO);
}
