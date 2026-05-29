package com.enterprise.datalifecyclemanager.controller;

import com.enterprise.datalifecyclemanager.model.Customer;
import com.enterprise.datalifecyclemanager.service.CustomerService;
import com.enterprise.datalifecyclemanager.service.ArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private ArchiveService archiveService;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Optional<Customer> customer = customerService.getCustomerById(id);
        return customer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        Customer updated = customerService.updateCustomer(id, customer);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    // Archive a customer
    @PostMapping("/{id}/archive")
    public ResponseEntity<String> archiveCustomer(@PathVariable Long id, @RequestParam(defaultValue = "Manual archive") String reason) {
        boolean archived = archiveService.archiveCustomer(id, reason);
        if (archived) {
            return ResponseEntity.ok("Customer archived successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Restore an archived customer
    @PostMapping("/restore/{archivedId}")
    public ResponseEntity<String> restoreCustomer(@PathVariable Long archivedId) {
        boolean restored = archiveService.restoreCustomer(archivedId);
        if (restored) {
            return ResponseEntity.ok("Customer restored successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
