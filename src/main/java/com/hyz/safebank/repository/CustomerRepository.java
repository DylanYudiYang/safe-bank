package com.hyz.safebank.repository;

import com.hyz.safebank.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Boolean existsByEmail(String email);

    Customer findByEmail(String email);

    //Optional<Customer> findById(Long id);
}
