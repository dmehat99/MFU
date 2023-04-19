package com.example.mfu.service;

import com.example.mfu.model.Customer;
import com.example.mfu.model.Portfolio;
import com.example.mfu.model.PurchaseMufRequest;
import com.example.mfu.model.WithdrawMufRequest;
import com.example.mfu.repository.CustomerRepository;
import com.example.mfu.repository.PortfolioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.Optional;
import java.math.BigDecimal;


@Service
public class MutualFundPWService {

    private final CustomerRepository customerRepository;
    private final PortfolioRepository portfolioRepository;
    private final WalletInfo walletService;
    private final MutualFundInfoService mutualFundInfoService;
    private final JavaMailSender javaMailSender;

    public MutualFundPWService(CustomerRepository customerRepository,
                               PortfolioRepository portfolioRepository,
                               WalletInfo walletService,
                               MutualFundInfoService mutualFundInfoService,
                               JavaMailSender javaMailSender) {
        this.customerRepository = customerRepository;
        this.portfolioRepository = portfolioRepository;
        this.walletService = walletService;
        this.mutualFundInfoService = mutualFundInfoService;
        this.javaMailSender = javaMailSender;
    }

    private void sendMail(String subject, String body, String to) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(to);
        mail.setSubject(subject);
        mail.setText(body);
        javaMailSender.send(mail);
    }

    public ResponseEntity<?> purchaseMutualFund(@RequestBody PurchaseMufRequest request) {
        try {
            Long customerId = request.getCustomerId();
            String mfName = request.getMfName();

            Optional<Customer> customerOptional = customerRepository.findById(customerId);
            Customer customer = customerOptional.orElse(null);

            MutualFundInfoService mutualFundInfo = mutualFundInfoService.getMutualFundInfo(mfName);

            if (mutualFundInfo != null) {
                String fundName = mutualFundInfo.getSchemaName();
                Double fundPrice = mutualFundInfo.getCurrPrice();

                int fundUnits = 0;
                double amount = 0.0;

                if (request.getUnits() != 0) {
                    fundUnits = request.getUnits();
                    amount = fundPrice * fundUnits;
                } else if (request.getAmount() != null) {
                    BigDecimal temp = request.getAmount();
                    amount = temp.doubleValue();
                    fundUnits = (int) (amount / fundPrice);
                }

                BigDecimal val = new BigDecimal(Double.toString(amount));

                WalletInfo walletInfo = walletService.getWalletInfo(customerId);

                if (walletInfo != null) {
                    BigDecimal walletBalance = walletInfo.getWalletBalance();
                    int comparison = walletBalance.compareTo(val);

                    if (comparison >= 0) {
                        walletService.withdrawWalletFunds(customerId, val);
                        Portfolio portfolio = portfolioRepository
                                .findByCustomerCustomerIdAndMfName(customerId, mfName);

                        if (portfolio == null) {
                            portfolio = new Portfolio();
                            portfolio.setCustomerId(customerId);
                            portfolio.setMfName(fundName);
                            portfolio.setMfFundHouse(mutualFundInfo.getFundHouse());
                            portfolio.setMfUnitsAvailable(fundUnits);
                            portfolio.setCurrency(mutualFundInfo.getSymbol());
                            portfolio.setTransactionDate(LocalDate.now());
                            portfolioRepository.save(portfolio);
                        } else {
                            portfolio.setMfUnitsAvailable(portfolio.getMfUnitsAvailable() + fundUnits);
                            portfolioRepository.save(portfolio);
                        }
                        if (customer != null) {
                            sendMail("Purchase Confirmation",
                                    "Thank you for your purchase of " + fundUnits + " units of " + fundName,
                                    customer.getEmail());
                        }

                        return ResponseEntity.ok().body("Successfully purchased mutual funds from wallet");
                    } else {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient funds in wallet");
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Wallet not found");
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mutual fund not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    public ResponseEntity<?> withdrawMutualFund(@RequestBody WithdrawMufRequest request) {
        try {
            Long customerId = request.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElse(null);
            String mfName = request.getMfName();
            Integer units = request.getUnits();

            MutualFundInfoService mutualFundInfo = mutualFundInfoService.getMutualFundInfo(mfName);

            if (mutualFundInfo != null) {
                String fundName = mutualFundInfo.getSchemaName();
                Double fundPrice = mutualFundInfo.getCurrPrice();

                Portfolio portfolio = portfolioRepository
                                    .findByCustomerCustomerIdAndMfName(customerId, mfName);

                if (portfolio != null) {
                    if (portfolio.getMfUnitsAvailable() >= units) {
                        double result = fundPrice * units;
                        BigDecimal amount = new BigDecimal(Double.toString(result));

                        walletService.addWalletFunds(customerId, amount);

                        portfolio.setMfUnitsAvailable(portfolio.getMfUnitsAvailable() - units);

                        portfolioRepository.save(portfolio);

                        if (customer != null) {
                            sendMail(
                                    "Withdrawal Confirmation",
                                    "Successfully sold " + units + " units of " + fundName + " for " + amount + ".",
                                    customer.getEmail());
                        }
                        return ResponseEntity.ok().body("Successfully withdrew mutual funds from portfolio");
                    } else {
                        return ResponseEntity.badRequest().body("Not enough units in the portfolio to sell.");
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mutual Fund not found");
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Portfolio not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


}