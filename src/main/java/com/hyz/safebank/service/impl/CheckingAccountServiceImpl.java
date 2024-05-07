package com.hyz.safebank.service.impl;

import com.hyz.safebank.dto.*;
import com.hyz.safebank.entity.CheckingAccount;
import com.hyz.safebank.entity.Customer;
import com.hyz.safebank.repository.CheckingAccountRepository;
import com.hyz.safebank.repository.CustomerRepository;
import com.hyz.safebank.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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
                .serviceCharge(BigDecimal.valueOf(new Random().nextDouble() * 7 + 1).setScale(2, BigDecimal.ROUND_HALF_UP))
                .build();

        CheckingAccount savedCheckingAccount = checkingAccountRepository.save(checkingAccount);

        return BankResponse.builder()
                .responseCode(AccountUtils.ACCOUNT_CREATION_SUCCESS)
                .responseMessage(AccountUtils.ACCOUNT_CREATION_MESSAGE)
                .checkingAccountInfo(CheckingAccountInfo.builder()
                        .accountId(savedCheckingAccount.getId())
                        .accountNumber(savedCheckingAccount.getAccountNumber())
                        .accountName(savedCheckingAccount.getAccountName())
                        .openDate(savedCheckingAccount.getOpenDate())
                        .accType(savedCheckingAccount.getAccType())
                        .accountBalance(savedCheckingAccount.getAccountBalance())
                        .serviceCharge(savedCheckingAccount.getServiceCharge())
                        .customerId(savedCheckingAccount.getCustomer().getId())
                                .build())
                .build();
    }

    public BankResponse getCheckingAccount(AccountRequest accountRequest) {
        if (!checkingAccountRepository.existsByCustomerId(accountRequest.getCustomerId())) {
            return BankResponse.builder()
                    .responseCode("001")
                    .responseMessage("Checking Account not found")
                    .checkingAccountInfo(null)
                    .build();
        }
        Optional<CheckingAccount> checkingAccount = checkingAccountRepository.findByCustomerId(accountRequest.getCustomerId());

        return BankResponse.builder()
                .responseCode("002")
                .responseMessage("Checking Account found")
                .checkingAccountInfo(CheckingAccountInfo.builder()
                        .accountId(checkingAccount.get().getId())
                        .accountNumber(checkingAccount.get().getAccountNumber())
                        .accountName(checkingAccount.get().getAccountName())
                        .openDate(checkingAccount.get().getOpenDate())
                        .accType(checkingAccount.get().getAccType())
                        .accountBalance(checkingAccount.get().getAccountBalance())
                        .serviceCharge(checkingAccount.get().getServiceCharge())
                        .customerId(checkingAccount.get().getCustomer().getId())
                        .build())
                .build();

    }

    @Override
    public BankResponse deposit(DepositWithdrawRequest depositWithdrawRequest) {
        if (!checkingAccountRepository.existsById(depositWithdrawRequest.getAccountId())) {
            return BankResponse.builder()
                    .responseCode("001")
                    .responseMessage("Checking Account not found")
                    .checkingAccountInfo(null)
                    .build();
        }

        if (depositWithdrawRequest.getAmount().compareTo(BigDecimal.ZERO) < 0) {
            return BankResponse.builder()
                    .responseCode("001")
                    .responseMessage("Invalid amount")
                    .checkingAccountInfo(null)
                    .build();
        }
        Optional<CheckingAccount> checkingAccount = checkingAccountRepository.findById(depositWithdrawRequest.getAccountId());
        BigDecimal newBalance = checkingAccount.get().getAccountBalance().add(depositWithdrawRequest.getAmount());
        checkingAccount.get().setAccountBalance(newBalance);
        checkingAccountRepository.save(checkingAccount.get());

        return BankResponse.builder()
                .responseCode("002")
                .responseMessage("Deposit successful")
                .checkingAccountInfo(CheckingAccountInfo.builder()
                        .accountId(checkingAccount.get().getId())
                        .accountNumber(checkingAccount.get().getAccountNumber())
                        .accountName(checkingAccount.get().getAccountName())
                        .openDate(checkingAccount.get().getOpenDate())
                        .accType(checkingAccount.get().getAccType())
                        .accountBalance(checkingAccount.get().getAccountBalance())
                        .serviceCharge(checkingAccount.get().getServiceCharge())
                        .customerId(checkingAccount.get().getCustomer().getId())
                        .build())
                .build();
    }

    @Override
    public BankResponse withdraw(DepositWithdrawRequest depositWithdrawRequest) {
        if (!checkingAccountRepository.existsById(depositWithdrawRequest.getAccountId())) {
            return BankResponse.builder()
                    .responseCode("001")
                    .responseMessage("Checking Account not found")
                    .checkingAccountInfo(null)
                    .build();
        }

        if (depositWithdrawRequest.getAmount().compareTo(BigDecimal.ZERO) < 0) {
            return BankResponse.builder()
                    .responseCode("001")
                    .responseMessage("Invalid amount")
                    .checkingAccountInfo(null)
                    .build();
        }
        Optional<CheckingAccount> checkingAccount = checkingAccountRepository.findById(depositWithdrawRequest.getAccountId());

        if (checkingAccount.get().getAccountBalance().compareTo(depositWithdrawRequest.getAmount()) < 0) {
            return BankResponse.builder()
                    .responseCode("001")
                    .responseMessage("Insufficient funds")
                    .checkingAccountInfo(CheckingAccountInfo.builder()
                            .accountId(checkingAccount.get().getId())
                            .accountNumber(checkingAccount.get().getAccountNumber())
                            .accountName(checkingAccount.get().getAccountName())
                            .openDate(checkingAccount.get().getOpenDate())
                            .accType(checkingAccount.get().getAccType())
                            .accountBalance(checkingAccount.get().getAccountBalance())
                            .serviceCharge(checkingAccount.get().getServiceCharge())
                            .customerId(checkingAccount.get().getCustomer().getId())
                            .build())
                    .build();
        }

        BigDecimal newBalance = checkingAccount.get().getAccountBalance().subtract(depositWithdrawRequest.getAmount());
        checkingAccount.get().setAccountBalance(newBalance);
        checkingAccountRepository.save(checkingAccount.get());

        return BankResponse.builder()
                .responseCode("002")
                .responseMessage("Withdraw successful")
                .checkingAccountInfo(CheckingAccountInfo.builder()
                        .accountId(checkingAccount.get().getId())
                        .accountNumber(checkingAccount.get().getAccountNumber())
                        .accountName(checkingAccount.get().getAccountName())
                        .openDate(checkingAccount.get().getOpenDate())
                        .accType(checkingAccount.get().getAccType())
                        .accountBalance(checkingAccount.get().getAccountBalance())
                        .serviceCharge(checkingAccount.get().getServiceCharge())
                        .customerId(checkingAccount.get().getCustomer().getId())
                        .build())
                .build();
    }

    @Override
    public BankResponse transfer(TransferRequest transferRequest) {
        if (!checkingAccountRepository.existsById(transferRequest.getFromAccountId())|| !checkingAccountRepository.existsById(transferRequest.getToAccountId())) {
            return BankResponse.builder()
                    .responseCode("001")
                    .responseMessage("Checking Account not found")
                    .checkingAccountInfo(null)
                    .build();
        }

        if (transferRequest.getAmount().compareTo(BigDecimal.ZERO) < 0) {
            return BankResponse.builder()
                    .responseCode("001")
                    .responseMessage("Invalid amount")
                    .checkingAccountInfo(null)
                    .build();
        }
        Optional<CheckingAccount> fromCheckingAccount = checkingAccountRepository.findById(transferRequest.getFromAccountId());

        if (fromCheckingAccount.get().getAccountBalance().compareTo(transferRequest.getAmount()) < 0) {
            return BankResponse.builder()
                    .responseCode("003")
                    .responseMessage("Insufficient funds")
                    .checkingAccountInfo(CheckingAccountInfo.builder()
                            .accountId(fromCheckingAccount.get().getId())
                            .accountNumber(fromCheckingAccount.get().getAccountNumber())
                            .accountName(fromCheckingAccount.get().getAccountName())
                            .openDate(fromCheckingAccount.get().getOpenDate())
                            .accType(fromCheckingAccount.get().getAccType())
                            .accountBalance(fromCheckingAccount.get().getAccountBalance())
                            .serviceCharge(fromCheckingAccount.get().getServiceCharge())
                            .customerId(fromCheckingAccount.get().getCustomer().getId())
                            .build())
                    .build();
        }

        Optional<CheckingAccount> toCheckingAccount = checkingAccountRepository.findById(transferRequest.getToAccountId());

        BigDecimal newFromBalance = fromCheckingAccount.get().getAccountBalance().subtract(transferRequest.getAmount());
        fromCheckingAccount.get().setAccountBalance(newFromBalance);
        checkingAccountRepository.save(fromCheckingAccount.get());

        BigDecimal newToBalance = toCheckingAccount.get().getAccountBalance().add(transferRequest.getAmount());
        toCheckingAccount.get().setAccountBalance(newToBalance);
        checkingAccountRepository.save(toCheckingAccount.get());

        return BankResponse.builder()
                .responseCode("002")
                .responseMessage("Transfer successful")
                .checkingAccountInfo(CheckingAccountInfo.builder()
                        .accountId(fromCheckingAccount.get().getId())
                        .accountNumber(fromCheckingAccount.get().getAccountNumber())
                        .accountName(fromCheckingAccount.get().getAccountName())
                        .openDate(fromCheckingAccount.get().getOpenDate())
                        .accType(fromCheckingAccount.get().getAccType())
                        .accountBalance(fromCheckingAccount.get().getAccountBalance())
                        .serviceCharge(fromCheckingAccount.get().getServiceCharge())
                        .customerId(fromCheckingAccount.get().getCustomer().getId())
                        .build())
                .build();
    }

    @Override
    public BankResponse deleteCheckingAccount(EnquiryRequest enquiryRequest) {
        if (!checkingAccountRepository.existsById(enquiryRequest.getAccountId())) {
            return BankResponse.builder()
                    .responseCode("001")
                    .responseMessage("Checking Account not found")
                    .checkingAccountInfo(null)
                    .build();
        }
        if (!checkingAccountRepository.existsById(enquiryRequest.getAccountId())) {
            return BankResponse.builder()
                    .responseCode("001")
                    .responseMessage("Checking Account not found")
                    .checkingAccountInfo(null)
                    .build();
        }

        //check if account has balance
        Optional<CheckingAccount> checkingAccount = checkingAccountRepository.findById(enquiryRequest.getAccountId());
        if (checkingAccount.get().getAccountBalance().compareTo(BigDecimal.ZERO) > 0) {
            return BankResponse.builder()
                    .responseCode("001")
                    .responseMessage("Account has balance. Withdraw balance before deleting account")
                    .build();
        }

        checkingAccountRepository.deleteById(enquiryRequest.getAccountId());

        return BankResponse.builder()
                .responseCode("002")
                .responseMessage("Checking Account deleted")
                .checkingAccountInfo(null)
                .build();
    }

    @Override
    public List<CheckingAccountInfo> getAllCheckingAccounts() {
        List<CheckingAccount> checkingAccounts = checkingAccountRepository.findAll();
        return checkingAccounts.stream()
                .map(this::convertToInfo)
                .toList();
    }

    @Override
    public CheckingAccountInfo convertToInfo(CheckingAccount checkingAccount) {
        return CheckingAccountInfo.builder()
                .accountId(checkingAccount.getId())
                .accountNumber(checkingAccount.getAccountNumber())
                .accountName(checkingAccount.getAccountName())
                .openDate(checkingAccount.getOpenDate())
                .accType(checkingAccount.getAccType())
                .accountBalance(checkingAccount.getAccountBalance())
                .serviceCharge(checkingAccount.getServiceCharge())
                .customerId(checkingAccount.getCustomer().getId())
                .build();
    }


}
