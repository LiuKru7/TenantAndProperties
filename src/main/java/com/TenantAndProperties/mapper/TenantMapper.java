package com.TenantAndProperties.mapper;

import com.TenantAndProperties.dto.TenantDTO;
import com.TenantAndProperties.model.Tenant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TenantMapper {
    TenantMapper INSTANCE = Mappers.getMapper(TenantMapper.class);

    @Mapping(target = "property", ignore = true)
    Tenant dtoToEntity(TenantDTO tenantDTO);

    @Mapping(target = "property", ignore = true)
    TenantDTO entityToDto(Tenant tenant);
}