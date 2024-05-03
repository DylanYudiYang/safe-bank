package com.hyz.safebank.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "hyz_insurance_company")
public class InsuranceCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(nullable = false)
    private String companyName;

    private String street;
    private String city;
    private String state;
    private String zipcode;

    // OneToMany relationship with HomeLoanAccount
    @OneToMany(mappedBy = "insuranceCompany", fetch = FetchType.LAZY)
    private Set<HomeLoanAccount> homeLoanAccounts;

}
