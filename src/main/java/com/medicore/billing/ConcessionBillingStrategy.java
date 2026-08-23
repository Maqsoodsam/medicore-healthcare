package com.medicore.billing;

public class ConcessionBillingStrategy implements BillingStrategy {
    @Override
    public double calculateFee(double baseFee){
        return baseFee *0.80;
    }
}
