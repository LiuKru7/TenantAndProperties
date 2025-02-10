package com.TenantAndProperties.service.impl;

import com.TenantAndProperties.Exceptions.TenantOrPropertyNotFoundException;
import com.TenantAndProperties.TestData;
import com.TenantAndProperties.dto.PropertyDTO;
import com.TenantAndProperties.dto.TenantDTO;
import com.TenantAndProperties.mapper.PropertyMapper;
import com.TenantAndProperties.mapper.TenantMapper;
import com.TenantAndProperties.model.Property;
import com.TenantAndProperties.model.Tenant;
import com.TenantAndProperties.repository.PropertyRepository;
import com.TenantAndProperties.repository.TenantRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TenantAndPropertyServiceImplTest {

    @Mock
    private PropertyRepository propertyRepository;
    @Mock
    private TenantRepository tenantRepository;
    @Mock
    private PropertyMapper propertyMapper;
    @Mock
    private TenantMapper tenantMapper;

    @InjectMocks
    private TenantAndPropertyServiceImpl testService;


    @Test
    void testAddProperty() {
        Property property = TestData.testProperty();
        PropertyDTO propertyDTO = TestData.testPropertyDTO();

        when(propertyMapper.propertyDtoToProperty(propertyDTO)).thenReturn(property);
        when(propertyRepository.save(property)).thenReturn(property);
        when(propertyMapper.propertyToPropertyDto(property)).thenReturn(propertyDTO);

        PropertyDTO result = testService.addProperty(propertyDTO);
        assertEquals(propertyDTO, result);
    }

    @Test
    void testAddTenant() {
        Long propertyId = 1L;
        Property property = TestData.testProperty();
        Tenant tenant = TestData.testTenant();
        TenantDTO tenantDTO = TestData.testTenantDTO();

        when(propertyRepository.findById(propertyId)).thenReturn(Optional.of(property));
        when(tenantMapper.tenantDtoToTenant(tenantDTO)).thenReturn(tenant);
        when(tenantRepository.save(tenant)).thenReturn(tenant);
        when(tenantMapper.tenantToTenantDto(tenant)).thenReturn(tenantDTO);

        TenantDTO result = testService.addTenant(propertyId, tenantDTO);
        assertEquals(tenantDTO, result);
    }

    @Test
    void testGetAllProperties() {
        List<Property> properties = List.of(TestData.testProperty());
        PropertyDTO propertyDTO = TestData.testPropertyDTO();

        when(propertyRepository.findAll()).thenReturn(properties);
        when(propertyMapper.propertyToPropertyDto(properties.get(0))).thenReturn(propertyDTO);

        List<PropertyDTO> result = testService.getAllProperties();
        assertEquals(1, result.size());
        assertEquals(propertyDTO, result.get(0));
    }

    @Test
    void testUpdateProperty() {
        Long id = 1L;
        Property property = TestData.testProperty();
        PropertyDTO propertyDTO = TestData.testPropertyDTO();

        when(propertyRepository.findById(id)).thenReturn(Optional.of(property));
        when(propertyRepository.save(property)).thenReturn(property);
        when(propertyMapper.propertyToPropertyDto(property)).thenReturn(propertyDTO);

        PropertyDTO result = testService.updateProperty(propertyDTO, id);
        assertEquals(propertyDTO, result);
    }

    @Test
    void testDeleteTenant() {
        Long id = 1L;
        when(tenantRepository.existsById(id)).thenReturn(true);
        testService.deleteTenant(id);
        verify(tenantRepository).deleteById(id);
    }

    @Test
    void testDeleteTenantThrowsException() {
        Long id = 1L;
        when(tenantRepository.existsById(id)).thenReturn(false);
        assertThrows(TenantOrPropertyNotFoundException.class, () -> testService.deleteTenant(id));
    }

    @Test
    void deleteProperty() {
        Long id = 1L;
        when(propertyRepository.existsById(id)).thenReturn(true);
        testService.deleteProperty(id);
        verify(propertyRepository).deleteById(id);
    }

    @Test
    void testDeletePropertyThrowsException() {
        Long id = 1L;
        when(propertyRepository.existsById(id)).thenReturn(false);
        assertThrows(TenantOrPropertyNotFoundException.class, () -> testService.deleteProperty(id));
    }

    @Test
    void updateProperty() {
        Long id = 1L;
        Property property = TestData.testProperty();
        PropertyDTO propertyDTO = TestData.testPropertyDTO();

        when(propertyRepository.findById(id)).thenReturn(Optional.of(property));
        when(propertyRepository.save(property)).thenReturn(property);
        when(propertyMapper.propertyToPropertyDto(property)).thenReturn(propertyDTO);

        PropertyDTO result = testService.updateProperty(propertyDTO, id);
        assertEquals(propertyDTO, result);
    }

    @Test
    void updateTenant() {
        Long id = 1L;
        Tenant tenant = TestData.testTenant();
        TenantDTO tenantDTO = TestData.testTenantDTO();

        when(tenantRepository.findById(id)).thenReturn(Optional.of(tenant));
        when(tenantMapper.tenantToTenantDto(tenant)).thenReturn(tenantDTO);

        TenantDTO result = testService.updateTenant(tenantDTO, id);
        assertEquals(tenantDTO, result);
    }
}