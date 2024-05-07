package com.hyz.safebank.controller;

import com.hyz.safebank.dto.*;
import com.hyz.safebank.entity.InsuranceCompany;
import com.hyz.safebank.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private InsuranceCompanyService insuranceCompanyService;

    @Autowired
    private UniversityService universityService;

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


    @PostMapping("insurance-company/create")
    public BankResponse createInsuranceCompany(@RequestBody InsuranceCompanyRequest insuranceCompanyRequest) {
        return insuranceCompanyService.createInsuranceCompany(insuranceCompanyRequest);
    }

    @PostMapping("insurance-company/get")
    public BankResponse getInsuranceCompany(@RequestBody InsuranceCompanyIdRequest insuranceCompanyIdRequest) {
        return insuranceCompanyService.getInsuranceCompany(insuranceCompanyIdRequest);
    }

    @PostMapping("insurance-company/update")
    public BankResponse updateInsuranceCompany(@RequestBody InsuranceCompanyUpdateRequest insuranceCompanyUpdateRequest) {
        return insuranceCompanyService.updateInsuranceCompany(insuranceCompanyUpdateRequest);
    }

    @PostMapping("insurance-company/delete")
    public BankResponse deleteInsuranceCompany(@RequestBody InsuranceCompanyIdRequest insuranceCompanyIdRequest) {
        return insuranceCompanyService.deleteInsuranceCompany(insuranceCompanyIdRequest);
    }

    @GetMapping("insurance-company/all")
    public List<InsuranceCompanyInfo> getAllInsuranceCompanies() {
        return insuranceCompanyService.getAllInsuranceCompanies();
    }

    @PostMapping("university/create")
    public BankResponse createUniversity(@RequestBody UniversityRequest universityRequest) {
        return universityService.createUniversity(universityRequest);
    }

    @PostMapping("university/get")
    public BankResponse getUniversity(@RequestBody UniversityIdRequest universityIdRequest) {
        return universityService.getUniversity(universityIdRequest);
    }

    @PostMapping("university/update")
    public BankResponse updateUniversity(@RequestBody UniversityUpdateRequest universityUpdateRequest) {
        return universityService.updateUniversity(universityUpdateRequest);
    }

    @PostMapping("university/delete")
    public BankResponse deleteUniversity(@RequestBody UniversityIdRequest universityIdRequest) {
        return universityService.deleteUniversity(universityIdRequest);
    }

    @GetMapping("university/all")
    public List<UniversityInfo> getAllUniversities() {
        return universityService.getAllUniversities();
    }


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

    @PostMapping("/checking-account/all")
    public List<CheckingAccountInfo> getAllCheckingAccounts() {
        return checkingAccountService.getAllCheckingAccounts();
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

    @PostMapping("/saving-account/all")
    public List<SavingAccountInfo> getAllSavingAccounts() {
        return savingAccountService.getAllSavingAccounts();
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

    @PostMapping("/person-loan-account/all")
    public List<LoanAccountInfo> getAllPersonLoanAccounts() {
        return personLoanAccountService.getAllPersonLoanAccounts();
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

    @PostMapping("/home-loan-account/all")
    public List<LoanAccountInfo> getAllHomeLoanAccounts() {
        return homeLoanAccountService.getAllHomeLoanAccounts();
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

    @PostMapping("/stu-loan-account/all")
    public List<LoanAccountInfo> getAllStuLoanAccounts() {
        return stuLoanAccountService.getAllStuLoanAccounts();
    }

    @PostMapping("/get-loan-account")
    public BankResponse getLoanAccount(@RequestBody AccountRequest accountRequest) {
        return loanAccountService.getLoanAccount(accountRequest);
    }

    @PostMapping("/add-customer")
    public BankResponse addCustomer(@RequestBody CustomerAddRequest customerAddRequest) {
        return customerService.addCustomer(customerAddRequest);
    }


}
