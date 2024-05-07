package com.hyz.safebank.service.impl;

import com.hyz.safebank.dto.*;
import com.hyz.safebank.entity.*;
import com.hyz.safebank.repository.*;

import com.hyz.safebank.utils.CustomerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CheckingAccountRepository checkingAccountRepository;

    @Autowired
    HomeLoanAccountRepository homeLoanAccountRepository;

    @Autowired
    SavingAccountRepository savingAccountRepository;

    @Autowired
    InsuranceCompanyRepository insuranceCompanyRepository;


    @Autowired
    CheckingAccountService checkingAccountService;

    @Autowired
    EmailService emailService;
    @Override
    public BankResponse createCustomer(CustomerRequest customerRequest) {

        if (customerRepository.existsByEmail(customerRequest.getEmail())) {
            return BankResponse.builder()
                    .responseCode(CustomerUtils.CUSTOMER_EXISTS_CODE)
                    .responseMessage(CustomerUtils.CUSTOMER_EXISTS_MESSAGE)
                    .customerInfo(null)
                    .build();
        }


        //create a new customer
        Customer newCustomer = Customer.builder()
                .firstName("")
                .lastName("")
                .email(customerRequest.getEmail())
                .password(customerRequest.getPassword())
                .street("")
                .city("")
                .state("")
                .zipcode("")
                .accounts(new HashSet<>())
                .status("ACTIVE")
                .build();

        Customer savedCustomer = customerRepository.save(newCustomer);




        //Send email
        EmailDetails emailDetails = EmailDetails.builder()
                .recipient(savedCustomer.getEmail())
                .subject("Safe Bank Account Creation")
                .messageBody("You Account Has Been Successfully Created!")
                .build();
        emailService.sendEmailAlert(emailDetails);


        return BankResponse.builder()
                .responseCode(CustomerUtils.CUSTOMER_CREATION_SUCCESS)
                .responseMessage(CustomerUtils.CUSTOMER_CREATION_MESSAGE)
                .customerInfo(CustomerInfo.builder()
                        .customerId(savedCustomer.getId())
                        .firstName(savedCustomer.getFirstName())
                        .lastName(savedCustomer.getLastName())
                        .email(savedCustomer.getEmail())
                        .street(savedCustomer.getStreet())
                        .city(savedCustomer.getCity())
                        .state(savedCustomer.getState())
                        .zipcode(savedCustomer.getZipcode())
                        .build())
                .build();
    }

    @Override
    public BankResponse getCustomer(CustomerIdRequest customerIdRequest) {
        if (!customerRepository.existsById(customerIdRequest.getCustomerId())) {
            return BankResponse.builder()
                    .responseCode("001")
                    .responseMessage("Customer not found")
                    .customerInfo(null)
                    .build();
        }

        Customer customer = customerRepository.findById(customerIdRequest.getCustomerId()).get();

        return BankResponse.builder()
                .responseCode("002")
                .responseMessage("Customer found")
                .customerInfo(CustomerInfo.builder()
                        .customerId(customer.getId())
                        .firstName(customer.getFirstName())
                        .lastName(customer.getLastName())
                        .email(customer.getEmail())
                        .street(customer.getStreet())
                        .city(customer.getCity())
                        .state(customer.getState())
                        .zipcode(customer.getZipcode())
                        .build())
                .build();
    }

    @Override
    public BankResponse loginCustomer(CustomerRequest customerRequest) {
        if (!customerRepository.existsByEmail(customerRequest.getEmail())) {
            return BankResponse.builder()
                    .responseCode("001")
                    .responseMessage("Customer not found")
                    .customerInfo(null)
                    .build();
        }

        Customer customer = customerRepository.findByEmail(customerRequest.getEmail());

        if (!customer.getPassword().equals(customerRequest.getPassword())) {
            return BankResponse.builder()
                    .responseCode("001")
                    .responseMessage("Invalid Password")
                    .customerInfo(null)
                    .build();
        }

        return BankResponse.builder()
                .responseCode("002")
                .responseMessage("Customer logged in successfully")
                .customerInfo(CustomerInfo.builder()
                        .customerId(customer.getId())
                        .firstName(customer.getFirstName())
                        .lastName(customer.getLastName())
                        .email(customer.getEmail())
                        .street(customer.getStreet())
                        .city(customer.getCity())
                        .state(customer.getState())
                        .zipcode(customer.getZipcode())
                        .build())
                .build();
    }


    @Override
    public BankResponse updateCustomer(CustomerUpdateRequest customerUpdateRequest) {
        if(customerUpdateRequest.getCustomerId() == null){
            return BankResponse.builder()
                    .responseCode("001")
                    .responseMessage("update Customer Id is required")
                    .customerInfo(null)
                    .build();
        }
        if (!customerRepository.existsById(customerUpdateRequest.getCustomerId())) {
            return BankResponse.builder()
                    .responseCode("001")
                    .responseMessage("Customer not found")
                    .customerInfo(null)
                    .build();
        }

        Customer customer = customerRepository.findById(customerUpdateRequest.getCustomerId()).get();

        customer.setFirstName(customerUpdateRequest.getFirstName());
        customer.setLastName(customerUpdateRequest.getLastName());
        customer.setEmail(customerUpdateRequest.getEmail());
        customer.setStreet(customerUpdateRequest.getStreet());
        customer.setCity(customerUpdateRequest.getCity());
        customer.setState(customerUpdateRequest.getState());
        customer.setZipcode(customerUpdateRequest.getZipcode());

        Customer updatedCustomer = customerRepository.save(customer);

        return BankResponse.builder()
                .responseCode("002")
                .responseMessage("Customer updated successfully")
                .customerInfo(CustomerInfo.builder()
                        .customerId(updatedCustomer.getId())
                        .firstName(updatedCustomer.getFirstName())
                        .lastName(updatedCustomer.getLastName())
                        .email(updatedCustomer.getEmail())
                        .street(updatedCustomer.getStreet())
                        .city(updatedCustomer.getCity())
                        .state(updatedCustomer.getState())
                        .zipcode(updatedCustomer.getZipcode())
                        .build())
                .build();
    }

    @Override
    public BankResponse deleteCustomer(CustomerIdRequest customerIdRequest) {
        if (!customerRepository.existsById(customerIdRequest.getCustomerId())) {
            return BankResponse.builder()
                    .responseCode("001")
                    .responseMessage("Customer not found")
                    .customerInfo(null)
                    .build();
        }


        //delete from repository
        customerRepository.deleteById(customerIdRequest.getCustomerId());

        return BankResponse.builder()
                .responseCode("002")
                .responseMessage("Customer deleted successfully")
                .customerInfo(null)
                .build();

    }

    @Override
    public List<CustomerInfo> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(this::convertToInfo).toList();
    }

    @Override
    public CustomerInfo convertToInfo(Customer customer) {
        return CustomerInfo.builder()
                .customerId(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .street(customer.getStreet())
                .city(customer.getCity())
                .state(customer.getState())
                .zipcode(customer.getZipcode())
                .build();
    }

    @Override
    public BankResponse addCustomer(CustomerAddRequest customerAddRequest) {
        if (customerRepository.existsByEmail(customerAddRequest.getEmail())) {
            return BankResponse.builder()
                    .responseCode(CustomerUtils.CUSTOMER_EXISTS_CODE)
                    .responseMessage(CustomerUtils.CUSTOMER_EXISTS_MESSAGE)
                    .customerInfo(null)
                    .build();
        }

        //create a new customer
        Customer newCustomer = Customer.builder()
                .firstName(customerAddRequest.getFirstName())
                .lastName(customerAddRequest.getLastName())
                .email(customerAddRequest.getEmail())
                .password(customerAddRequest.getPassword())
                .street(customerAddRequest.getStreet())
                .city(customerAddRequest.getCity())
                .state(customerAddRequest.getState())
                .zipcode(customerAddRequest.getZipcode())
                .accounts(new HashSet<>())
                .status("ACTIVE")
                .build();

        Customer savedCustomer = customerRepository.save(newCustomer);

        return BankResponse.builder()
                .responseCode(CustomerUtils.CUSTOMER_CREATION_SUCCESS)
                .responseMessage(CustomerUtils.CUSTOMER_CREATION_MESSAGE)
                .customerInfo(CustomerInfo.builder()
                        .customerId(savedCustomer.getId())
                        .firstName(savedCustomer.getFirstName())
                        .lastName(savedCustomer.getLastName())
                        .email(savedCustomer.getEmail())
                        .street(savedCustomer.getStreet())
                        .city(savedCustomer.getCity())
                        .state(savedCustomer.getState())
                        .zipcode(savedCustomer.getZipcode())
                        .build())
                .build();
    }

}
