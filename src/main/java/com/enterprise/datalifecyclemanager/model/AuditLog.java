

package com.enterprise.datalifecyclemanager.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "audit_logs")
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String action;

    @Column(nullable = false)
    private Long recordId;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column(nullable = false)
    private String details;

    public AuditLog() {}

    // For DataInitializer: id, action, details, timestamp
    public AuditLog(Long id, String action, String details, java.time.LocalDateTime timestamp) {
        this.id = id;
        this.action = action;
        this.details = details;
        this.timestamp = timestamp;
    }

    public AuditLog(Long id, String action, Long recordId, LocalDateTime timestamp, String details) {
        this.id = id;
        this.action = action;
        this.recordId = recordId;
        this.timestamp = timestamp;
        this.details = details;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }
    public Long getRecordId() { return recordId; }
    public void setRecordId(Long recordId) { this.recordId = recordId; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }
}
