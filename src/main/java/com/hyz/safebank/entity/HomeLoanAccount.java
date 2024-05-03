package com.hyz.safebank.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "hyz_home_loan")
@DiscriminatorValue("HOME")
public class HomeLoanAccount extends LoanAccount{
    private LocalDate houseBuiltDate;
    private String insuranceAccountNumber;
    private double yearlyInsuranceFee;

    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "insurance_company_id", nullable = false)
    private InsuranceCompany insuranceCompany;
}
