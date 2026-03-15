package com.furb.delivery;

public class SedexDeliveryStrategy implements DeliveryStrategy {
    @Override
    public double calculateShippingCost(int totalWeightGrams) {
        if (totalWeightGrams <= 0) {
            throw new IllegalArgumentException("Peso total deve ser maior que zero.");
        }
        if (totalWeightGrams <= 500) {
            return 12.5;
        }
        if (totalWeightGrams <= 1000) {
            return 20.0;
        }
        int additionalWeight = totalWeightGrams - 1000;
        int steps = (int) Math.ceil(additionalWeight / 100.0);
        return 46.5 + (steps * 1.5);
    }
}
