package com.hyz.safebank.service.impl;

import com.hyz.safebank.dto.AccountRequest;
import com.hyz.safebank.dto.BankResponse;
import com.hyz.safebank.dto.DepositWithdrawRequest;
import com.hyz.safebank.dto.EnquiryRequest;

import java.math.BigDecimal;

public interface CheckingAccountService {

    BankResponse createCheckingAccount(AccountRequest accountRequest);
    BankResponse getCheckingAccount(AccountRequest accountRequest);

    BankResponse deposit(DepositWithdrawRequest depositWithdrawRequest);
    BankResponse withdraw(DepositWithdrawRequest depositWithdrawRequest);
}
