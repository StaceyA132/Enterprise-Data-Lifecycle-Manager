package com.enterprise.datalifecyclemanager.service;

import org.springframework.stereotype.Service;

@Service
public class PrivacyService {
    public String maskName(String name) {
        if (name == null || name.length() < 2) return name;
        return name.charAt(0) + "*" + (name.length() > 2 ? name.substring(2) : "");
    }

    public String maskEmail(String email) {
        if (email == null || !email.contains("@")) return email;
        String[] parts = email.split("@", 2);
        String local = parts[0];
        if (local.length() < 2) return "*" + "@" + parts[1];
        return local.charAt(0) + "*****@" + parts[1];
    }

    public String maskSSN(String ssn) {
        if (ssn == null || ssn.length() != 11) return ssn;
        return "XXX-XX-" + ssn.substring(7);
    }
}
