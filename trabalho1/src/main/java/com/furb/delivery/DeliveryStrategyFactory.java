package com.furb.delivery;

import com.furb.domain.DeliveryType;

public class DeliveryStrategyFactory {
    public DeliveryStrategy createStrategy(DeliveryType deliveryType) {
        if (deliveryType == null) {
            throw new IllegalArgumentException("Modalidade de entrega não pode ser nula.");
        }
        return switch (deliveryType) {
            case PAC -> new PacDeliveryStrategy();
            case SEDEX -> new SedexDeliveryStrategy();
            case PICKUP -> new PickupDeliveryStrategy();
        };
    }
}
