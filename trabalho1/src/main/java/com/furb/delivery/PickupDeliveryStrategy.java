package com.furb.delivery;

public class PickupDeliveryStrategy implements DeliveryStrategy {
    @Override
    public double calculateShippingCost(int totalWeightGrams) {
        return 0.0;
    }
}
