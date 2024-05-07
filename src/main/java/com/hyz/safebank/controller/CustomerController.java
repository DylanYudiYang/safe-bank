package com.hyz.safebank.controller;

import com.hyz.safebank.dto.*;
import com.hyz.safebank.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
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

    @Autowired
    StuLoanAccountService stuLoanAccountService;

    @Autowired
    LoanAccountService loanAccountService;

    @PostMapping("/register")
    public BankResponse createCustomer(@RequestBody CustomerRequest customerRequest) {
        return customerService.createCustomer(customerRequest);
    }

    @PostMapping("/customer/get")
    public BankResponse getCustomer(@RequestBody CustomerIdRequest customerIdRequest) {
        return customerService.getCustomer(customerIdRequest);
    }

    @PostMapping("/login")
    public BankResponse loginCustomer(@RequestBody CustomerRequest customerRequest) {
        return customerService.loginCustomer(customerRequest);
    }

    @PostMapping("/customer/update")
    public BankResponse updateCustomer(@RequestBody CustomerUpdateRequest customerUpdateRequest) {
        return customerService.updateCustomer(customerUpdateRequest);
    }

    @PostMapping("/customer/delete")
    public BankResponse deleteCustomer(@RequestBody CustomerIdRequest customerIdRequest) {
        return customerService.deleteCustomer(customerIdRequest);
    }

    @PostMapping("/customer/all")
    public List<CustomerInfo> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @PostMapping("/checking-account/create")
    public BankResponse createCheckingAccount(@RequestBody AccountRequest accountRequest) {
        return checkingAccountService.createCheckingAccount(accountRequest);
    }

    @PostMapping("/checking-account/get")
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

    @PostMapping("/checking-account/delete")
    public BankResponse deleteCheckingAccount(@RequestBody EnquiryRequest enquiryRequest) {
        return checkingAccountService.deleteCheckingAccount(enquiryRequest);
    }


    @PostMapping("/saving-account/create")
    public BankResponse createSavingAccount(@RequestBody AccountRequest accountRequest) {
        return savingAccountService.createSavingAccount(accountRequest);
    }

    @PostMapping("/saving-account/get")
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

    @PostMapping("/saving-account/delete")
    public BankResponse deleteSavingAccount(@RequestBody EnquiryRequest enquiryRequest) {
        return savingAccountService.deleteSavingAccount(enquiryRequest);
    }

    @PostMapping("/person-loan-account/create")
    public BankResponse createPersonLoanAccount(@RequestBody PersonLoanRequest personLoanRequest) {
        return personLoanAccountService.createPersonLoanAccount(personLoanRequest);
    }

    @PostMapping("/person-loan-account/get")
    public BankResponse getPersonLoanAccount(@RequestBody AccountRequest accountRequest) {
        return personLoanAccountService.getPersonLoanAccount(accountRequest);
    }

    @PostMapping("/person-loan-account/pay")
    public BankResponse payPersonLoan(@RequestBody LoanPaymentRequest loanPaymentRequest) {
        return personLoanAccountService.payPersonLoan(loanPaymentRequest);
    }

    @PostMapping("/person-loan-account/delete")
    public BankResponse deletePersonLoanAccount(@RequestBody EnquiryRequest enquiryRequest) {
        return personLoanAccountService.deletePersonLoanAccount(enquiryRequest);
    }

    @PostMapping("/home-loan-account/create")
    public BankResponse createHomeLoanAccount(@RequestBody HomeLoanRequest homeLoanRequest) {
        return homeLoanAccountService.createHomeLoanAccount(homeLoanRequest);
    }

    @PostMapping("/home-loan-account/get")
    public BankResponse getHomeLoanAccount(@RequestBody AccountRequest accountRequest) {
        return homeLoanAccountService.getHomeLoanAccount(accountRequest);
    }

    @PostMapping("/home-loan-account/pay")
    public BankResponse payHomeLoan(@RequestBody LoanPaymentRequest loanPaymentRequest) {
        return homeLoanAccountService.payHomeLoan(loanPaymentRequest);
    }

    @PostMapping("/home-loan-account/delete")
    public BankResponse deleteHomeLoanAccount(@RequestBody EnquiryRequest enquiryRequest) {
        return homeLoanAccountService.deleteHomeLoanAccount(enquiryRequest);
    }

    @PostMapping("/stu-loan-account/create")
    public BankResponse createStuLoanAccount(@RequestBody StuLoanRequest stuLoanRequest) {
        return stuLoanAccountService.createStuLoanAccount(stuLoanRequest);
    }

    @PostMapping("/stu-loan-account/get")
    public BankResponse getStuLoanAccount(@RequestBody AccountRequest accountRequest) {
        return stuLoanAccountService.getStuLoanAccount(accountRequest);
    }

    @PostMapping("/stu-loan-account/pay")
    public BankResponse payStuLoan(@RequestBody LoanPaymentRequest loanPaymentRequest) {
        return stuLoanAccountService.payStuLoan(loanPaymentRequest);
    }

    @PostMapping("/stu-loan-account/delete")
    public BankResponse deleteStuLoanAccount(@RequestBody EnquiryRequest enquiryRequest) {
        return stuLoanAccountService.deleteStuLoanAccount(enquiryRequest);
    }

    @PostMapping("/get-loan-account")
    public BankResponse getLoanAccount(@RequestBody AccountRequest accountRequest) {
        return loanAccountService.getLoanAccount(accountRequest);
    }
}
