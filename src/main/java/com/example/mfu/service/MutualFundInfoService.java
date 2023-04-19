package com.example.mfu.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;


@Service
public class MutualFundInfoService {
    private String schemaName;
    private String fundHouse;
    private Double currPrice;
    private String symbol;


    public MutualFundInfoService getMutualFundInfo(String mfName) {
        String url = "/mf/get/details/";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("schemaId", mfName);
    
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);
    
        ResponseEntity<MutualFundInfoService> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, MutualFundInfoService.class);
    
        if (response.getStatusCode() == HttpStatus.OK) {
            MutualFundInfoService mutualFundInfo = response.getBody();
            if (mutualFundInfo != null) {
                this.schemaName = mutualFundInfo.getSchemaName();
                this.currPrice = mutualFundInfo.getCurrPrice();
                this.fundHouse = mutualFundInfo.getFundHouse();
                this.symbol = mutualFundInfo.getSymbol();
            }
            return mutualFundInfo;
        } else {
            return null;
        }
    }

    public String getSymbol() {
        return symbol;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public Double getCurrPrice() {
        return currPrice;
    }

    public String getFundHouse() {
        return fundHouse;
    }
}
