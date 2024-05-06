package com.hyz.safebank.service.impl;

import com.hyz.safebank.dto.*;
import com.hyz.safebank.entity.Customer;
import com.hyz.safebank.entity.HomeLoanAccount;
import com.hyz.safebank.entity.InsuranceCompany;
import com.hyz.safebank.repository.CustomerRepository;
import com.hyz.safebank.repository.HomeLoanAccountRepository;
import com.hyz.safebank.repository.InsuranceCompanyRepository;
import com.hyz.safebank.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;

@Service
public class HomeLoanAccountServiceImpl implements HomeLoanAccountService{

    @Autowired
    private HomeLoanAccountRepository homeLoanAccountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private InsuranceCompanyRepository insuranceCompanyRepository;

    @Override
    public BankResponse createHomeLoanAccount(HomeLoanRequest homeLoanRequest) {
        Long customerId = homeLoanRequest.getCustomerId();
        if (homeLoanAccountRepository.existsByCustomerId(customerId)) {
            return BankResponse.builder()
                    .responseCode("001")
                    .responseMessage("Account exists")
                    .customerInfo(null)
                    .build();
        }

        Customer theCustomer = customerRepository.findById(customerId).orElse(null);

        InsuranceCompany insuranceCompany = insuranceCompanyRepository.findByCompanyName(homeLoanRequest.getInsuranceCompanyName()).orElse(null);

        if (insuranceCompany == null) {
            InsuranceCompany newInsuranceCompany = InsuranceCompany.builder()
                    .companyName(homeLoanRequest.getInsuranceCompanyName())
                    .street("250 Jay Street")
                    .city("Brooklyn")
                    .state("NY")
                    .zipcode("11204")
                    .homeLoanAccounts(new HashSet<>())  // Initialize an empty set for related entities
                    .build();

            insuranceCompany = insuranceCompanyRepository.save(newInsuranceCompany);
        }


        HomeLoanAccount homeLoanAccount = HomeLoanAccount.builder()
                .customer(theCustomer)
                .accountNumber(AccountUtils.generateAccountNumber())
                .accountName(theCustomer.getFirstName()+ " " + theCustomer.getLastName() + " Home Loan")
                .openDate(LocalDate.now())
                .accType("LOAN")
                .loanAmount(homeLoanRequest.getLoanAmount())
                .loanRate(0)
                .loanMonths(homeLoanRequest.getLoanMonths())
                .loanType("HOME")
                .houseBuiltDate(homeLoanRequest.getHouseBuiltDate())
                .insuranceAccountNumber(AccountUtils.generateInsuranceNumber())
                .yearlyInsuranceFee(0)
                .insuranceCompany(insuranceCompany)
                .build();

        HomeLoanAccount savedHomeLoanAccount = homeLoanAccountRepository.save(homeLoanAccount);

        return BankResponse.builder()
                .responseCode(AccountUtils.ACCOUNT_CREATION_SUCCESS)
                .responseMessage(AccountUtils.ACCOUNT_CREATION_MESSAGE)
                .loanAccountInfo(LoanAccountInfo.builder()
                        .accountId(savedHomeLoanAccount.getId())
                        .accountNumber(savedHomeLoanAccount.getAccountNumber())
                        .accountName(savedHomeLoanAccount.getAccountName())
                        .openDate(savedHomeLoanAccount.getOpenDate())
                        .accType(savedHomeLoanAccount.getAccType())
                        .loanAmount(savedHomeLoanAccount.getLoanAmount())
                        .loanRate(savedHomeLoanAccount.getLoanRate())
                        .loanMonths(savedHomeLoanAccount.getLoanMonths())
                        .loanType(savedHomeLoanAccount.getLoanType())
                        .houseBuiltDate(savedHomeLoanAccount.getHouseBuiltDate())
                        .insuranceAccountNumber(savedHomeLoanAccount.getInsuranceAccountNumber())
                        .yearlyInsuranceFee(savedHomeLoanAccount.getYearlyInsuranceFee())
                        .insuranceCompanyName(savedHomeLoanAccount.getInsuranceCompany().getCompanyName())
                        .build())
                .build();

    }

