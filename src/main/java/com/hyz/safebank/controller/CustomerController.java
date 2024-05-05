package com.hyz.safebank.controller;

import com.hyz.safebank.dto.AccountRequest;
import com.hyz.safebank.dto.BankResponse;
import com.hyz.safebank.dto.CustomerRequest;
import com.hyz.safebank.service.impl.CheckingAccountService;
import com.hyz.safebank.service.impl.CustomerService;
import com.hyz.safebank.service.impl.SavingAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @Autowired
    CheckingAccountService checkingAccountService;

    @Autowired
    SavingAccountService savingAccountService;

    @PostMapping("/customer")
    public BankResponse createCustomer(@RequestBody CustomerRequest customerRequest) {
        return customerService.createCustomer(customerRequest);
    }

    @PostMapping("/checking-account")
    public BankResponse createCheckingAccount(@RequestBody AccountRequest accountRequest) {
        return checkingAccountService.createCheckingAccount(accountRequest);
    }

    @PostMapping("/saving-account")
    public BankResponse createSavingAccount(@RequestBody AccountRequest accountRequest) {
        return savingAccountService.createSavingAccount(accountRequest);
    }
}
