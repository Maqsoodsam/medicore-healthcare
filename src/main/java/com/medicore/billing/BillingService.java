package com.medicore.billing;

public class BillingService {
    private BillingStrategy billingStrategy;

    public BillingService(BillingStrategy billingStrategy)
    {
        this.billingStrategy=billingStrategy;
    }
    public double calculateFee(double baseFee)
    {
        return billingStrategy.calculateFee(baseFee);
    }
}
