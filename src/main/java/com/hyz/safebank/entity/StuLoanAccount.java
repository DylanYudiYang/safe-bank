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
@Table(name = "hyz_stu_loan")
@DiscriminatorValue("STU")
public class StuLoanAccount extends LoanAccount{
    private String studentID;
    private LocalDate expGradDate;
    private String degreeType;

    @ManyToOne
    @JoinColumn(name = "university_id", nullable = false)
    private University university;

}
