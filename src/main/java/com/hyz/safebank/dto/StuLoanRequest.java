package com.hyz.safebank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StuLoanRequest {
    private Long customerId;
    private BigDecimal loanAmount;
    private int loanMonths;
    private String studentID;
    private LocalDate expGradDate;
    private String degreeType;
    private String universityName;
}
