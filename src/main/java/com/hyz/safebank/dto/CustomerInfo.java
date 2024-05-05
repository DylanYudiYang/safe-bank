package com.hyz.safebank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerInfo {
    private Long customerId;
    private String customerName;
//    private BigDecimal accountBalance;
//    private String checkingAccountNumber;
//    private String savingAccountNumber;
//    private String loanAccountNumber;

}
