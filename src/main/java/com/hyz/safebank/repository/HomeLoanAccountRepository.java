package com.hyz.safebank.repository;

import com.hyz.safebank.entity.HomeLoanAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeLoanAccountRepository extends JpaRepository<HomeLoanAccount, Long> {
}
