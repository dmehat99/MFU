package com.example.mfu.repository;

import com.example.mfu.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * DAO Customer
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	// Find customer by ID
	Optional<Customer> findCustomerByCustomerId(Long customerId);
}

