package com.hyz.safebank.repository;

import com.hyz.safebank.entity.LoanAccount;
import com.hyz.safebank.entity.SavingAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoanAccountRepository extends JpaRepository<LoanAccount, Long> {
    Optional<LoanAccount> findByCustomerId(Long customerId);
}
