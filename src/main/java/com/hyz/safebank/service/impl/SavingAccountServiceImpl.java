package com.hyz.safebank.service.impl;

import com.hyz.safebank.dto.AccountRequest;
import com.hyz.safebank.dto.BankResponse;
import com.hyz.safebank.dto.SavingAccountInfo;
import com.hyz.safebank.entity.Customer;
import com.hyz.safebank.entity.SavingAccount;
import com.hyz.safebank.repository.SavingAccountRepository;
import com.hyz.safebank.repository.CustomerRepository;
import com.hyz.safebank.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class SavingAccountServiceImpl implements SavingAccountService{
    @Autowired
    private SavingAccountRepository savingAccountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public BankResponse createSavingAccount(AccountRequest accountRequest) {
        Long customerId = accountRequest.getCustomerId();
        if (savingAccountRepository.existsByCustomerId(customerId)) {
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_EXISTS_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_EXISTS_MESSAGE)
                    .customerInfo(null)
                    .build();
        }

        Customer theCustomer = customerRepository.findById(customerId).orElse(null);

        SavingAccount savingAccount = SavingAccount.builder()
                .customer(theCustomer)
                .accountNumber(AccountUtils.generateAccountNumber())
                .accountName(theCustomer.getFirstName()+ " " + theCustomer.getLastName() + " Saving")
                .openDate(LocalDate.now())
                .accType("SAVING")
                .accountBalance(BigDecimal.ZERO)
                .interestRate(BigDecimal.ZERO)
                .build();

        SavingAccount savedSavingAccount = savingAccountRepository.save(savingAccount);
        return BankResponse.builder()
                .responseCode(AccountUtils.ACCOUNT_CREATION_SUCCESS)
                .responseMessage(AccountUtils.ACCOUNT_CREATION_MESSAGE)
                .savingAccountInfo(SavingAccountInfo.builder()
                        .savingAccountId(savedSavingAccount.getId())
                        .accountNumber(savedSavingAccount.getAccountNumber())
                        .accountName(savedSavingAccount.getAccountName())
                        .openDate(savedSavingAccount.getOpenDate())
                        .accType(savedSavingAccount.getAccType())
                        .accountBalance(savedSavingAccount.getAccountBalance())
                        .interestRate(savedSavingAccount.getInterestRate())
                                .build())
                .build();
    }
}
