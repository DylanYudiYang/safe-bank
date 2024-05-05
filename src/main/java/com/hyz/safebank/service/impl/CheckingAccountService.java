package com.hyz.safebank.service.impl;

import com.hyz.safebank.dto.AccountRequest;
import com.hyz.safebank.dto.BankResponse;

import java.math.BigDecimal;

public interface CheckingAccountService {

    BankResponse createCheckingAccount(AccountRequest accountRequest);
    BigDecimal getCheckingAccountBalance(Long customerId);
}
