package com.hyz.safebank.repository;

import com.hyz.safebank.entity.HomeLoanAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HomeLoanAccountRepository extends JpaRepository<HomeLoanAccount, Long> {
    boolean existsByCustomerId(Long customerId);

    Optional<HomeLoanAccount> findByCustomerId(Long customerId);
}
