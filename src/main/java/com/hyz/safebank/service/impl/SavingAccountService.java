package com.hyz.safebank.service.impl;

import com.hyz.safebank.dto.*;
import com.hyz.safebank.entity.SavingAccount;

import java.util.List;

public interface SavingAccountService {
    BankResponse createSavingAccount(AccountRequest accountRequest);

    BankResponse getSavingAccount(AccountRequest accountRequest);

    BankResponse deposit(DepositWithdrawRequest depositWithdrawRequest);

    BankResponse withdraw(DepositWithdrawRequest depositWithdrawRequest);

    //delete
    BankResponse deleteSavingAccount(EnquiryRequest enquiryRequest);

    List<SavingAccountInfo> getAllSavingAccounts();

    SavingAccountInfo convertToInfo(SavingAccount savingAccount);

}
