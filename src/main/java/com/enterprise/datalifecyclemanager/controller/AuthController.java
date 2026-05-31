package com.enterprise.datalifecyclemanager.controller;

import com.enterprise.datalifecyclemanager.model.LoginRequest;
import com.enterprise.datalifecyclemanager.model.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        // Simple hardcoded authentication for demo
        if ("admin@example.com".equals(request.getEmail()) && "password123".equals(request.getPassword())) {
            return ResponseEntity.ok(new LoginResponse("success", "dummy-token-123"));
        } else {
            return ResponseEntity.ok(new LoginResponse("fail", null));
        }
    }
}
