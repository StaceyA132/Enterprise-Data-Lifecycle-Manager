package com.enterprise.datalifecyclemanager;

import com.enterprise.datalifecyclemanager.model.Customer;
import com.enterprise.datalifecyclemanager.model.ArchivedCustomer;
import com.enterprise.datalifecyclemanager.model.AuditLog;
import com.enterprise.datalifecyclemanager.repository.CustomerRepository;
import com.enterprise.datalifecyclemanager.repository.ArchivedCustomerRepository;
import com.enterprise.datalifecyclemanager.repository.AuditLogRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class DataInitializer {
    @Bean
    public CommandLineRunner loadData(CustomerRepository customerRepo, ArchivedCustomerRepository archivedRepo, AuditLogRepository auditLogRepo) {
        return args -> {
            if (customerRepo.count() == 0) {
                customerRepo.save(new Customer(null, "Alice", "Smith", "alice@example.com", "123-45-6789", "ACTIVE", java.time.LocalDateTime.now()));
                customerRepo.save(new Customer(null, "Bob", "Johnson", "bob@example.com", "987-65-4321", "ACTIVE", java.time.LocalDateTime.now()));
            }
            if (archivedRepo.count() == 0) {
                archivedRepo.save(new ArchivedCustomer(
                    null,
                    "Carol",
                    "White",
                    "carol@example.com",
                    "555-55-5555",
                    java.time.LocalDateTime.now().minusDays(3),
                    "ARCHIVED"
                ));
            }
            if (auditLogRepo.count() == 0) {
                auditLogRepo.save(new AuditLog(null, "Customer Archived", 1L, java.time.LocalDateTime.now().minusDays(2), "carol@example.com"));
                auditLogRepo.save(new AuditLog(null, "Customer Restored", 1L, java.time.LocalDateTime.now().minusDays(1), "carol@example.com"));
            }
        };
    }
}
