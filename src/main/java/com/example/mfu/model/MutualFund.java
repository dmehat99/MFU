package com.example.mfu.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "mf_details")
public class MutualFund {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "schema_id")
	private Long schemaId;

	@Column(name = "schema_name")
	private String schemaName;

	@Column(name = "schema_category")
	private String schemaCategory;

	@Column(name = "description")
	private String description;

	@Column(name = "fund_house")
	private String fundHouse;

	@Column(name = "symbol")
	private String symbol;

	@Column(name = "curr_price")
	private Integer currPrice;

	@Column(name = "delta")
	private Integer delta;
}
