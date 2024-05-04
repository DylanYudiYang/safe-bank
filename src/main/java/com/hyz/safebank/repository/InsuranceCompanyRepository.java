package com.hyz.safebank.repository;

import com.hyz.safebank.entity.CheckingAccount;
import com.hyz.safebank.entity.InsuranceCompany;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InsuranceCompanyRepository extends JpaRepository<InsuranceCompany, Long> {
    Boolean existsByCompanyName(String companyName);
    Optional<InsuranceCompany> findById(Long id);
}
