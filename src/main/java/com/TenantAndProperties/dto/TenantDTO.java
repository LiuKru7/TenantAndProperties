package com.TenantAndProperties.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TenantDTO {
    private Long id;
    @Size(min = 2, message = "Name must be longer than 2 symbols!")
    private String name;
    private PropertyDTO property;
}
