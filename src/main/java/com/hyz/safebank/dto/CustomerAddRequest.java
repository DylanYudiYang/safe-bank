package com.hyz.safebank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerAddRequest {
    private Long customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String street;
    private String city;
    private String state;
    private String zipcode;

}
