package com.hyz.safebank.service.impl;

import com.hyz.safebank.dto.*;

public interface StuLoanAccountService {

    BankResponse createStuLoanAccount(StuLoanRequest stuLoanRequest);
    BankResponse getStuLoanAccount(AccountRequest accountRequest);

    //payloan
    BankResponse payStuLoan(LoanPaymentRequest loanPaymentRequest);

    //delete
    BankResponse deleteStuLoanAccount(EnquiryRequest enquiryRequest);
}
