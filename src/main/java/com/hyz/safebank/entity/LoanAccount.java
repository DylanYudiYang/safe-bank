package com.hyz.safebank.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "hyz_loan")
@DiscriminatorValue("LOAN")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "loan_type", discriminatorType = DiscriminatorType.STRING)
public class LoanAccount extends Account {
    private BigDecimal loanAmount;
    private double loanRate;
    private int loanMonths;

    @Column(name = "loan_type", insertable = false, updatable = false)
    private String loanType;

}
