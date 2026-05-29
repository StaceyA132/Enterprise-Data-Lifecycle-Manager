package com.enterprise.datalifecyclemanager.service;

import com.enterprise.datalifecyclemanager.repository.CustomerRepository;
import com.enterprise.datalifecyclemanager.repository.ArchivedCustomerRepository;
import com.enterprise.datalifecyclemanager.repository.AuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportingService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ArchivedCustomerRepository archivedCustomerRepository;
    @Autowired
    private AuditLogRepository auditLogRepository;

    public Map<String, Object> getDashboardStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalActiveCustomers", customerRepository.count());
        stats.put("totalArchivedCustomers", archivedCustomerRepository.count());
        stats.put("totalArchiveOperations", auditLogRepository.countByAction("Customer Archived"));
        stats.put("totalRestoreOperations", auditLogRepository.countByAction("Customer Restored"));
        stats.put("totalAuditLogEntries", auditLogRepository.count());
        return stats;
    }

    public Map<String, Long> getMonthlyArchiveOperations(int year) {
        Map<String, Long> result = new HashMap<>();
        for (int month = 1; month <= 12; month++) {
            YearMonth ym = YearMonth.of(year, month);
            LocalDate start = ym.atDay(1);
            LocalDate end = ym.atEndOfMonth();
            long count = auditLogRepository.countByActionAndTimestampBetween(
                "Customer Archived",
                start.atStartOfDay(ZoneId.systemDefault()).toLocalDateTime(),
                end.atTime(23,59,59).atZone(ZoneId.systemDefault()).toLocalDateTime()
            );
            result.put(ym.toString(), count);
        }
        return result;
    }

    public Map<String, Long> getMonthlyRestoreOperations(int year) {
        Map<String, Long> result = new HashMap<>();
        for (int month = 1; month <= 12; month++) {
            YearMonth ym = YearMonth.of(year, month);
            LocalDate start = ym.atDay(1);
            LocalDate end = ym.atEndOfMonth();
            long count = auditLogRepository.countByActionAndTimestampBetween(
                "Customer Restored",
                start.atStartOfDay(ZoneId.systemDefault()).toLocalDateTime(),
                end.atTime(23,59,59).atZone(ZoneId.systemDefault()).toLocalDateTime()
            );
            result.put(ym.toString(), count);
        }
        return result;
    }
}
