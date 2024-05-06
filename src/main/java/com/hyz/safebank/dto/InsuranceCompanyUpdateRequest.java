package com.hyz.safebank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InsuranceCompanyUpdateRequest {
    private Long insuranceCompanyId;
    private String companyName;
    private String street;
    private String city;
    private String state;
    private String zipcode;
}
