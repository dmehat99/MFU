package com.example.mfu.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Data;

@Data
@Entity
@Table(name = "customer")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "customer_id")
	private Long customerId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "birth_date")
	private LocalDate birthDate;

	@Column(name = "email")
	private String email;

	@Column(name = "verification_status")
	private String verificationStatus;

//	@OneToMany(mappedBy = "customer")
//	private List<CustomerPortfolio> portfolios;
}
