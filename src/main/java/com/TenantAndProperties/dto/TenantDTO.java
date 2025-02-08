package com.TenantAndProperties.dto;

import com.TenantAndProperties.model.Property;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TenantDTO {

    private Long id;

    @NotEmpty
    private String name;

    private PropertyDTO property;

}
