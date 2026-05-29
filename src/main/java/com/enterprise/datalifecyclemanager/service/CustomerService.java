package com.enterprise.datalifecyclemanager.service;

import com.enterprise.datalifecyclemanager.model.Customer;
import com.enterprise.datalifecyclemanager.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.enterprise.datalifecyclemanager.service.AuditService;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AuditService auditService;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public Customer createCustomer(Customer customer) {
        Customer saved = customerRepository.save(customer);
        auditService.logAction("Customer Created", saved.getId(), "Created customer: " + saved.getEmail());
        return saved;
    }

    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        return customerRepository.findById(id).map(customer -> {
            customer.setFirstName(updatedCustomer.getFirstName());
            customer.setLastName(updatedCustomer.getLastName());
            customer.setEmail(updatedCustomer.getEmail());
            customer.setSsn(updatedCustomer.getSsn());
            customer.setStatus(updatedCustomer.getStatus());
            Customer saved = customerRepository.save(customer);
            auditService.logAction("Customer Updated", saved.getId(), "Updated customer: " + saved.getEmail());
            return saved;
        }).orElse(null);
    }

    public void deleteCustomer(Long id) {
        customerRepository.findById(id).ifPresent(customer -> {
            customerRepository.deleteById(id);
            auditService.logAction("Customer Deleted", id, "Deleted customer: " + customer.getEmail());
        });
    }
}
