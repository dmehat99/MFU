package com.example.mfu.service;

import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class WalletInfo {
    private BigDecimal walletBalance;

    public WalletInfo getWalletInfo(Long customerId) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        String url = "/wallet/" + customerId;
        WalletInfo walletInfo = restTemplate.getForObject(url, WalletInfo.class);
        if (walletInfo != null) {
            this.walletBalance = walletInfo.getWalletBalance();
            return walletInfo;
        } else {
            return null;
        }
    }

    public BigDecimal getWalletBalance() {
        return walletBalance;
    }

    public ResponseEntity<String> addWalletFunds(Long customerId, BigDecimal amount) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        String url = "/transact";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> transaction = new HashMap<>();
        transaction.put("typeId", 1);
        transaction.put("description", "Deposit");
        transaction.put("amount", amount);
        transaction.put("customerId", customerId);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(transaction, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
        return response;
    }

    public ResponseEntity<String> withdrawWalletFunds(Long customerId, BigDecimal amount) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        String url = "/transact";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> transaction = new HashMap<>();
        transaction.put("typeId", 2);
        transaction.put("description", "Withdrawal");
        transaction.put("amount", amount);
        transaction.put("customerId", customerId);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(transaction, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
        return response;
    }
}
