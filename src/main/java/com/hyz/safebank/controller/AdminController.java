package com.hyz.safebank.controller;

import com.hyz.safebank.dto.BankResponse;
import com.hyz.safebank.dto.CustomerRequest;
import com.hyz.safebank.dto.InsuranceCompanyRequest;
import com.hyz.safebank.entity.InsuranceCompany;
import com.hyz.safebank.service.impl.CustomerService;
import com.hyz.safebank.service.impl.InsuranceCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private InsuranceCompanyService insuranceCompanyService;

    @PostMapping("insurance-company")
    public BankResponse createInsuranceCompany(@RequestBody InsuranceCompanyRequest insuranceCompanyRequest) {
        return insuranceCompanyService.createInsuranceCompany(insuranceCompanyRequest);
    }
}
