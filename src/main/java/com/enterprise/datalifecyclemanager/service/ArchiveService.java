package com.enterprise.datalifecyclemanager.service;

import com.enterprise.datalifecyclemanager.model.ArchivedCustomer;
import com.enterprise.datalifecyclemanager.model.Customer;
import com.enterprise.datalifecyclemanager.repository.ArchivedCustomerRepository;
import com.enterprise.datalifecyclemanager.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ArchiveService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ArchivedCustomerRepository archivedCustomerRepository;

    public boolean archiveCustomer(Long customerId, String reason) {
        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        if (customerOpt.isEmpty()) return false;
        Customer customer = customerOpt.get();
        ArchivedCustomer archived = new ArchivedCustomer();
        archived.setFirstName(customer.getFirstName());
        archived.setLastName(customer.getLastName());
        archived.setEmail(customer.getEmail());
        archived.setSsn(customer.getSsn());
        archived.setArchivedAt(LocalDateTime.now());
        archived.setArchiveReason(reason);
        archivedCustomerRepository.save(archived);
        customerRepository.deleteById(customerId);
        return true;
    }

    public boolean restoreCustomer(Long archivedCustomerId) {
        Optional<ArchivedCustomer> archivedOpt = archivedCustomerRepository.findById(archivedCustomerId);
        if (archivedOpt.isEmpty()) return false;
        ArchivedCustomer archived = archivedOpt.get();
        Customer customer = new Customer();
        customer.setFirstName(archived.getFirstName());
        customer.setLastName(archived.getLastName());
        customer.setEmail(archived.getEmail());
        customer.setSsn(archived.getSsn());
        customer.setStatus("restored");
        customer.setCreatedAt(LocalDateTime.now());
        customerRepository.save(customer);
        archivedCustomerRepository.deleteById(archivedCustomerId);
        return true;
    }
}
