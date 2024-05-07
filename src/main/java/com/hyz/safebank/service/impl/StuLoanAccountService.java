package com.hyz.safebank.service.impl;

import com.hyz.safebank.dto.*;
import com.hyz.safebank.entity.StuLoanAccount;

import java.util.List;

public interface StuLoanAccountService {

    BankResponse createStuLoanAccount(StuLoanRequest stuLoanRequest);
    BankResponse getStuLoanAccount(AccountRequest accountRequest);

    //payloan
    BankResponse payStuLoan(LoanPaymentRequest loanPaymentRequest);

    //delete
    BankResponse deleteStuLoanAccount(EnquiryRequest enquiryRequest);

    List<LoanAccountInfo> getAllStuLoanAccounts();

    LoanAccountInfo convertToInfo(StuLoanAccount stuLoanAccount);
}
