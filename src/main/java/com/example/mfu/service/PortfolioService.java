package com.example.mfu.service;

import com.example.mfu.model.Portfolio;
import com.example.mfu.repository.PortfolioProjection;
import com.example.mfu.repository.PortfolioRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class PortfolioService {
	private final PortfolioRepository portfolioRepository;

	public PortfolioService(PortfolioRepository portfolioRepository) {
		this.portfolioRepository = portfolioRepository;
	}

	/* GET a portfolio */
	public Portfolio getPortfolioById(@PathVariable Long portfolioId) {
		Optional<Portfolio> optionalPortfolio = portfolioRepository.findById(portfolioId);
		return optionalPortfolio.orElse(null);
	}

	/* GET all portfolios of a customer */
	public List<PortfolioProjection> getPortfolioByCustomerId(@PathVariable Long customerId) {
		return portfolioRepository.findByCustomerId(customerId);
	}
}
/**
 * 		// Set the customer ID on the portfolio
 * 		portfolio.setCustomerId(customerId);
 *
 * 		// Create a new instance of RestTemplate
 * 		RestTemplate restTemplate = new RestTemplate();
 *
 * 		// Set the endpoint URL for the external portfolio management system
 * 		String url = "http://localhost:8080/portfolios/customers/" + customerId;
 *
 * 		// Create a new instance of HttpHeaders and set the content type to JSON
 * 		HttpHeaders headers = new HttpHeaders();
 * 		headers.setContentType(MediaType.APPLICATION_JSON);
 *
 * 		// Create a new HttpEntity with the portfolio and headers
 * 		HttpEntity<Portfolio> request = new HttpEntity<>(portfolio, headers);
 *
 * 		// Make an HTTP POST request to the external system with the portfolio
 * 		restTemplate.postForObject(url, request, Portfolio.class);
 */
