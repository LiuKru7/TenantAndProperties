package com.TenantAndProperties.controller;

import com.TenantAndProperties.TestData;
import com.TenantAndProperties.dto.PropertyDTO;
import com.TenantAndProperties.dto.TenantDTO;
import com.TenantAndProperties.model.Tenant;
import com.TenantAndProperties.repository.PropertyRepository;
import com.TenantAndProperties.service.TenantAndPropertyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TenantAndPropertyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TenantAndPropertyService service;

    @Autowired
    private PropertyRepository propertyRepository;

    @Test
    void testAddProperty() throws Exception {
        final PropertyDTO propertyDTO = TestData.testPropertyDTO();
        final String propertyJson = new ObjectMapper().writeValueAsString(propertyDTO);

        propertyRepository.deleteAll();

        mockMvc.perform(post("/api/property")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(propertyJson))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value(propertyDTO.getAddress()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rentAmount").value(propertyDTO.getRentAmount()));
    }

    @Test
    void testAddTenant() throws Exception {
        propertyRepository.deleteAll();

        final PropertyDTO propertyDTO = TestData.testPropertyDTO();
        PropertyDTO savedProperty = service.addProperty(propertyDTO);

        final TenantDTO tenant = TestData.testTenantDTO();
        final String tenantJson = new ObjectMapper().writeValueAsString(tenant);

        mockMvc.perform(post("/api/tenant/" + savedProperty.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(tenantJson))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(tenant.getName()));
    }


    @Test
    void getAllProperties() throws Exception {
        final PropertyDTO propertyDTO = TestData.testPropertyDTO();
        propertyRepository.deleteAll();
        service.addProperty(propertyDTO);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/property"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].address").value(propertyDTO.getAddress()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].rentAmount").value(propertyDTO.getRentAmount()));

    }


    @Test
    void getAllTenants() throws Exception {
        final PropertyDTO propertyDTO = TestData.testPropertyDTO();
        final TenantDTO tenantDTO = TestData.testTenantDTO();
        final List<TenantDTO> tenantDTOS = List.of(tenantDTO);

        propertyRepository.deleteAll();
        propertyDTO.setTenants(tenantDTOS);
        service.addProperty(propertyDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/tenant"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].name").value(tenantDTO.getName()));
    }

    @Test
    @Transactional
    void deleteTenant() throws Exception {
        propertyRepository.deleteAll(); // Clear existing data

        PropertyDTO propertyDTO = TestData.testPropertyDTO();
        PropertyDTO savedProperty = service.addProperty(propertyDTO);

        TenantDTO tenant = TestData.testTenantDTO();
        Long tenantId = service.addTenant(savedProperty.getId(), tenant).getId();

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/tenant/" + tenantId))
                .andExpect(status().isOk());
    }

    @Test
    void deleteProperty() {
    }

    }