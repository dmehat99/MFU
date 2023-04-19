package com.example.mfu.repository;

import com.example.mfu.model.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
	// Get the portfolios of the current customer
	@Query("SELECT p FROM Portfolio p WHERE p.customer.customerId = :customerId")
	List<PortfolioProjection> findByCustomerId(@Param("customerId") Long customerId);

	// Find customer portfolio by mutual fund ID and customer ID
	Portfolio findByCustomerCustomerIdAndMfName(Long customerId, String mfName);

	// Save customer portfolio
//	Portfolio save(Portfolio portfolio);
}
