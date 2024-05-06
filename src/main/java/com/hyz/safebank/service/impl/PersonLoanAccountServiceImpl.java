package com.hyz.safebank.service.impl;

import com.hyz.safebank.dto.*;
import com.hyz.safebank.entity.Customer;
import com.hyz.safebank.entity.PersonLoanAccount;
import com.hyz.safebank.repository.CustomerRepository;
import com.hyz.safebank.repository.PersonLoanAccountRepository;
import com.hyz.safebank.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class PersonLoanAccountServiceImpl implements PersonLoanAccountService{

    @Autowired
    private PersonLoanAccountRepository personLoanAccountRepository;

    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public BankResponse createPersonLoanAccount(PersonLoanRequest personLoanRequest) {
        Long customerId = personLoanRequest.getCustomerId();
        if (personLoanAccountRepository.existsByCustomerId(customerId)) {
            return BankResponse.builder()
                    .responseCode("001")
                    .responseMessage("Account exists")
                    .customerInfo(null)
                    .build();
        }

        Customer theCustomer = customerRepository.findById(customerId).orElse(null);

        PersonLoanAccount personLoanAccount = PersonLoanAccount.builder()
                .customer(theCustomer)
                .accountNumber(AccountUtils.generateAccountNumber())
                .accountName(theCustomer.getFirstName()+ " " + theCustomer.getLastName() + " Person Loan")
                .openDate(LocalDate.now())
                .accType("LOAN")
                .loanAmount(personLoanRequest.getLoanAmount())
                .loanRate(0)
                .loanMonths(personLoanRequest.getLoanMonths())
                .loanType("PERSON")
                .description(theCustomer.getFirstName()+ " " + theCustomer.getLastName() + " Person Loan")
                .build();

        PersonLoanAccount savedPersonLoanAccount = personLoanAccountRepository.save(personLoanAccount);
        return BankResponse.builder()
                .responseCode(AccountUtils.ACCOUNT_CREATION_SUCCESS)
                .responseMessage(AccountUtils.ACCOUNT_CREATION_MESSAGE)
                .loanAccountInfo(LoanAccountInfo.builder()
                        .accountId(savedPersonLoanAccount.getId())
                        .accountNumber(savedPersonLoanAccount.getAccountNumber())
                        .accountName(savedPersonLoanAccount.getAccountName())
                        .openDate(savedPersonLoanAccount.getOpenDate())
                        .accType(savedPersonLoanAccount.getAccType())
                        .loanAmount(savedPersonLoanAccount.getLoanAmount())
                        .loanRate(savedPersonLoanAccount.getLoanRate())
                        .loanMonths(savedPersonLoanAccount.getLoanMonths())
                        .loanType(savedPersonLoanAccount.getLoanType())
                        .description(savedPersonLoanAccount.getDescription())
                        .build())
                .build();
    }

    @Override
    public BankResponse getPersonLoanAccount(AccountRequest accountRequest) {
        if (!personLoanAccountRepository.existsByCustomerId(accountRequest.getCustomerId())) {
            return BankResponse.builder()
                    .responseCode("001")
                    .responseMessage("Account does not exist")
                    .loanAccountInfo(null)
                    .build();
        }

        Optional<PersonLoanAccount> personLoanAccount = personLoanAccountRepository.findByCustomerId(accountRequest.getCustomerId());

        return BankResponse.builder()
                .responseCode("002")
                .responseMessage("Account found")
                .loanAccountInfo(LoanAccountInfo.builder()
                        .accountId(personLoanAccount.get().getId())
                        .accountNumber(personLoanAccount.get().getAccountNumber())
                        .accountName(personLoanAccount.get().getAccountName())
                        .openDate(personLoanAccount.get().getOpenDate())
                        .accType(personLoanAccount.get().getAccType())
                        .loanAmount(personLoanAccount.get().getLoanAmount())
                        .loanRate(personLoanAccount.get().getLoanRate())
                        .loanMonths(personLoanAccount.get().getLoanMonths())
                        .loanType(personLoanAccount.get().getLoanType())
                        .description(personLoanAccount.get().getDescription())
                        .build())
                .build();
    }

    @Override
    public BankResponse payLoan(LoanPaymentRequest loanPaymentRequest) {
        //reduce loan amount by payment amount
        if (!personLoanAccountRepository.existsById(loanPaymentRequest.getAccountId())) {
            return BankResponse.builder()
                    .responseCode("001")
                    .responseMessage("Account does not exist")
                    .loanAccountInfo(null)
                    .build();
        }
         if (loanPaymentRequest.getPaymentAmount().compareTo(BigDecimal.ZERO) <= 0) {
            return BankResponse.builder()
                    .responseCode("001")
                    .responseMessage("Invalid payment amount")
                    .loanAccountInfo(null)
                    .build();
         }
        Optional<PersonLoanAccount> personLoanAccount = personLoanAccountRepository.findById(loanPaymentRequest.getAccountId());
        personLoanAccount.get().setLoanAmount(personLoanAccount.get().getLoanAmount().subtract(loanPaymentRequest.getPaymentAmount()));
        personLoanAccountRepository.save(personLoanAccount.get());

        return BankResponse.builder()
                .responseCode("002")
                .responseMessage("Payment successful")
                .loanAccountInfo(LoanAccountInfo.builder()
                        .accountId(personLoanAccount.get().getId())
                        .accountNumber(personLoanAccount.get().getAccountNumber())
                        .accountName(personLoanAccount.get().getAccountName())
                        .openDate(personLoanAccount.get().getOpenDate())
                        .accType(personLoanAccount.get().getAccType())
                        .loanAmount(personLoanAccount.get().getLoanAmount())
                        .loanRate(personLoanAccount.get().getLoanRate())
                        .loanMonths(personLoanAccount.get().getLoanMonths())
                        .loanType(personLoanAccount.get().getLoanType())
                        .description(personLoanAccount.get().getDescription())
                        .build())
                .build();
    }



    @Override
    public BankResponse deletePersonLoanAccount(EnquiryRequest enquiryRequest) {
        if (!personLoanAccountRepository.existsById(enquiryRequest.getAccountId())) {
            return BankResponse.builder()
                    .responseCode("001")
                    .responseMessage("Account does not exist")
                    .loanAccountInfo(null)
                    .build();
        }

        personLoanAccountRepository.deleteById(enquiryRequest.getAccountId());

        return BankResponse.builder()
                .responseCode("002")
                .responseMessage("Account deleted")
                .loanAccountInfo(null)
                .build();
    }
}
