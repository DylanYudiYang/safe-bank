package com.hyz.safebank.service.impl;

import com.hyz.safebank.entity.CheckingAccount;
import com.hyz.safebank.repository.CheckingAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CheckingAccountServiceImpl implements CheckingAccountService {
    @Autowired
    private CheckingAccountRepository checkingAccountRepository;

    public BigDecimal getCheckingAccountBalance(Long customerId) {
        Optional<CheckingAccount> account = checkingAccountRepository.findByCustomerId(customerId);
        return account.map(CheckingAccount::getAccountBalance).orElse(BigDecimal.ZERO);
    }
}
