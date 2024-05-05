package com.hyz.safebank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InsuranceCompanyInfo {
    private Long InsuranceCompanyId;
    private String companyName;
    private String street;
    private String city;
    private String state;
    private String zipcode;
}
