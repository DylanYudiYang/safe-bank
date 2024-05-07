package com.hyz.safebank.service.impl;

import com.hyz.safebank.dto.*;
import com.hyz.safebank.entity.PersonLoanAccount;

import java.util.List;

public interface PersonLoanAccountService {
    BankResponse createPersonLoanAccount(PersonLoanRequest personLoanRequest);
    BankResponse getPersonLoanAccount(AccountRequest accountRequest);

    //payloan
    BankResponse payPersonLoan(LoanPaymentRequest loanPaymentRequest);
    BankResponse deletePersonLoanAccount(EnquiryRequest enquiryRequest);

    //get all
    List<LoanAccountInfo> getAllPersonLoanAccounts();

    LoanAccountInfo convertToInfo(PersonLoanAccount personLoanAccount);

}
