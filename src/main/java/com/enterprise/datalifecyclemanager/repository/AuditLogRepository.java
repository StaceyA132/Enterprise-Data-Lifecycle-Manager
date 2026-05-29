package com.enterprise.datalifecyclemanager.repository;

import com.enterprise.datalifecyclemanager.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;

@Repository

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
    long countByAction(String action);
    long countByActionAndTimestampBetween(String action, LocalDateTime start, LocalDateTime end);
}
