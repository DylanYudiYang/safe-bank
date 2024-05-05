package com.hyz.safebank.service.impl;

import com.hyz.safebank.dto.BankResponse;
import com.hyz.safebank.dto.InsuranceCompanyRequest;
import com.hyz.safebank.entity.InsuranceCompany;

public interface InsuranceCompanyService {
    BankResponse createInsuranceCompany(InsuranceCompanyRequest insuranceCompanyRequest);
}
