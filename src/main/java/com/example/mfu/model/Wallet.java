package com.example.mfu.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Data;

@Data
@Entity
@Table(name = "customer_wallet")
public class Wallet {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "wallet_id")
	private Long walletId;

	@ManyToOne // multiple CustomerPortfolio <-> 1 Customer
	@JoinColumn(name = "customer_id_id", referencedColumnName = "customer_id")
	private Customer customer;

	@Column(name = "wallet_balance", nullable = false, columnDefinition = "double default 0")
	private Double walletBalance;

	@Column(name = "transaction_date")
	private LocalDate transactionDate;
}
