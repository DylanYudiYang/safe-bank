package com.hyz.safebank.service.impl;

import com.hyz.safebank.dto.*;

public interface HomeLoanAccountService {

        BankResponse createHomeLoanAccount(HomeLoanRequest homeLoanRequest);
        BankResponse getHomeLoanAccount(AccountRequest accountRequest);

        //payloan
        BankResponse payHomeLoan(LoanPaymentRequest loanPaymentRequest);

        //delete
        BankResponse deleteHomeLoanAccount(EnquiryRequest enquiryRequest);
}
