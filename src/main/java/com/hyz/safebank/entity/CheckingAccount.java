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
@Table(name = "hyz_checking")
@DiscriminatorValue("CHECKING")
public class CheckingAccount extends Account {
    private BigDecimal serviceCharge;
}
