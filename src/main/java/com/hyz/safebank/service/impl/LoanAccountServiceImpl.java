package com.hyz.safebank.service.impl;

import com.hyz.safebank.dto.AccountRequest;
import com.hyz.safebank.dto.BankResponse;
import com.hyz.safebank.dto.HomeLoanRequest;
import com.hyz.safebank.dto.LoanAccountInfo;
import com.hyz.safebank.entity.HomeLoanAccount;
import com.hyz.safebank.entity.PersonLoanAccount;
import com.hyz.safebank.entity.StuLoanAccount;
import com.hyz.safebank.repository.HomeLoanAccountRepository;
import com.hyz.safebank.repository.PersonLoanAccountRepository;
import com.hyz.safebank.repository.StuLoanAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanAccountServiceImpl implements LoanAccountService{

    @Autowired
    private PersonLoanAccountRepository personLoanAccountRepository;

    @Autowired
    private HomeLoanAccountRepository homeLoanAccountRepository;

    @Autowired
    private StuLoanAccountRepository stuLoanAccountRepository;


    @Override
    public BankResponse getLoanAccount(AccountRequest accountRequest) {
        if (homeLoanAccountRepository.existsByCustomerId(accountRequest.getCustomerId())) {
            HomeLoanAccount homeLoanAccount = homeLoanAccountRepository.findByCustomerId(accountRequest.getCustomerId()).orElse(null);
            return BankResponse.builder()
                    .responseCode("002")
                    .responseMessage("Home Account found")
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
                            .customerId(homeLoanAccount.getCustomer().getId())
                            .houseBuiltDate(homeLoanAccount.getHouseBuiltDate())
                            .insuranceAccountNumber(homeLoanAccount.getInsuranceAccountNumber())
                            .yearlyInsuranceFee(homeLoanAccount.getYearlyInsuranceFee())
                            .insuranceCompanyName(homeLoanAccount.getInsuranceCompany().getCompanyName())
                            .build())
                    .build();
        } else if (personLoanAccountRepository.existsByCustomerId(accountRequest.getCustomerId())) {
            PersonLoanAccount personLoanAccount = personLoanAccountRepository.findByCustomerId(accountRequest.getCustomerId()).orElse(null);
            return BankResponse.builder()
                    .responseCode("002")
                    .responseMessage("Person Account found")
                    .loanAccountInfo(LoanAccountInfo.builder()
                            .accountId(personLoanAccount.getId())
                            .accountNumber(personLoanAccount.getAccountNumber())
                            .accountName(personLoanAccount.getAccountName())
                            .openDate(personLoanAccount.getOpenDate())
                            .accType(personLoanAccount.getAccType())
                            .loanAmount(personLoanAccount.getLoanAmount())
                            .loanRate(personLoanAccount.getLoanRate())
                            .loanMonths(personLoanAccount.getLoanMonths())
                            .loanType(personLoanAccount.getLoanType())
                            .customerId(personLoanAccount.getCustomer().getId())
                            .description(personLoanAccount.getDescription())
                            .build())
                    .build();
        } else if (stuLoanAccountRepository.existsByCustomerId(accountRequest.getCustomerId())) {
            StuLoanAccount stuLoanAccount = stuLoanAccountRepository.findByCustomerId(accountRequest.getCustomerId()).orElse(null);
            return BankResponse.builder()
                    .responseCode("002")
                    .responseMessage("Stu Account found")
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
                            .customerId(stuLoanAccount.getCustomer().getId())
                            .studentID(stuLoanAccount.getStudentID())
                            .expGradDate(stuLoanAccount.getExpGradDate())
                            .degreeType(stuLoanAccount.getDegreeType())
                            .universityName(stuLoanAccount.getUniversity().getUniversityName())
                            .build())
                    .build();
        } else {
            return BankResponse.builder()
                    .responseCode("001")
                    .responseMessage("Account does not exist")
                    .loanAccountInfo(null)
                    .build();
        }
    }
}
