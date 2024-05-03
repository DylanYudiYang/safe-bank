package com.hyz.safebank.service.impl;

import com.hyz.safebank.dto.*;
import com.hyz.safebank.entity.*;
import com.hyz.safebank.repository.CheckingAccountRepository;
import com.hyz.safebank.repository.CustomerRepository;
import com.hyz.safebank.repository.PersonLoanAccountRepository;
import com.hyz.safebank.repository.SavingAccountRepository;
import com.hyz.safebank.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CheckingAccountRepository checkingAccountRepository;

    @Autowired
    SavingAccountRepository savingAccountRepository;

    @Autowired
    PersonLoanAccountRepository personLoanAccountRepository;

    @Autowired
    CheckingAccountService checkingAccountService;

    @Autowired
    EmailService emailService;
    @Override
    public BankResponse createAccount(CustomerRequest customerRequest) {

        if (customerRepository.existsByEmail(customerRequest.getEmail())) {
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_EXISTS_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_EXISTS_MESSAGE)
                    .accountInfo(null)
                    .build();
        }


        //create a new customer
        Customer newCustomer = Customer.builder()
                .firstName(customerRequest.getFirstName())
                .lastName(customerRequest.getLastName())
                .email(customerRequest.getEmail())
                .street(customerRequest.getStreet())
                .city(customerRequest.getCity())
                .state(customerRequest.getState())
                .zipcode(customerRequest.getZipcode())
                .accounts(new HashSet<>())
//                .checkingAccountNumber(AccountUtils.generateAccountNumber())
//                .savingAccountNumber(AccountUtils.generateAccountNumber())
//                .loanAccountNumber(AccountUtils.generateAccountNumber())
//                .accountBalance(BigDecimal.ZERO)
                .status("ACTIVE")
                .build();

        Customer savedCustomer = customerRepository.save(newCustomer);

        CheckingAccount checkingAccount = CheckingAccount.builder()
                .customer(savedCustomer)
                .accountNumber(AccountUtils.generateAccountNumber())
                .accountName(savedCustomer.getFirstName()+ " " + savedCustomer.getLastName() + " Checking")
                .accountBalance(BigDecimal.ZERO)
                .openDate(LocalDate.of(2024, 5, 10))
                .accType("CHECKING")
                .serviceCharge(BigDecimal.ZERO)
                .build();

        SavingAccount savingAccount = SavingAccount.builder()
                .customer(savedCustomer)
                .accountNumber(AccountUtils.generateAccountNumber())
                .accountName(savedCustomer.getFirstName()+ " " + savedCustomer.getLastName() + " Saving")
                .accountBalance(BigDecimal.ZERO)
                .openDate(LocalDate.of(2024, 5, 10))
                .accType("SAVING")
                .interestRate(BigDecimal.ZERO)
                .build();

        InsuranceCompany insuranceCompany = InsuranceCompany.builder()
                .companyName("ssss")
                .street("sssss")
                .build();

//        LoanAccount loanAccount = LoanAccount.builder()
//                .customer(savedCustomer)
//                .accountNumber(AccountUtils.generateAccountNumber())
//                .accountName(savedCustomer.getFirstName()+ " " + savedCustomer.getLastName() + " Loan")
//                .accountBalance(BigDecimal.ZERO)
//                .openDate(LocalDate.of(2024, 5, 10))
//                .accType("LOAN")
//                .loanAmount(BigDecimal.ZERO)
//                .loanRate(0)
//                .loanMonths(0)
//                .loanType("STUDENT")
//                .build();



        CheckingAccount savedCheckingAccount = checkingAccountRepository.save(checkingAccount);
        SavingAccount savedSavingAccount = savingAccountRepository.save(savingAccount);
//        LoanAccount savedLoanAccount = loanAccountRepository.save(loanAccount);

        savedCustomer.addAccount(checkingAccount);
        savedCustomer.addAccount(savingAccount);
//        savedCustomer.addAccount(loanAccount);


        //Send email
        EmailDetails emailDetails = EmailDetails.builder()
                .recipient(savedCustomer.getEmail())
                .subject("Safe Bank Account Creation")
                .messageBody("You Account Has Been Successfully Created!\nAccount Detail:\n" +
                        "Account Name: " + savedCustomer.getFirstName()+ " " + savedCustomer.getLastName() +
                        "\nChecking Account Number: " + savedCheckingAccount.getAccountNumber())
                .build();
        emailService.sendEmailAlert(emailDetails);

//        System.out.println(accountService.getCheckingAccountBalance(savedCustomer.getId()));

        return BankResponse.builder()
                .responseCode(AccountUtils.ACCOUNT_CREATION_SUCCESS)
                .responseMessage(AccountUtils.ACCOUNT_CREATION_MESSAGE)
                .accountInfo(AccountInfo.builder()
                        .accountBalance(savedCheckingAccount.getAccountBalance())
                        .checkingAccountNumber(savedCheckingAccount.getAccountNumber())
                        .savingAccountNumber((savedSavingAccount.getAccountNumber()))
                        .loanAccountNumber(null)
                        .accountName(savedCustomer.getFirstName() + " " + savedCustomer.getLastName())
                        .build())
                .build();
    }

}
