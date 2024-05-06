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
public class CheckingAccountInfo {
    private Long accountId;
    private String accountNumber;
    private String accountName;
    private LocalDate openDate;
    private String accType;
    private BigDecimal accountBalance;
    private BigDecimal serviceCharge;
}
