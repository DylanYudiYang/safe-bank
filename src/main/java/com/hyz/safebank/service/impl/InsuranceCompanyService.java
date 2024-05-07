package com.hyz.safebank.service.impl;

import com.hyz.safebank.dto.*;
import com.hyz.safebank.entity.InsuranceCompany;

import java.util.List;

public interface InsuranceCompanyService {
    BankResponse createInsuranceCompany(InsuranceCompanyRequest insuranceCompanyRequest);
    BankResponse getInsuranceCompany(InsuranceCompanyIdRequest insuranceCompanyIdRequest);

    //updateInsuranceCompany
    BankResponse updateInsuranceCompany(InsuranceCompanyUpdateRequest insuranceCompanyUpdateRequest);
    //deleteInsuranceCompany
    BankResponse deleteInsuranceCompany(InsuranceCompanyIdRequest insuranceCompanyIdRequest);

    //get all InsuranceCompanies
    List<InsuranceCompanyInfo> getAllInsuranceCompanies();

    InsuranceCompanyInfo convertToInfo(InsuranceCompany insuranceCompany);

}
