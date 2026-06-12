package com.dpdp_consent.demo.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class OtpController {

    private final Map<String, String> otpStore = new HashMap<>();

    // =========================
    // GENERATE OTP (POST)
    // =========================
    @PostMapping("/generate-otp")
    public Map<String, String> generateOtp(@RequestParam String aadhaar) {

        // Generate 6-digit OTP
        String otp = String.format("%06d", new Random().nextInt(999999));

        otpStore.put(aadhaar, otp);

        Map<String, String> res = new HashMap<>();
        res.put("status", "SUCCESS");
        res.put("otp", otp);
        res.put("message", "OTP generated successfully");

        System.out.println("Generated OTP for " + aadhaar + " = " + otp);

        return res;
    }

    // =========================
    // VERIFY OTP (POST)
    // =========================
    @PostMapping("/verify-otp")
    public Map<String, String> verifyOtp(
            @RequestParam String aadhaar,
            @RequestParam String otp) {

        Map<String, String> res = new HashMap<>();

        String storedOtp = otpStore.get(aadhaar);

        if (storedOtp == null) {
            res.put("status", "FAILED");
            res.put("message", "OTP not generated");
            return res;
        }

        if (storedOtp.equals(otp)) {
            res.put("status", "SUCCESS");
            res.put("message", "OTP verified successfully");

            otpStore.remove(aadhaar); // one-time use
            return res;
        }

        res.put("status", "FAILED");
        res.put("message", "Invalid OTP");

        return res;
    }
}