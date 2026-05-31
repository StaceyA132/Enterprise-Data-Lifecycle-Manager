package com.enterprise.datalifecyclemanager.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "archived_customers")
public class ArchivedCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String ssn;

    @Column(nullable = false)
    private LocalDateTime archivedAt;

    @Column(nullable = false)
    private String archiveReason;

    // No-args constructor
    public ArchivedCustomer() {}

    // For DataInitializer: id, firstName, lastName, email, archiveReason
    public ArchivedCustomer(Long id, String firstName, String lastName, String email, String archiveReason) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.archiveReason = archiveReason;
    }

    // All-args constructor
    public ArchivedCustomer(Long id, String firstName, String lastName, String email, String ssn, LocalDateTime archivedAt, String archiveReason) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.ssn = ssn;
        this.archivedAt = archivedAt;
        this.archiveReason = archiveReason;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSsn() { return ssn; }
    public void setSsn(String ssn) { this.ssn = ssn; }
    public LocalDateTime getArchivedAt() { return archivedAt; }
    public void setArchivedAt(LocalDateTime archivedAt) { this.archivedAt = archivedAt; }
    public String getArchiveReason() { return archiveReason; }
    public void setArchiveReason(String archiveReason) { this.archiveReason = archiveReason; }
}
