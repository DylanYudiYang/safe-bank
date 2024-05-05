package com.hyz.safebank.service.impl;

import com.hyz.safebank.dto.*;
import com.hyz.safebank.entity.*;
import com.hyz.safebank.repository.*;
import com.hyz.safebank.utils.AccountUtils;
import com.hyz.safebank.utils.CustomerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CheckingAccountRepository checkingAccountRepository;

    @Autowired
    HomeLoanAccountRepository homeLoanAccountRepository;

    @Autowired
    SavingAccountRepository savingAccountRepository;

    @Autowired
    InsuranceCompanyRepository insuranceCompanyRepository;


    @Autowired
    CheckingAccountService checkingAccountService;

    @Autowired
    EmailService emailService;
    @Override
    public BankResponse createCustomer(CustomerRequest customerRequest) {

        if (customerRepository.existsByEmail(customerRequest.getEmail())) {
            return BankResponse.builder()
                    .responseCode(CustomerUtils.CUSTOMER_EXISTS_CODE)
                    .responseMessage(CustomerUtils.CUSTOMER_EXISTS_MESSAGE)
                    .customerInfo(null)
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
                .status("ACTIVE")
                .build();

        Customer savedCustomer = customerRepository.save(newCustomer);

//        CheckingAccount checkingAccount = CheckingAccount.builder()
//                .customer(savedCustomer)
//                .accountNumber(AccountUtils.generateAccountNumber())
//                .accountName(savedCustomer.getFirstName()+ " " + savedCustomer.getLastName() + " Checking")
//                .accountBalance(BigDecimal.ZERO)
//                .openDate(LocalDate.of(2024, 5, 10))
//                .accType("CHECKING")
//                .serviceCharge(BigDecimal.ZERO)
//                .build();
//
//        SavingAccount savingAccount = SavingAccount.builder()
//                .customer(savedCustomer)
//                .accountNumber(AccountUtils.generateAccountNumber())
//                .accountName(savedCustomer.getFirstName()+ " " + savedCustomer.getLastName() + " Saving")
//                .accountBalance(BigDecimal.ZERO)
//                .openDate(LocalDate.of(2024, 5, 10))
//                .accType("SAVING")
//                .interestRate(BigDecimal.ZERO)
//
//                .build();
//
//        HomeLoanAccount homeLoanAccount = HomeLoanAccount.builder()
//                .customer(savedCustomer)
//                .accountNumber(AccountUtils.generateAccountNumber())
//                .accountName(savedCustomer.getFirstName()+ " " + savedCustomer.getLastName() + " Home Loan")
//                .openDate(LocalDate.of(2024, 5, 10))
//                .accType("HOME_LOAN")
//                .loanAmount(BigDecimal.ZERO)
//                .loanRate(1.3)
//                .loanMonths(2)
//                .loanType("HOME")
//                .houseBuiltDate(LocalDate.of(2024, 5, 10))
//                .insuranceAccountNumber("2011201299")
//                .yearlyInsuranceFee(1000)
//                .insuranceCompany(insuranceCompanyRepository.findById(1L).get())
//                .build();
//
//        HomeLoanAccount savedHomeLoanAccount = homeLoanAccountRepository.save(homeLoanAccount);
//
//
//
//
//
//
//        CheckingAccount savedCheckingAccount = checkingAccountRepository.save(checkingAccount);
//
//        SavingAccount savedSavingAccount = savingAccountRepository.save(savingAccount);
//
//        savedCustomer.addAccount(savedCheckingAccount);
//        savedCustomer.addAccount(savedSavingAccount);
//        savedCustomer.addAccount(savedHomeLoanAccount);


        //Send email
        EmailDetails emailDetails = EmailDetails.builder()
                .recipient(savedCustomer.getEmail())
                .subject("Safe Bank Account Creation")
                .messageBody("You Account Has Been Successfully Created!\nAccount Detail:\n" +
                        "Account Name: " + savedCustomer.getFirstName()+ " " + savedCustomer.getLastName())
                .build();
        emailService.sendEmailAlert(emailDetails);


        return BankResponse.builder()
                .responseCode(CustomerUtils.CUSTOMER_CREATION_SUCCESS)
                .responseMessage(CustomerUtils.CUSTOMER_CREATION_MESSAGE)
                .customerInfo(CustomerInfo.builder()
                        .customerId(savedCustomer.getId())
                        .customerName(savedCustomer.getFirstName() + " " + savedCustomer.getLastName())
                        .build())
                .build();
    }

}
