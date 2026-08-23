package com.medicore.billing;

public class BillingStrategyDemo {

    public static void main(String[] args) {

        double baseFee = 100.0;

        BillingService standardBilling =
                new BillingService(new StandardBillingStrategy());

        BillingService concessionBilling =
                new BillingService(new ConcessionBillingStrategy());

        BillingService insuredBilling =
                new BillingService(new InsuredBillingStrategy());

        System.out.println(
                "Standard Patient Fee: $" +
                        standardBilling.calculateFee(baseFee));

        System.out.println(
                "Concession Patient Fee: $" +
                        concessionBilling.calculateFee(baseFee));

        System.out.println(
                "Insured Patient Fee: $" +
                        insuredBilling.calculateFee(baseFee));
    }
}
