package com.hyz.safebank.service.impl;

import com.hyz.safebank.dto.BankResponse;
import com.hyz.safebank.dto.CustomerRequest;
import com.hyz.safebank.dto.EnquiryRequest;

public interface CustomerService {
    BankResponse createAccount(CustomerRequest customerRequest);


}
