package com.hyz.safebank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankResponse {

    private String responseCode;
    private String responseMessage;
    private CustomerInfo customerInfo;
    private CheckingAccountInfo checkingAccountInfo;
    private SavingAccountInfo savingAccountInfo;
    private StuLoanAccountInfo stuLoanAccountInfo;
    private InsuranceCompanyInfo insuranceCompanyInfo;

}
