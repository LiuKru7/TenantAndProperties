package com.TenantAndProperties.Config;

import com.TenantAndProperties.model.Property;
import com.TenantAndProperties.model.Tenant;
import com.TenantAndProperties.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class FakeDataLoader implements CommandLineRunner {

    private final PropertyRepository propertyRepository;


    @Override
    public void run(String... args) throws Exception {
        Random random =new Random();
        List<String> names = List.of("Jurga","Aldona", "Giedre","Monika","Jurate","Magdalena", "Igle");
        List<String> names2 = List.of("Margarita", "Ruta", "Birute", "Kristina", "Asta", "Virginija", "Diana");
        List<String> addresses = List.of("Gedimino pr. 1", "Pilies g. 15", "Antakalnio g. 45", "Kalvarijų g. 28",
                "Konstitucijos pr. 12", "Lukiškių g. 5", "Žirmūnų g. 30");

        for (int i = 0; i < 6; i++) {
            int randomNumber = random.nextInt(1000);
            Tenant tenant1 = Tenant.builder()
                    .name(names.get(i))
                    .build();

            Tenant tenant2 = Tenant.builder()
                    .name(names2.get(i))
                    .build();

            Property property = Property.builder()
                    .address(addresses.get(i))
                    .rentAmount(400.99)
                    .tenants(List.of(tenant1, tenant2))
                    .build();

            tenant1.setProperty(property);
            tenant2.setProperty(property);
            propertyRepository.save(property);

        }




    }
}
