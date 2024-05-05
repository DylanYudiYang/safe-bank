package com.hyz.safebank.service.impl;

import com.hyz.safebank.dto.AccountRequest;
import com.hyz.safebank.dto.BankResponse;
import com.hyz.safebank.dto.CheckingAccountInfo;
import com.hyz.safebank.entity.CheckingAccount;
import com.hyz.safebank.entity.Customer;
import com.hyz.safebank.repository.CheckingAccountRepository;
import com.hyz.safebank.repository.CustomerRepository;
import com.hyz.safebank.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class CheckingAccountServiceImpl implements CheckingAccountService {
    @Autowired
    private CheckingAccountRepository checkingAccountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public BankResponse createCheckingAccount(AccountRequest accountRequest) {
        Long customerId = accountRequest.getCustomerId();
        if (checkingAccountRepository.existsByCustomerId(customerId)) {
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_EXISTS_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_EXISTS_MESSAGE)
                    .customerInfo(null)
                    .build();
        }

        Customer theCustomer = customerRepository.findById(customerId).orElse(null);
        CheckingAccount checkingAccount = CheckingAccount.builder()
                .customer(theCustomer)
                .accountNumber(AccountUtils.generateAccountNumber())
                .accountName(theCustomer.getFirstName()+ " " + theCustomer.getLastName() + " Checking")
                .openDate(LocalDate.now())
                .accType("CHECKING")
                .accountBalance(BigDecimal.ZERO)
                .serviceCharge(BigDecimal.ZERO)
                .build();

        CheckingAccount savedCheckingAccount = checkingAccountRepository.save(checkingAccount);

        return BankResponse.builder()
                .responseCode(AccountUtils.ACCOUNT_CREATION_SUCCESS)
                .responseMessage(AccountUtils.ACCOUNT_CREATION_MESSAGE)
                .checkingAccountInfo(CheckingAccountInfo.builder()
                        .checkingAccountId(savedCheckingAccount.getId())
                        .accountNumber(savedCheckingAccount.getAccountNumber())
                        .accountName(savedCheckingAccount.getAccountName())
                        .openDate(savedCheckingAccount.getOpenDate())
                        .accType(savedCheckingAccount.getAccType())
                        .accountBalance(savedCheckingAccount.getAccountBalance())
                        .serviceCharge(savedCheckingAccount.getServiceCharge())
                                .build())
                .build();
    }

    public BigDecimal getCheckingAccountBalance(Long customerId) {
        Optional<CheckingAccount> account = checkingAccountRepository.findByCustomerId(customerId);
        return account.map(CheckingAccount::getAccountBalance).orElse(BigDecimal.ZERO);
    }
}
