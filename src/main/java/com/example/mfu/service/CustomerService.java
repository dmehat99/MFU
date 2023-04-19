package com.example.mfu.service;

import com.example.mfu.model.Customer;
import com.example.mfu.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
	private final CustomerRepository customerRepository;

	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	/* GET all customers */
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	/* GET a customer */
	public Customer getCustomerById(Long id) {
		Optional<Customer> optionalCustomer = customerRepository.findById(id);
		return optionalCustomer.orElse(null);
	}

	/* POST a customer */
	public void addCustomer(Customer customer) {
		customerRepository.save(customer);
	}
}