    @Override
    public BankResponse getHomeLoanAccount(AccountRequest accountRequest) {
        if (!homeLoanAccountRepository.existsByCustomerId(accountRequest.getCustomerId())) {
            return BankResponse.builder()
                    .responseCode("001")
                    .responseMessage("Account does not exist")
                    .loanAccountInfo(null)
                    .build();
        }

        HomeLoanAccount homeLoanAccount = homeLoanAccountRepository.findByCustomerId(accountRequest.getCustomerId()).orElse(null);

        return BankResponse.builder()
                .responseCode("002")
                .responseMessage("Account found")
                .loanAccountInfo(LoanAccountInfo.builder()
                        .accountId(homeLoanAccount.getId())
                        .accountNumber(homeLoanAccount.getAccountNumber())
                        .accountName(homeLoanAccount.getAccountName())
                        .openDate(homeLoanAccount.getOpenDate())
                        .accType(homeLoanAccount.getAccType())
                        .loanAmount(homeLoanAccount.getLoanAmount())
                        .loanRate(homeLoanAccount.getLoanRate())
                        .loanMonths(homeLoanAccount.getLoanMonths())
                        .loanType(homeLoanAccount.getLoanType())
                        .houseBuiltDate(homeLoanAccount.getHouseBuiltDate())
                        .insuranceAccountNumber(homeLoanAccount.getInsuranceAccountNumber())
                        .yearlyInsuranceFee(homeLoanAccount.getYearlyInsuranceFee())
                        .insuranceCompanyName(homeLoanAccount.getInsuranceCompany().getCompanyName())
                        .build())
                .build();
    }

    @Override
    public BankResponse payHomeLoan(LoanPaymentRequest loanPaymentRequest) {
        if (!homeLoanAccountRepository.existsById(loanPaymentRequest.getAccountId())) {
            return BankResponse.builder()
                    .responseCode("001")
                    .responseMessage("Account does not exist")
                    .loanAccountInfo(null)
                    .build();
        }
        Optional<HomeLoanAccount> homeLoanAccount = homeLoanAccountRepository.findById(loanPaymentRequest.getAccountId());
        if (loanPaymentRequest.getPaymentAmount().compareTo(BigDecimal.ZERO) <= 0 || loanPaymentRequest.getPaymentAmount().compareTo(homeLoanAccount.get().getLoanAmount()) > 0) {
            return BankResponse.builder()
                    .responseCode("001")
                    .responseMessage("Invalid payment amount")
                    .loanAccountInfo(null)
                    .build();
        }


        homeLoanAccount.get().setLoanAmount(homeLoanAccount.get().getLoanAmount().subtract(loanPaymentRequest.getPaymentAmount()));
        homeLoanAccountRepository.save(homeLoanAccount.get());

        return BankResponse.builder()
                .responseCode("002")
                .responseMessage("Payment successful")
                .loanAccountInfo(LoanAccountInfo.builder()
                        .accountId(homeLoanAccount.get().getId())
                        .accountNumber(homeLoanAccount.get().getAccountNumber())
                        .accountName(homeLoanAccount.get().getAccountName())
                        .openDate(homeLoanAccount.get().getOpenDate())
                        .accType(homeLoanAccount.get().getAccType())
                        .loanAmount(homeLoanAccount.get().getLoanAmount())
                        .loanRate(homeLoanAccount.get().getLoanRate())
                        .loanMonths(homeLoanAccount.get().getLoanMonths())
                        .loanType(homeLoanAccount.get().getLoanType())
                        .houseBuiltDate(homeLoanAccount.get().getHouseBuiltDate())
                        .insuranceAccountNumber(homeLoanAccount.get().getInsuranceAccountNumber())
                        .yearlyInsuranceFee(homeLoanAccount.get().getYearlyInsuranceFee())
                        .insuranceCompanyName(homeLoanAccount.get().getInsuranceCompany().getCompanyName())
                        .build())
                .build();
    }

    @Override
    public BankResponse deleteHomeLoanAccount(EnquiryRequest enquiryRequest) {
        if (!homeLoanAccountRepository.existsById(enquiryRequest.getAccountId())) {
            return BankResponse.builder()
                    .responseCode("001")
                    .responseMessage("Account does not exist")
                    .loanAccountInfo(null)
                    .build();
        }

        homeLoanAccountRepository.deleteById(enquiryRequest.getAccountId());

        return BankResponse.builder()
                .responseCode("002")
                .responseMessage("Account deleted")
                .loanAccountInfo(null)
                .build();
    }
}
