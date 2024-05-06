package com.hyz.safebank.service.impl;

import com.hyz.safebank.dto.*;

public interface PersonLoanAccountService {
    BankResponse createPersonLoanAccount(PersonLoanRequest personLoanRequest);
    BankResponse getPersonLoanAccount(AccountRequest accountRequest);

    //payloan
    BankResponse payPersonLoan(LoanPaymentRequest loanPaymentRequest);
    BankResponse deletePersonLoanAccount(EnquiryRequest enquiryRequest);

}
