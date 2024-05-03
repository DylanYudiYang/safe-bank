package com.hyz.safebank.repository;

import com.hyz.safebank.entity.HomeLoanAccount;
import com.hyz.safebank.entity.PersonLoanAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonLoanAccountRepository extends JpaRepository<PersonLoanAccount, Long> {
    Optional<PersonLoanAccount> findByCustomerId(Long customerId);
}
