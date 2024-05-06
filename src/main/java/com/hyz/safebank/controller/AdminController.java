package com.hyz.safebank.controller;

import com.hyz.safebank.dto.*;
import com.hyz.safebank.entity.InsuranceCompany;
import com.hyz.safebank.service.impl.CustomerService;
import com.hyz.safebank.service.impl.InsuranceCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private InsuranceCompanyService insuranceCompanyService;


    @PostMapping("insurance-company")
    public BankResponse createInsuranceCompany(@RequestBody InsuranceCompanyRequest insuranceCompanyRequest) {
        return insuranceCompanyService.createInsuranceCompany(insuranceCompanyRequest);
    }

    @GetMapping("insurance-company")
    public BankResponse getInsuranceCompany(@RequestBody InsuranceCompanyIdRequest insuranceCompanyIdRequest) {
        return insuranceCompanyService.getInsuranceCompany(insuranceCompanyIdRequest);
    }

    @PutMapping("insurance-company")
    public BankResponse updateInsuranceCompany(@RequestBody InsuranceCompanyUpdateRequest insuranceCompanyUpdateRequest) {
        return insuranceCompanyService.updateInsuranceCompany(insuranceCompanyUpdateRequest);
    }

    @DeleteMapping("insurance-company")
    public BankResponse deleteInsuranceCompany(@RequestBody InsuranceCompanyIdRequest insuranceCompanyIdRequest) {
        return insuranceCompanyService.deleteInsuranceCompany(insuranceCompanyIdRequest);
    }


}
