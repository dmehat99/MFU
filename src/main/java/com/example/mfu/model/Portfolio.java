package com.example.mfu.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Data;

@Data
@Entity
@Table(name = "customer_portfolio")
public class Portfolio {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "portfolio_id")
	private Long portfolioId;

	@ManyToOne // multiple CustomerPortfolio <-> 1 Customer
	@JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
	private Customer customer;

	@Column(name = "mf_name")
	private String mfName;

	@Column(name = "mf_fund_house")
	private String mfFundHouse;

	@Column(name = "mf_units_available", nullable = false, columnDefinition = "int default 0")
	private Integer mfUnitsAvailable;

	@Column(name = "currency")
	private String currency;

	@Column(name = "transaction_date")
	private LocalDate transactionDate;

	public void setCustomerId(Long customerId) {
		if (customer == null) {
			customer = new Customer();
		}
		customer.setCustomerId(customerId);
	}

	public Long getCustomerId() {
		return customer.getCustomerId();
	}
}
