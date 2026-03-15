package com.furb.delivery;

public class PacDeliveryStrategy implements DeliveryStrategy {
    @Override
    public double calculateShippingCost(int totalWeightGrams) {
        if (totalWeightGrams <= 0) {
            throw new IllegalArgumentException("Peso total deve ser maior que zero.");
        }
        if (totalWeightGrams <= 1000) {
            return 10.0;
        }
        if (totalWeightGrams <= 2000) {
            return 15.0;
        }
        throw new IllegalArgumentException("PAC não aceita entregas acima de 2 kg.");
    }
}
