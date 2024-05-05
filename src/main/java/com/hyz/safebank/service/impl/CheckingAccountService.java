package com.hyz.safebank.service.impl;

import com.hyz.safebank.dto.*;

import java.math.BigDecimal;

public interface CheckingAccountService {

    BankResponse createCheckingAccount(AccountRequest accountRequest);
    BankResponse getCheckingAccount(AccountRequest accountRequest);

    BankResponse deposit(DepositWithdrawRequest depositWithdrawRequest);
    BankResponse withdraw(DepositWithdrawRequest depositWithdrawRequest);

    BankResponse transfer(TransferRequest transferRequest);

    //delete
    BankResponse deleteCheckingAccount(EnquiryRequest enquiryRequest);
}
