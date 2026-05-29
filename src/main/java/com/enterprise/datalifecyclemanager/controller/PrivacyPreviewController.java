package com.enterprise.datalifecyclemanager.controller;

import com.enterprise.datalifecyclemanager.model.Customer;
import com.enterprise.datalifecyclemanager.service.CustomerService;
import com.enterprise.datalifecyclemanager.service.PrivacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/privacy-preview")
public class PrivacyPreviewController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private PrivacyService privacyService;

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, String>> getPrivacyPreview(@PathVariable Long id) {
        Optional<Customer> customerOpt = customerService.getCustomerById(id);
        if (customerOpt.isEmpty()) return ResponseEntity.notFound().build();
        Customer customer = customerOpt.get();
        Map<String, String> preview = new HashMap<>();
        preview.put("firstNameMasked", privacyService.maskName(customer.getFirstName()));
        preview.put("lastNameMasked", privacyService.maskName(customer.getLastName()));
        preview.put("emailMasked", privacyService.maskEmail(customer.getEmail()));
        preview.put("ssnMasked", privacyService.maskSSN(customer.getSsn()));
        preview.put("firstName", customer.getFirstName());
        preview.put("lastName", customer.getLastName());
        preview.put("email", customer.getEmail());
        preview.put("ssn", customer.getSsn());
        return ResponseEntity.ok(preview);
    }
}
