package com.hyz.safebank.repository;

import com.hyz.safebank.entity.CheckingAccount;
import com.hyz.safebank.entity.SavingAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SavingAccountRepository extends JpaRepository <SavingAccount, Long>{
    Optional<SavingAccount> findByCustomerId(Long customerId);

    Boolean existsByCustomerId(Long customerId);
}
