package com.septp0402.customerservice.repository;

import com.septp0402.customerservice.models.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {
    Optional<CustomerEntity> findByUsername(String username);
    Boolean existsByUsername(String username);

    CustomerEntity findCustomerEntityById(int id);
}
