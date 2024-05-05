package com.hyz.safebank.service.impl;

import com.hyz.safebank.dto.BankResponse;
import com.hyz.safebank.dto.CustomerRequest;

public interface CustomerService {
    BankResponse createCustomer(CustomerRequest customerRequest);


}
