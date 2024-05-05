package com.hyz.safebank.service.impl;

import com.hyz.safebank.dto.*;
import com.hyz.safebank.entity.Customer;
import com.hyz.safebank.entity.SavingAccount;
import com.hyz.safebank.repository.SavingAccountRepository;
import com.hyz.safebank.repository.CustomerRepository;
import com.hyz.safebank.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

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

    @Override
    public BankResponse getSavingAccount(AccountRequest accountRequest) {
        Long customerId = accountRequest.getCustomerId();
        if (!savingAccountRepository.existsByCustomerId(customerId)) {
            return BankResponse.builder()
                    .responseCode("001")
                    .responseMessage("Account does not exist")
                    .savingAccountInfo(null)
                    .build();
        }

        Optional<SavingAccount> savingAccount = savingAccountRepository.findByCustomerId(customerId);
        return BankResponse.builder()
                .responseCode("002")
                .responseMessage("Account found")
                .savingAccountInfo(SavingAccountInfo.builder()
                        .savingAccountId(savingAccount.get().getId())
                        .accountNumber(savingAccount.get().getAccountNumber())
                        .accountName(savingAccount.get().getAccountName())
                        .openDate(savingAccount.get().getOpenDate())
                        .accType(savingAccount.get().getAccType())
                        .accountBalance(savingAccount.get().getAccountBalance())
                        .interestRate(savingAccount.get().getInterestRate())
                        .build())
                .build();
    }

    @Override
    public BankResponse deposit(DepositWithdrawRequest depositWithdrawRequest) {
        if (!savingAccountRepository.existsById(depositWithdrawRequest.getAccountId())) {
            return BankResponse.builder()
                    .responseCode("001")
                    .responseMessage("Account does not exist")
                    .savingAccountInfo(null)
                    .build();
        }

        Optional<SavingAccount> savingAccount = savingAccountRepository.findById(depositWithdrawRequest.getAccountId());
        SavingAccount theSavingAccount = savingAccount.get();
        BigDecimal currentBalance = theSavingAccount.getAccountBalance();
        BigDecimal depositAmount = depositWithdrawRequest.getAmount();
        BigDecimal newBalance = currentBalance.add(depositAmount);
        theSavingAccount.setAccountBalance(newBalance);
        savingAccountRepository.save(theSavingAccount);

        return BankResponse.builder()
                .responseCode("002")
                .responseMessage("Deposit successful")
                .savingAccountInfo(SavingAccountInfo.builder()
                        .savingAccountId(theSavingAccount.getId())
                        .accountNumber(theSavingAccount.getAccountNumber())
                        .accountName(theSavingAccount.getAccountName())
                        .openDate(theSavingAccount.getOpenDate())
                        .accType(theSavingAccount.getAccType())
                        .accountBalance(theSavingAccount.getAccountBalance())
                        .interestRate(theSavingAccount.getInterestRate())
                        .build())
                .build();

    }

    @Override
    public BankResponse withdraw(DepositWithdrawRequest depositWithdrawRequest) {
        if (!savingAccountRepository.existsById(depositWithdrawRequest.getAccountId())) {
            return BankResponse.builder()
                    .responseCode("001")
                    .responseMessage("Account does not exist")
                    .savingAccountInfo(null)
                    .build();
        }

        Optional<SavingAccount> savingAccount = savingAccountRepository.findById(depositWithdrawRequest.getAccountId());
        SavingAccount theSavingAccount = savingAccount.get();
        BigDecimal currentBalance = theSavingAccount.getAccountBalance();
        BigDecimal withdrawAmount = depositWithdrawRequest.getAmount();
        BigDecimal newBalance = currentBalance.subtract(withdrawAmount);
        theSavingAccount.setAccountBalance(newBalance);
        savingAccountRepository.save(theSavingAccount);

        return BankResponse.builder()
                .responseCode("002")
                .responseMessage("Withdraw successful")
                .savingAccountInfo(SavingAccountInfo.builder()
                        .savingAccountId(theSavingAccount.getId())
                        .accountNumber(theSavingAccount.getAccountNumber())
                        .accountName(theSavingAccount.getAccountName())
                        .openDate(theSavingAccount.getOpenDate())
                        .accType(theSavingAccount.getAccType())
                        .accountBalance(theSavingAccount.getAccountBalance())
                        .interestRate(theSavingAccount.getInterestRate())
                        .build())
                .build();
    }

    @Override
    public BankResponse deleteSavingAccount(EnquiryRequest enquiryRequest) {
        if (!savingAccountRepository.existsById(enquiryRequest.getAccountId())) {
            return BankResponse.builder()
                    .responseCode("001")
                    .responseMessage("Account does not exist")
                    .savingAccountInfo(null)
                    .build();
        }

        savingAccountRepository.deleteById(enquiryRequest.getAccountId());
        return BankResponse.builder()
                .responseCode("002")
                .responseMessage("Account deleted")
                .savingAccountInfo(null)
                .build();
    }


}
