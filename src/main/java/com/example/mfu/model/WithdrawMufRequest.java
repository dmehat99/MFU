package com.example.mfu.model;

public class WithdrawMufRequest {
    private Long customerId;
    private String mfName;
    private int units;

    public Long getCustomerId() {
        return customerId;
    }

    public String getMfName() {
        return mfName;
    }

    public int getUnits() {
        return units;
    }
}
