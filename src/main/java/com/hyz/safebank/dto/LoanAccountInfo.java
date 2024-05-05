package com.hyz.safebank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanAccountInfo {
    private Long loanAccountId;
    private String accountNumber;
    private String accountName;
    private LocalDate openDate;
    private String accType;
    private BigDecimal loanAmount;
    private double loanRate;
    private int loanMonths;
    private String loanType;
    //home
    private LocalDate houseBuiltDate;
    private String insuranceAccountNumber;
    private double yearlyInsuranceFee;
    private String insuranceCompanyName;
    //stu
    private String studentID;
    private LocalDate expGradDate;
    private String degreeType;
    private String universityName;
    //person
    private String description;

}
