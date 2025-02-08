package com.TenantAndProperties.service.impl;

import com.TenantAndProperties.dto.PropertyDTO;
import com.TenantAndProperties.mapper.PropertyMapper;
import com.TenantAndProperties.model.Property;
import com.TenantAndProperties.repository.PropertyRepository;
import com.TenantAndProperties.repository.TenantRepository;
import com.TenantAndProperties.service.TenantAndPropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TenantAndPropertyServiceImpl implements TenantAndPropertyService {
    private final PropertyRepository propertyRepository;
    private final TenantRepository tenantRepository;


    @Override
    public PropertyDTO addProperty(final PropertyDTO propertyDTO) {
        final Property property = propertyRepository.save(PropertyMapper.INSTANCE.dtoToEntity(propertyDTO));
        return PropertyMapper.INSTANCE.entityToDto(property);
    }
}
