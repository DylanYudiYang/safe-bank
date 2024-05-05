package com.hyz.safebank.controller;

import com.hyz.safebank.dto.*;
import com.hyz.safebank.service.impl.CheckingAccountService;
import com.hyz.safebank.service.impl.CustomerService;
import com.hyz.safebank.service.impl.SavingAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/checking-account")
    public BankResponse getCheckingAccount(@RequestBody AccountRequest accountRequest) {
        return checkingAccountService.getCheckingAccount(accountRequest);
    }

    @PostMapping("/checking-account/deposit")
    public BankResponse deposit(@RequestBody DepositWithdrawRequest depositWithdrawRequest) {
        return checkingAccountService.deposit(depositWithdrawRequest);
    }

    @PostMapping("/checking-account/withdraw")
    public BankResponse withdraw(@RequestBody DepositWithdrawRequest depositWithdrawRequest) {
        return checkingAccountService.withdraw(depositWithdrawRequest);
    }

    @PostMapping("/saving-account")
    public BankResponse createSavingAccount(@RequestBody AccountRequest accountRequest) {
        return savingAccountService.createSavingAccount(accountRequest);
    }
}
