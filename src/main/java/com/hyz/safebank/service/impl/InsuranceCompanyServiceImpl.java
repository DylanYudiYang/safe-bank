package com.hyz.safebank.service.impl;

import com.hyz.safebank.dto.InsuranceCompanyRequest;
import com.hyz.safebank.entity.InsuranceCompany;
import com.hyz.safebank.repository.InsuranceCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class InsuranceCompanyServiceImpl implements InsuranceCompanyService{
    @Autowired
    InsuranceCompanyRepository insuranceCompanyRepository;

    @Override
    public InsuranceCompany createInsuranceCompany(InsuranceCompanyRequest insuranceCompanyRequest) {

        // Build and save the new insurance company
        InsuranceCompany newCompany = InsuranceCompany.builder()
                .companyName(insuranceCompanyRequest.getCompanyName())
                .street(insuranceCompanyRequest.getStreet())
                .city(insuranceCompanyRequest.getCity())
                .state(insuranceCompanyRequest.getState())
                .zipcode(insuranceCompanyRequest.getZipcode())
                .homeLoanAccounts(new HashSet<>())  // Initialize an empty set for related entities
                .build();

        InsuranceCompany savedCompany = insuranceCompanyRepository.save(newCompany);
        return  savedCompany;


    }
}
