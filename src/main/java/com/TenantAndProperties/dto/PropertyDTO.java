package com.TenantAndProperties.dto;

import com.TenantAndProperties.model.Tenant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PropertyDTO {
    private Long id;
    private String address;
    private Double rentAmount;
    private List<TenantDTO> tenants;
}
