package com.hyz.safebank.repository;

import com.hyz.safebank.entity.StuLoanAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StuLoanAccountRepository extends JpaRepository<StuLoanAccount, Long> {
    boolean existsByCustomerId(Long customerId);

    Optional<StuLoanAccount> findByCustomerId(Long customerId);
}
