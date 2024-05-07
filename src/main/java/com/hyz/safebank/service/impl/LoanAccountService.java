package com.hyz.safebank.service.impl;

import com.hyz.safebank.dto.AccountRequest;
import com.hyz.safebank.dto.BankResponse;
import com.hyz.safebank.dto.EnquiryRequest;

public interface LoanAccountService {

    BankResponse getLoanAccount(AccountRequest accountRequest);
}
