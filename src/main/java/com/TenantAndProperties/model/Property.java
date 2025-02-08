package com.TenantAndProperties.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String address;


    private Double rentAmount;

    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = false)
    @JsonManagedReference
    private List<Tenant> tenants;

}
