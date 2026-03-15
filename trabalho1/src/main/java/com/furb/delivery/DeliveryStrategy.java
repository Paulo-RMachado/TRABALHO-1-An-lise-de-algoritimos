package com.furb.delivery;

public interface DeliveryStrategy {
    double calculateShippingCost(int totalWeightGrams);
}
