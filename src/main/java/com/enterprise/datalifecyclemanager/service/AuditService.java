package com.enterprise.datalifecyclemanager.service;

import com.enterprise.datalifecyclemanager.model.AuditLog;
import com.enterprise.datalifecyclemanager.repository.AuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuditService {
    @Autowired
    private AuditLogRepository auditLogRepository;

    public void logAction(String action, Long recordId, String details) {
        AuditLog log = new AuditLog();
        log.setAction(action);
        log.setRecordId(recordId);
        log.setTimestamp(LocalDateTime.now());
        log.setDetails(details);
        auditLogRepository.save(log);
    }

    public List<AuditLog> getAllLogs() {
        return auditLogRepository.findAll();
    }
}
