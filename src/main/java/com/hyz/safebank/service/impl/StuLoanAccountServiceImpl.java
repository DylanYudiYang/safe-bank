package com.hyz.safebank.service.impl;

import com.hyz.safebank.dto.*;
import com.hyz.safebank.entity.Customer;
import com.hyz.safebank.entity.StuLoanAccount;
import com.hyz.safebank.entity.University;
import com.hyz.safebank.repository.CustomerRepository;
import com.hyz.safebank.repository.StuLoanAccountRepository;
import com.hyz.safebank.repository.UniversityRepository;
import com.hyz.safebank.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;

@Service
public class StuLoanAccountServiceImpl implements StuLoanAccountService{

    @Autowired
    private StuLoanAccountRepository stuLoanAccountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UniversityRepository universityRepository;
    @Override
    public BankResponse createStuLoanAccount(StuLoanRequest stuLoanRequest) {
        Long customerId = stuLoanRequest.getCustomerId();
        if (stuLoanAccountRepository.existsByCustomerId(customerId)) {
            return BankResponse.builder()
                    .responseCode("001")
                    .responseMessage("Account exists")
                    .customerInfo(null)
                    .build();
        }

        Customer theCustomer = customerRepository.findById(customerId).orElse(null);

        University university = universityRepository.findByUniversityName(stuLoanRequest.getUniversityName()).orElse(null);

        if (university == null) {
            University newUniversity = University.builder()
                    .universityName(stuLoanRequest.getUniversityName())
                    .universityAbbreviation("FYU")
                    .stuLoanAccounts(new HashSet<>())  // Initialize an empty set for related entities
                    .build();

            university = universityRepository.save(newUniversity);
        }

        StuLoanAccount stuLoanAccount = StuLoanAccount.builder()
                .customer(theCustomer)
                .accountNumber(AccountUtils.generateAccountNumber())
                .accountName(theCustomer.getFirstName()+ " " + theCustomer.getLastName() + " Stu Loan")
                .openDate(LocalDate.now())
                .accType("LOAN")
                .loanAmount(stuLoanRequest.getLoanAmount())
                .loanRate(0)
                .loanMonths(stuLoanRequest.getLoanMonths())
                .loanType("STU")
                .studentID(stuLoanRequest.getStudentID())
                .expGradDate(stuLoanRequest.getExpGradDate())
                .degreeType(stuLoanRequest.getDegreeType())
                .university(university)
                .build();

        StuLoanAccount savedStuLoanAccount = stuLoanAccountRepository.save(stuLoanAccount);

        return BankResponse.builder()
                .responseCode(AccountUtils.ACCOUNT_CREATION_SUCCESS)
                .responseMessage(AccountUtils.ACCOUNT_CREATION_MESSAGE)
                .loanAccountInfo(LoanAccountInfo.builder()
                        .accountId(savedStuLoanAccount.getId())
                        .accountNumber(savedStuLoanAccount.getAccountNumber())
                        .accountName(savedStuLoanAccount.getAccountName())
                        .openDate(savedStuLoanAccount.getOpenDate())
                        .accType(savedStuLoanAccount.getAccType())
                        .loanAmount(savedStuLoanAccount.getLoanAmount())
                        .loanRate(savedStuLoanAccount.getLoanRate())
                        .loanMonths(savedStuLoanAccount.getLoanMonths())
                        .loanType(savedStuLoanAccount.getLoanType())
                        .studentID(savedStuLoanAccount.getStudentID())
                        .expGradDate(savedStuLoanAccount.getExpGradDate())
                        .degreeType(savedStuLoanAccount.getDegreeType())
                        .universityName(savedStuLoanAccount.getUniversity().getUniversityName())
                        .build())
                .build();
    }

