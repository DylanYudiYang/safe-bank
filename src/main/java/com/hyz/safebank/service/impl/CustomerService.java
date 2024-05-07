package com.hyz.safebank.service.impl;

import com.hyz.safebank.dto.*;
import com.hyz.safebank.entity.Customer;

import java.util.List;

public interface CustomerService {
    BankResponse createCustomer(CustomerRequest customerRequest);

    BankResponse getCustomer(CustomerIdRequest customerIdRequest);

    //login
    BankResponse loginCustomer(CustomerRequest customerRequest);

    BankResponse updateCustomer(CustomerUpdateRequest customerUpdateRequest);

    BankResponse deleteCustomer(CustomerIdRequest customerIdRequest);

    List<CustomerInfo> getAllCustomers();

    CustomerInfo convertToInfo(Customer customer);

    BankResponse addCustomer(CustomerAddRequest customerAddRequest);


}
