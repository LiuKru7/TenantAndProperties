package com.TenantAndProperties.dto;

import jakarta.validation.constraints.*;
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
    @NotEmpty
    @Size(min = 5, message = "Address size must be more than 5 symbols!")
    private String address;
    @PositiveOrZero(message = "Rent amount must be positive or zero!")
    private Double rentAmount;
    private List<TenantDTO> tenants;
}
