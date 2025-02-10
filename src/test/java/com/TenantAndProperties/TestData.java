package com.TenantAndProperties;

import com.TenantAndProperties.dto.PropertyDTO;
import com.TenantAndProperties.dto.TenantDTO;
import com.TenantAndProperties.model.Property;
import com.TenantAndProperties.model.Tenant;

public class TestData {
    private TestData(){}

    public static Property testProperty(){

        return Property.builder()
                .address("Ukmerges")
                .rentAmount(100.0)
                .build();
    }
    public static PropertyDTO testPropertyDTO(){
        return PropertyDTO.builder()
                .address("Ukmerges")
                .rentAmount(100.0)
                .build();
    }
    public static Tenant testTenant(){
        return Tenant.builder()
                .name("Jonas")
                .build();

    }
    public static TenantDTO testTenantDTO(){
        return TenantDTO.builder()
                .name("Jonas")
                .build();
    }



}
