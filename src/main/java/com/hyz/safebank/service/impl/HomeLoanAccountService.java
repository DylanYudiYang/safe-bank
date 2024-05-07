package com.hyz.safebank.service.impl;

import com.hyz.safebank.dto.*;
import com.hyz.safebank.entity.HomeLoanAccount;

import java.util.List;

public interface HomeLoanAccountService {

        BankResponse createHomeLoanAccount(HomeLoanRequest homeLoanRequest);
        BankResponse getHomeLoanAccount(AccountRequest accountRequest);

        //payloan
        BankResponse payHomeLoan(LoanPaymentRequest loanPaymentRequest);

        //delete
        BankResponse deleteHomeLoanAccount(EnquiryRequest enquiryRequest);

        List<LoanAccountInfo> getAllHomeLoanAccounts();

        LoanAccountInfo convertToInfo(HomeLoanAccount homeLoanAccount);
}
