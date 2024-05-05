package com.hyz.safebank.repository;

import com.hyz.safebank.entity.CheckingAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CheckingAccountRepository extends JpaRepository<CheckingAccount, Long> {
    Optional<CheckingAccount> findByCustomerId(Long customerId);
    Boolean existsByCustomerId(Long customerId);
}
