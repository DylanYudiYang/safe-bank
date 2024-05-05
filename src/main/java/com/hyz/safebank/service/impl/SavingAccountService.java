package com.hyz.safebank.service.impl;

import com.hyz.safebank.dto.AccountRequest;
import com.hyz.safebank.dto.BankResponse;
import com.hyz.safebank.dto.DepositWithdrawRequest;
import com.hyz.safebank.dto.EnquiryRequest;

public interface SavingAccountService {
    BankResponse createSavingAccount(AccountRequest accountRequest);

    BankResponse getSavingAccount(AccountRequest accountRequest);

    BankResponse deposit(DepositWithdrawRequest depositWithdrawRequest);

    BankResponse withdraw(DepositWithdrawRequest depositWithdrawRequest);

    //delete
    BankResponse deleteSavingAccount(EnquiryRequest enquiryRequest);

}
