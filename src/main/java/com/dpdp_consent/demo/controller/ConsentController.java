package com.dpdp_consent.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.dpdp_consent.demo.model.ConsentRequest;
import com.dpdp_consent.demo.repository.ConsentRepository;
import java.util.UUID;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080")
public class ConsentController {

    @Autowired
    private ConsentRepository consentRepository;

    @PostMapping("/create_consent")
    public ConsentRequest createConsent(@RequestBody ConsentRequest consentRequest) {
        consentRequest.setStateToken(UUID.randomUUID().toString());
        consentRequest.setApprovalStatus("Pending");
        return consentRepository.save(consentRequest);
    }

    @GetMapping("/get_consents")
    public List<ConsentRequest> getConsents() {
        return consentRepository.findAll();
    }

    @GetMapping("/dashboard")
    public Map<String, Long> getDashboardStats() {
        List<ConsentRequest> all = consentRepository.findAll();
        Map<String, Long> stats = new HashMap<>();
        
        stats.put("total", (long) all.size());
        stats.put("approved", all.stream().filter(c -> "Approved".equals(c.getApprovalStatus())).count());
        stats.put("rejected", all.stream().filter(c -> "Rejected".equals(c.getApprovalStatus())).count());
        stats.put("pending", all.stream().filter(c -> "Pending".equals(c.getApprovalStatus())).count());
        
        return stats;
    }

    @PostMapping("/update_status")
    public ConsentRequest updateStatus(@RequestBody Map<String, Object> request) {
        Long id = Long.valueOf(request.get("id").toString());
        String status = request.get("status").toString();
        
        ConsentRequest consent = consentRepository.findById(id).orElse(null);
        if (consent != null) {
            consent.setApprovalStatus(status);
            return consentRepository.save(consent);
        }
        return null;
    }
}