package com.example.mfu.controller;

import com.example.mfu.model.Customer;
import com.example.mfu.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	/* GET all customers */
	@GetMapping("/all")
	public List<Customer> getAllCustomers() {
		return customerService.getAllCustomers();
	}

	/* GET a customer */
	@GetMapping("/{id}")
	public Customer getCustomer(@PathVariable Long id) {
		return customerService.getCustomerById(id);
	}

	/* POST a customer */
	@PostMapping("/create")
	public ResponseEntity<String> addCustomer(@RequestBody Customer customer) {
		customerService.addCustomer(customer);
		return ResponseEntity.ok("Customer added successfully!");
	}
}
