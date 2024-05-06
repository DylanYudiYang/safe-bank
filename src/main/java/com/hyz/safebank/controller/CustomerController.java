package com.hyz.safebank.controller;

import com.hyz.safebank.dto.*;
import com.hyz.safebank.service.impl.*;
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

    @Autowired
    PersonLoanAccountService personLoanAccountService;

    @Autowired
    HomeLoanAccountService homeLoanAccountService;

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

    @PostMapping("/checking-account/transfer")
    public BankResponse transfer(@RequestBody TransferRequest transferRequest) {
        return checkingAccountService.transfer(transferRequest);
    }

    @DeleteMapping("/checking-account")
    public BankResponse deleteCheckingAccount(@RequestBody EnquiryRequest enquiryRequest) {
        return checkingAccountService.deleteCheckingAccount(enquiryRequest);
    }

    @PostMapping("/saving-account")
    public BankResponse createSavingAccount(@RequestBody AccountRequest accountRequest) {
        return savingAccountService.createSavingAccount(accountRequest);
    }

    @GetMapping("/saving-account")
    public BankResponse getSavingAccount(@RequestBody AccountRequest accountRequest) {
        return savingAccountService.getSavingAccount(accountRequest);
    }

    @PostMapping("/saving-account/deposit")
    public BankResponse depositSavingAccount(@RequestBody DepositWithdrawRequest depositWithdrawRequest) {
        return savingAccountService.deposit(depositWithdrawRequest);
    }

    @PostMapping("/saving-account/withdraw")
    public BankResponse withdrawSavingAccount(@RequestBody DepositWithdrawRequest depositWithdrawRequest) {
        return savingAccountService.withdraw(depositWithdrawRequest);
    }

    @DeleteMapping("/saving-account")
    public BankResponse deleteSavingAccount(@RequestBody EnquiryRequest enquiryRequest) {
        return savingAccountService.deleteSavingAccount(enquiryRequest);
    }

    @PostMapping("/person-loan-account")
    public BankResponse createPersonLoanAccount(@RequestBody PersonLoanRequest personLoanRequest) {
        return personLoanAccountService.createPersonLoanAccount(personLoanRequest);
    }

    @GetMapping("/person-loan-account")
    public BankResponse getPersonLoanAccount(@RequestBody AccountRequest accountRequest) {
        return personLoanAccountService.getPersonLoanAccount(accountRequest);
    }

    @PostMapping("/person-loan-account/pay")
    public BankResponse payPersonLoan(@RequestBody LoanPaymentRequest loanPaymentRequest) {
        return personLoanAccountService.payPersonLoan(loanPaymentRequest);
    }

    @DeleteMapping("/person-loan-account")
    public BankResponse deletePersonLoanAccount(@RequestBody EnquiryRequest enquiryRequest) {
        return personLoanAccountService.deletePersonLoanAccount(enquiryRequest);
    }

    @PostMapping("/home-loan-account")
    public BankResponse createHomeLoanAccount(@RequestBody HomeLoanRequest homeLoanRequest) {
        return homeLoanAccountService.createHomeLoanAccount(homeLoanRequest);
    }

    @GetMapping("/home-loan-account")
    public BankResponse getHomeLoanAccount(@RequestBody AccountRequest accountRequest) {
        return homeLoanAccountService.getHomeLoanAccount(accountRequest);
    }

    @PostMapping("/home-loan-account/pay")
    public BankResponse payHomeLoan(@RequestBody LoanPaymentRequest loanPaymentRequest) {
        return homeLoanAccountService.payHomeLoan(loanPaymentRequest);
    }

    @DeleteMapping("/home-loan-account")
    public BankResponse deleteHomeLoanAccount(@RequestBody EnquiryRequest enquiryRequest) {
        return homeLoanAccountService.deleteHomeLoanAccount(enquiryRequest);
    }
}
