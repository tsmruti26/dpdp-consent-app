package com.dpdp_consent.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "consent_requests")
public class ConsentRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(name = "consent_for", nullable = false)
    private String consentFor;

    @Column(name = "dependant_details")
    private String taylorDetails;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String purpose;

    @Column(nullable = false)
    private String decision;

    @Column(name = "approval_status", nullable = false)
    private String approvalStatus;

    @Column(name = "state_token", nullable = false, unique = true)
    private String stateToken;

    @Column(name = "consent_date", nullable = false)
    private LocalDateTime consentDate;

    public ConsentRequest() {}

    @PrePersist
    protected void onCreate() {
        this.consentDate = LocalDateTime.now();
        if (this.approvalStatus == null) {
            this.approvalStatus = "Pending";
        }
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getConsentFor() { return consentFor; }
    public void setConsentFor(String consentFor) { this.consentFor = consentFor; }
    public String getTaylorDetails() { return taylorDetails; }
    public void setTaylorDetails(String taylorDetails) { this.taylorDetails = taylorDetails; }
    public String getPurpose() { return purpose; }
    public void setPurpose(String purpose) { this.purpose = purpose; }
    public String getDecision() { return decision; }
    public void setDecision(String decision) { this.decision = decision; }
    public String getApprovalStatus() { return approvalStatus; }
    public void setApprovalStatus(String approvalStatus) { this.approvalStatus = approvalStatus; }
    public String getStateToken() { return stateToken; }
    public void setStateToken(String stateToken) { this.stateToken = stateToken; }
    public LocalDateTime getConsentDate() { return consentDate; }
    public void setConsentDate(LocalDateTime consentDate) { this.consentDate = consentDate; }
}