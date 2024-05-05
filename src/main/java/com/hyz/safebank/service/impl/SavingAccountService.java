package com.hyz.safebank.service.impl;

import com.hyz.safebank.dto.AccountRequest;
import com.hyz.safebank.dto.BankResponse;

public interface SavingAccountService {
    public BankResponse createSavingAccount(AccountRequest accountRequest);
}
