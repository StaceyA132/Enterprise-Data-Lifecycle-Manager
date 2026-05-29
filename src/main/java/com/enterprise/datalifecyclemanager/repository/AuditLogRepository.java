package com.enterprise.datalifecyclemanager.repository;

import com.enterprise.datalifecyclemanager.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
    // Add custom query methods if needed
}
