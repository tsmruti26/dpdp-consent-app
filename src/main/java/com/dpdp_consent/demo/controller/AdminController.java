package com.dpdp_consent.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.dpdp_consent.demo.model.Admin;
import com.dpdp_consent.demo.repository.AdminRepository;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:8080")
public class AdminController {

    @Autowired
 private AdminRepository adminRepository;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");
        
        Admin admin = adminRepository.findByUsername(username);
        
        Map<String, Object> response = new HashMap<>();
        
        if (admin != null && admin.getPassword().equals(password)) {
            response.put("success", true);
            response.put("message", "Login successful");
            response.put("adminId", admin.getId());
        } else {
            response.put("success", false);
            response.put("message", "Invalid credentials");
        }
        
        return response;
    }
}