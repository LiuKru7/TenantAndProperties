package com.TenantAndProperties.mapper;

import com.TenantAndProperties.dto.PropertyDTO;
import com.TenantAndProperties.model.Property;
import com.TenantAndProperties.model.Tenant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PropertyMapper {
    private final TenantMapper tenantMapper;

    public Property propertyDtoToProperty(PropertyDTO propertyDTO) {

        Property property = Property.builder()
                .id(propertyDTO.getId())
                .address(propertyDTO.getAddress())
                .rentAmount(propertyDTO.getRentAmount())
                .build();

        if (propertyDTO.getTenants() != null) {
            property.setTenants(propertyDTO.getTenants().stream()
                    .map(tenantDTO -> Tenant.builder()
                            .id(tenantDTO.getId())
                            .name(tenantDTO.getName())
                            .property(property)
                            .build())
                    .collect(Collectors.toList()));
        }
        return property;
    }

    public PropertyDTO propertyToPropertyDto(Property property) {
        return PropertyDTO.builder()
                .id(property.getId())
                .address(property.getAddress())
                .rentAmount(property.getRentAmount())
                .tenants(property.getTenants() != null ?
                        property.getTenants().stream()
                                .map(tenantMapper::tenantToTenantDto)
                                .collect(Collectors.toList())
                        : null)
                .build();
    }
}