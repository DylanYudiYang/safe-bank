package com.hyz.safebank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceCompanyRequest {
    private String companyName;
    private String street;
    private String city;
    private String state;
    private String zipcode;
}
