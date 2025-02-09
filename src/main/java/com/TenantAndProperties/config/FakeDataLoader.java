package com.TenantAndProperties.config;

import com.TenantAndProperties.model.Property;
import com.TenantAndProperties.model.Tenant;
import com.TenantAndProperties.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class FakeDataLoader implements CommandLineRunner {

    private final PropertyRepository propertyRepository;


    /**
     * Initializes the application with sample data on startup.
     * This method creates and persists 7 properties, each with 2 tenants.
     *
     * The method performs the following operations:
     * - Generates random rent amounts between 400.00 and 1000.00
     * - Creates properties with predefined Lithuanian addresses
     * - Assigns two tenants to each property using predefined Lithuanian names
     * - Sets up bidirectional relationships between properties and tenants
     * - Persists the data to the database
     *
     * Data generation details:
     * - Addresses: Uses 7 predefined addresses from major streets in Vilnius
     * - Tenant names: Uses 14 predefined Lithuanian female names (7 for first tenants, 7 for second tenants)
     * - Rent amounts: Randomly generated with exactly 2 decimal places
     */
    @Override
    public void run(String... args) throws Exception {
        Random random = new Random();
        List<String> names = List.of("Jurga", "Aldona", "Giedre", "Monika", "Jurate", "Magdalena", "Igle");
        List<String> names2 = List.of("Margarita", "Ruta", "Birute", "Kristina", "Asta", "Virginija", "Diana");
        List<String> addresses = List.of("Gedimino pr. 1", "Pilies g. 15", "Antakalnio g. 45", "Kalvarijų g. 28",
                "Konstitucijos pr. 12", "Lukiškių g. 5", "Žirmūnų g. 30");


        for (int i = 0; i < 7; i++) {
            DecimalFormat df = new DecimalFormat("#.00");
            double rentAmountRandom = Double.parseDouble(String.format("%.2f", random.nextDouble(400.0, 1000.0)));

            Tenant tenant1 = Tenant.builder()
                    .name(names.get(i))
                    .build();

            Tenant tenant2 = Tenant.builder()
                    .name(names2.get(i))
                    .build();

            Property property = Property.builder()
                    .address(addresses.get(i))
                    .rentAmount(rentAmountRandom)
                    .tenants(List.of(tenant1, tenant2))
                    .build();

            tenant1.setProperty(property);
            tenant2.setProperty(property);
            propertyRepository.save(property);
        }
    }
}
