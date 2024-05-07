package com.hyz.safebank.service.impl;

import com.hyz.safebank.dto.*;
import com.hyz.safebank.entity.CheckingAccount;

import java.math.BigDecimal;
import java.util.List;

public interface CheckingAccountService {

    BankResponse createCheckingAccount(AccountRequest accountRequest);
    BankResponse getCheckingAccount(AccountRequest accountRequest);

    BankResponse deposit(DepositWithdrawRequest depositWithdrawRequest);
    BankResponse withdraw(DepositWithdrawRequest depositWithdrawRequest);

    BankResponse transfer(TransferRequest transferRequest);

    //delete
    BankResponse deleteCheckingAccount(EnquiryRequest enquiryRequest);

    List<CheckingAccountInfo> getAllCheckingAccounts();

    CheckingAccountInfo convertToInfo(CheckingAccount checkingAccount);
}
