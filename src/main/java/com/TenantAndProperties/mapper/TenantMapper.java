package com.TenantAndProperties.mapper;

import com.TenantAndProperties.dto.PropertyDTO;
import com.TenantAndProperties.dto.TenantDTO;
import com.TenantAndProperties.model.Property;
import com.TenantAndProperties.model.Tenant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TenantMapper {

    public Tenant tenantDtoToTenant(TenantDTO tenantDTO) {
        Tenant tenant = Tenant.builder()
                .id(tenantDTO.getId())
                .name(tenantDTO.getName())
                .build();
        if (tenantDTO.getProperty() != null) {
            Property property = Property.builder()
                    .id(tenantDTO.getProperty().getId())
                    .address(tenantDTO.getProperty().getAddress())
                    .rentAmount(tenantDTO.getProperty().getRentAmount())
                    .build();
            tenant.setProperty(property);
        }
        return tenant;
    }

    public TenantDTO tenantToTenantDto(Tenant tenant) {

        TenantDTO tenantDTO = TenantDTO.builder()
                .id(tenant.getId())
                .name(tenant.getName())
                .build();
        if (tenant.getProperty() != null) {
            PropertyDTO propertyDTO = PropertyDTO.builder()
                    .id(tenant.getProperty().getId())
                    .address(tenant.getProperty().getAddress())
                    .rentAmount(tenant.getProperty().getRentAmount())
                    .build();
            tenantDTO.setProperty(propertyDTO);
        }

        return tenantDTO;
    }
}
