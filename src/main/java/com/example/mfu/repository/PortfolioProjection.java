package com.example.mfu.repository;

import java.time.LocalDate;

/* DO NOT CHANGE THIS FILE !!! */
public interface PortfolioProjection {
	Long getPortfolioId();
	Long getCustomerId();
	String getMfName();
	String getMfFundHouse();
	Integer getMfUnitsAvailable();
	String getCurrency();
	LocalDate getTransactionDate();
}
