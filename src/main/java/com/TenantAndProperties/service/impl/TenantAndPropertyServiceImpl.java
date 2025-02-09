package com.TenantAndProperties.service.impl;

import com.TenantAndProperties.TenantNotFoundException;
import com.TenantAndProperties.dto.PropertyDTO;
import com.TenantAndProperties.dto.TenantDTO;
import com.TenantAndProperties.mapper.PropertyMapper;
import com.TenantAndProperties.mapper.TenantMapper;
import com.TenantAndProperties.model.Property;
import com.TenantAndProperties.model.Tenant;
import com.TenantAndProperties.repository.PropertyRepository;
import com.TenantAndProperties.repository.TenantRepository;
import com.TenantAndProperties.service.TenantAndPropertyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TenantAndPropertyServiceImpl implements TenantAndPropertyService {
    private final PropertyRepository propertyRepository;
    private final TenantRepository tenantRepository;
    private final PropertyMapper propertyMapper;
    private final TenantMapper tenantMapper;


    @Override
    public PropertyDTO addProperty(final PropertyDTO propertyDTO) {
        final Property property = propertyRepository.save(propertyMapper.propertyDtoToProperty(propertyDTO));
        return propertyMapper.propertyToPropertyDto(property);
    }

    @Override
    public TenantDTO addTenant(Long propertyId, TenantDTO tenantDTO) {
        Optional<Property> propertyOptional = propertyRepository.findById(propertyId);
        if (propertyOptional.isPresent()){
            Property property = propertyOptional.get();

            Tenant tenant = tenantMapper.tenantDtoToTenant(tenantDTO);;
            tenant.setProperty(property);
            tenant = tenantRepository.save(tenant);
            return tenantMapper.tenantToTenantDto(tenant);
        }
        return null;
//        throw new ResourceNotFoundException("Property not found with id: " + propertyId);
    }

    @Override
    public List<PropertyDTO> getAllProperties() {
        List<Property> properties = propertyRepository.findAll();
        return properties.stream()
                .map(propertyMapper::propertyToPropertyDto)
                .toList();

    }

    @Override
    public List<TenantDTO> getAllTenants() {
        List<Tenant> tenants = tenantRepository.findAll();
        return tenants.stream()
                .map(tenantMapper::tenantToTenantDto)
                .toList();
    }

    @Override
    public void deleteTenant(Long id) {
        if (!tenantRepository.existsById(id))
            throw new TenantNotFoundException("Tenant was not found!");
        tenantRepository.deleteById(id);
    }

    @Override
    public void deleteProperty(Long id) {
        try {
            propertyRepository.deleteById(id);
        } catch (final EmptyResultDataAccessException emptyResultDataAccessException) {
            log.debug("Attempted non-existing property", emptyResultDataAccessException);
        }
    }


}
