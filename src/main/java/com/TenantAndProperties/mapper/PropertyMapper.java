package com.TenantAndProperties.mapper;

import com.TenantAndProperties.dto.PropertyDTO;
import com.TenantAndProperties.model.Property;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {TenantMapper.class})
public interface PropertyMapper {
    PropertyMapper INSTANCE = Mappers.getMapper(PropertyMapper.class);

    Property dtoToEntity(PropertyDTO propertyDTO);
    PropertyDTO entityToDto(Property property);
}