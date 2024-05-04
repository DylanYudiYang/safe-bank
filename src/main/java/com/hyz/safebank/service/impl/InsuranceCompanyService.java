package com.hyz.safebank.service.impl;

import com.hyz.safebank.dto.InsuranceCompanyRequest;
import com.hyz.safebank.entity.InsuranceCompany;

public interface InsuranceCompanyService {
    InsuranceCompany createInsuranceCompany(InsuranceCompanyRequest insuranceCompanyRequest);
}
