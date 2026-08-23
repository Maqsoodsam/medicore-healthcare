package com.medicore.billing;

public class InsuredBillingStrategy implements BillingStrategy {
    @Override
    public double calculateFee(double baseFee){
        return baseFee * 0.30;
    }
}
