package com.medicore.billing;

public class StandardBillingStrategy implements BillingStrategy {

    @Override
    public double calculateFee(double baseFee){
        return baseFee;
    }
}
