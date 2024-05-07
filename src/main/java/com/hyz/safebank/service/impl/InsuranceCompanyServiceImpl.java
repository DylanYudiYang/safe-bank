package com.hyz.safebank.service.impl;

import com.hyz.safebank.dto.*;
import com.hyz.safebank.entity.InsuranceCompany;
import com.hyz.safebank.repository.InsuranceCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InsuranceCompanyServiceImpl implements InsuranceCompanyService{
    @Autowired
    InsuranceCompanyRepository insuranceCompanyRepository;

    @Override
    public BankResponse createInsuranceCompany(InsuranceCompanyRequest insuranceCompanyRequest) {
        // Check if the insurance company already exists
        if(insuranceCompanyRepository.existsByCompanyName(insuranceCompanyRequest.getCompanyName())){
            return BankResponse.builder()
                    .responseCode("001")
                    .responseMessage("Insurance Company already exists")
                    .build();
        }

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
        return BankResponse.builder()
                .responseCode("002")
                .responseMessage("Insurance Company created successfully")
                .insuranceCompanyInfo(InsuranceCompanyInfo.builder()
                        .InsuranceCompanyId(savedCompany.getId())
                        .companyName(savedCompany.getCompanyName())
                        .street(savedCompany.getStreet())
                        .city(savedCompany.getCity())
                        .state(savedCompany.getState())
                        .zipcode(savedCompany.getZipcode())
                        .build())
                .build();


    }

    @Override
    public BankResponse getInsuranceCompany(InsuranceCompanyIdRequest insuranceCompanyIdRequest) {
        if(!insuranceCompanyRepository.existsById(insuranceCompanyIdRequest.getInsuranceCompanyId())){
            return BankResponse.builder()
                    .responseCode("001")
                    .responseMessage("Insurance Company not found")
                    .build();
        }
        Optional<InsuranceCompany> insuranceCompany = insuranceCompanyRepository.findById(insuranceCompanyIdRequest.getInsuranceCompanyId());
        return BankResponse.builder()
                .responseCode("002")
                .responseMessage("Insurance Company found")
                .insuranceCompanyInfo(InsuranceCompanyInfo.builder()
                        .InsuranceCompanyId(insuranceCompany.get().getId())
                        .companyName(insuranceCompany.get().getCompanyName())
                        .street(insuranceCompany.get().getStreet())
                        .city(insuranceCompany.get().getCity())
                        .state(insuranceCompany.get().getState())
                        .zipcode(insuranceCompany.get().getZipcode())
                        .build())
                .build();
    }

    @Override
    public BankResponse updateInsuranceCompany(InsuranceCompanyUpdateRequest insuranceCompanyUpdateRequest) {
        if(!insuranceCompanyRepository.existsById(insuranceCompanyUpdateRequest.getInsuranceCompanyId())){
            return BankResponse.builder()
                    .responseCode("001")
                    .responseMessage("Insurance Company not found")
                    .build();
        }

        Optional<InsuranceCompany> insuranceCompany = insuranceCompanyRepository.findById(insuranceCompanyUpdateRequest.getInsuranceCompanyId());
        insuranceCompany.get().setCompanyName(insuranceCompanyUpdateRequest.getCompanyName());
        insuranceCompany.get().setStreet(insuranceCompanyUpdateRequest.getStreet());
        insuranceCompany.get().setCity(insuranceCompanyUpdateRequest.getCity());
        insuranceCompany.get().setState(insuranceCompanyUpdateRequest.getState());
        insuranceCompany.get().setZipcode(insuranceCompanyUpdateRequest.getZipcode());

        InsuranceCompany updatedCompany = insuranceCompanyRepository.save(insuranceCompany.get());

        return BankResponse.builder()
                .responseCode("002")
                .responseMessage("Insurance Company updated successfully")
                .insuranceCompanyInfo(InsuranceCompanyInfo.builder()
                        .InsuranceCompanyId(updatedCompany.getId())
                        .companyName(updatedCompany.getCompanyName())
                        .street(updatedCompany.getStreet())
                        .city(updatedCompany.getCity())
                        .state(updatedCompany.getState())
                        .zipcode(updatedCompany.getZipcode())
                        .build())
                .build();
    }

    @Override
    public BankResponse deleteInsuranceCompany(InsuranceCompanyIdRequest insuranceCompanyIdRequest) {
        if(!insuranceCompanyRepository.existsById(insuranceCompanyIdRequest.getInsuranceCompanyId())){
            return BankResponse.builder()
                    .responseCode("001")
                    .responseMessage("Insurance Company not found")
                    .build();
        }

        insuranceCompanyRepository.deleteById(insuranceCompanyIdRequest.getInsuranceCompanyId());

        return BankResponse.builder()
                .responseCode("002")
                .responseMessage("Insurance Company deleted successfully")
                .build();
    }

    @Override
    public List<InsuranceCompanyInfo> getAllInsuranceCompanies() {
        // Get all insurance companies and build a list of InsuranceCompanyInfo objects
        List<InsuranceCompany> insuranceCompanies = insuranceCompanyRepository.findAll();
        return insuranceCompanies.stream()
                .map(this::convertToInfo)
                .collect(Collectors.toList());
    }
    @Override
    public InsuranceCompanyInfo convertToInfo(InsuranceCompany insuranceCompany) {
        return InsuranceCompanyInfo.builder()
                .InsuranceCompanyId(insuranceCompany.getId())
                .companyName(insuranceCompany.getCompanyName())
                .street(insuranceCompany.getStreet())
                .city(insuranceCompany.getCity())
                .state(insuranceCompany.getState())
                .zipcode(insuranceCompany.getZipcode())
                .build();
    }
}
