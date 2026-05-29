package com.enterprise.datalifecyclemanager.repository;

import com.enterprise.datalifecyclemanager.model.ArchivedCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchivedCustomerRepository extends JpaRepository<ArchivedCustomer, Long> {
    // Add custom query methods if needed
}