    @Override
    public BankResponse getStuLoanAccount(AccountRequest accountRequest) {
        if (!stuLoanAccountRepository.existsByCustomerId(accountRequest.getCustomerId())) {
            return BankResponse.builder()
                    .responseCode("001")
                    .responseMessage("Account does not exist")
                    .loanAccountInfo(null)
                    .build();
        }

        StuLoanAccount stuLoanAccount = stuLoanAccountRepository.findByCustomerId(accountRequest.getCustomerId()).orElse(null);

        return BankResponse.builder()
                .responseCode("000")
                .responseMessage("Account found")
                .loanAccountInfo(LoanAccountInfo.builder()
                        .accountId(stuLoanAccount.getId())
                        .accountNumber(stuLoanAccount.getAccountNumber())
                        .accountName(stuLoanAccount.getAccountName())
                        .openDate(stuLoanAccount.getOpenDate())
                        .accType(stuLoanAccount.getAccType())
                        .loanAmount(stuLoanAccount.getLoanAmount())
                        .loanRate(stuLoanAccount.getLoanRate())
                        .loanMonths(stuLoanAccount.getLoanMonths())
                        .loanType(stuLoanAccount.getLoanType())
                        .studentID(stuLoanAccount.getStudentID())
                        .expGradDate(stuLoanAccount.getExpGradDate())
                        .degreeType(stuLoanAccount.getDegreeType())
                        .universityName(stuLoanAccount.getUniversity().getUniversityName())
                        .build())
                .build();
    }

    @Override
    public BankResponse payStuLoan(LoanPaymentRequest loanPaymentRequest) {
        if (!stuLoanAccountRepository.existsById(loanPaymentRequest.getAccountId())) {
            return BankResponse.builder()
                    .responseCode("001")
                    .responseMessage("Account does not exist")
                    .loanAccountInfo(null)
                    .build();
        }

        Optional<StuLoanAccount> stuLoanAccount = stuLoanAccountRepository.findById(loanPaymentRequest.getAccountId());
        if (loanPaymentRequest.getPaymentAmount().compareTo(BigDecimal.ZERO) <= 0 || loanPaymentRequest.getPaymentAmount().compareTo(stuLoanAccount.get().getLoanAmount()) > 0) {
            return BankResponse.builder()
                    .responseCode("001")
                    .responseMessage("Invalid payment amount")
                    .loanAccountInfo(null)
                    .build();

        }

        stuLoanAccount.get().setLoanAmount(stuLoanAccount.get().getLoanAmount().subtract(loanPaymentRequest.getPaymentAmount()));
        stuLoanAccountRepository.save(stuLoanAccount.get());

        return BankResponse.builder()
                .responseCode("002")
                .responseMessage("Payment successful")
                .loanAccountInfo(LoanAccountInfo.builder()
                        .accountId(stuLoanAccount.get().getId())
                        .accountNumber(stuLoanAccount.get().getAccountNumber())
                        .accountName(stuLoanAccount.get().getAccountName())
                        .openDate(stuLoanAccount.get().getOpenDate())
                        .accType(stuLoanAccount.get().getAccType())
                        .loanAmount(stuLoanAccount.get().getLoanAmount())
                        .loanRate(stuLoanAccount.get().getLoanRate())
                        .loanMonths(stuLoanAccount.get().getLoanMonths())
                        .loanType(stuLoanAccount.get().getLoanType())
                        .studentID(stuLoanAccount.get().getStudentID())
                        .expGradDate(stuLoanAccount.get().getExpGradDate())
                        .degreeType(stuLoanAccount.get().getDegreeType())
                        .universityName(stuLoanAccount.get().getUniversity().getUniversityName())
                        .build())
                .build();
    }


    @Override
    public BankResponse deleteStuLoanAccount(EnquiryRequest enquiryRequest) {
        if (!stuLoanAccountRepository.existsById(enquiryRequest.getAccountId())) {
            return BankResponse.builder()
                    .responseCode("001")
                    .responseMessage("Account does not exist")
                    .loanAccountInfo(null)
                    .build();
        }

        stuLoanAccountRepository.deleteById(enquiryRequest.getAccountId());

        return BankResponse.builder()
                .responseCode("002")
                .responseMessage("Account deleted")
                .loanAccountInfo(null)
                .build();
    }
}
