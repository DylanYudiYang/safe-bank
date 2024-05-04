package com.hyz.safebank.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
//@Table(name = "hyz_saving")
@DiscriminatorValue("SAVING")
public class SavingAccount extends Account {
    private BigDecimal interestRate;

    // Getters and setters
}
