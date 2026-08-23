package com.medicore.billing;

public interface BillingStrategy {
    double calculateFee(double baseFee);
}
