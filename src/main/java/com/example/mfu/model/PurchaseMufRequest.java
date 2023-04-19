package com.example.mfu.model;

import java.math.BigDecimal;

public class PurchaseMufRequest {
    private Long customerId;
    private String mfName;
    private BigDecimal amount;
    private Integer units;

    private Long schemaId;

    public Long getCustomerId() {
        return customerId;
    }

    public String getMfName() {
        return mfName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public int getUnits() {
        return units;
    }

}
