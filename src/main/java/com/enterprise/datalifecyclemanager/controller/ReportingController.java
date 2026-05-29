package com.enterprise.datalifecyclemanager.controller;

import com.enterprise.datalifecyclemanager.service.ReportingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/reports")
public class ReportingController {
    @Autowired
    private ReportingService reportingService;

    @GetMapping("/dashboard-stats")
    public Map<String, Object> getDashboardStats() {
        return reportingService.getDashboardStats();
    }

    @GetMapping("/monthly-archive-operations")
    public Map<String, Long> getMonthlyArchiveOperations(@RequestParam int year) {
        return reportingService.getMonthlyArchiveOperations(year);
    }

    @GetMapping("/monthly-restore-operations")
    public Map<String, Long> getMonthlyRestoreOperations(@RequestParam int year) {
        return reportingService.getMonthlyRestoreOperations(year);
    }
}
