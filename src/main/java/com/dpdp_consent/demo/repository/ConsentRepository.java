package com.dpdp_consent.demo.repository;

import com.dpdp_consent.demo.model.ConsentRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsentRepository extends JpaRepository<ConsentRequest, Long> {
    long countByApprovalStatus(String approvalStatus);
}