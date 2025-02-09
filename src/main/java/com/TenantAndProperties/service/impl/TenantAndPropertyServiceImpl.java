package com.TenantAndProperties.service.impl;

import com.TenantAndProperties.Exceptions.TenantOrPropertyNotFoundException;
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
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${error.propertyNotFound}")
    private String propertyNotFoundMessage;
    @Value("${error.tenantNotFound}")
    private String tenantNotFoundMessage;


    @Override
    public PropertyDTO addProperty(final PropertyDTO propertyDTO) {
        final Property property = propertyRepository.save(propertyMapper.propertyDtoToProperty(propertyDTO));
        return propertyMapper.propertyToPropertyDto(property);
    }

    @Override
    public TenantDTO addTenant(final Long propertyId, TenantDTO tenantDTO) {
        Optional<Property> propertyOptional = propertyRepository.findById(propertyId);
        if (propertyOptional.isPresent()) {
            Property property = propertyOptional.get();
            Tenant tenant = tenantMapper.tenantDtoToTenant(tenantDTO);
            tenant.setProperty(property);
            tenant = tenantRepository.save(tenant);
            return tenantMapper.tenantToTenantDto(tenant);
        }
        throw new TenantOrPropertyNotFoundException(propertyNotFoundMessage);
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
    public void deleteTenant(final Long id) {
        if (!tenantRepository.existsById(id))
            throw new TenantOrPropertyNotFoundException(tenantNotFoundMessage);
        tenantRepository.deleteById(id);
    }

    @Override
    public void deleteProperty(final Long id) {
        if (!propertyRepository.existsById(id)) {
            throw new TenantOrPropertyNotFoundException(propertyNotFoundMessage);
        }
        propertyRepository.deleteById(id);
    }

    @Override
    public PropertyDTO updateProperty(final PropertyDTO propertyDTO,final Long id) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(()->new TenantOrPropertyNotFoundException(propertyNotFoundMessage));

        if (propertyDTO.getAddress()!=null){
            property.setAddress(propertyDTO.getAddress());
        }
        if(propertyDTO.getRentAmount()!=null){
            property.setRentAmount(propertyDTO.getRentAmount());
        }

        property = propertyRepository.save(property);
        return propertyMapper.propertyToPropertyDto(property);
    }

    @Override
    public TenantDTO updateTenant(final TenantDTO tenantDTO,final Long id) {
        Tenant tenant = tenantRepository.findById(id)
                .orElseThrow(()-> new TenantOrPropertyNotFoundException(tenantNotFoundMessage));

        if (tenantDTO.getName() != null) {
            tenant.setName(tenantDTO.getName());
        }

        return tenantMapper.tenantToTenantDto(tenant);
    }

}
