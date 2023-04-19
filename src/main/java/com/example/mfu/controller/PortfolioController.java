package com.example.mfu.controller;

import com.example.mfu.model.Portfolio;
import com.example.mfu.repository.PortfolioProjection;
import com.example.mfu.service.PortfolioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/portfolios")
public class PortfolioController {
	private final PortfolioService portfolioService;

	public PortfolioController(PortfolioService portfolioService) {
		this.portfolioService = portfolioService;
	}

	/* GET a portfolio */
	@GetMapping("/{portfolioId}")
	public Portfolio getPortfolioById(@PathVariable Long portfolioId) {
		return portfolioService.getPortfolioById(portfolioId);
	}

	/* GET all portfolios of a customer */
	@ResponseBody
	@GetMapping("/customers/{customerId}")
	public List<PortfolioProjection> getPortfolioByCustomerId(@PathVariable Long customerId) {
		return portfolioService.getPortfolioByCustomerId(customerId);
	}
}
