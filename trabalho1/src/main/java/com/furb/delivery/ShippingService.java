package com.furb.delivery;

import com.furb.domain.Order;

public class ShippingService {
    private static final ShippingService INSTANCE = new ShippingService(new DeliveryStrategyFactory());

    private final DeliveryStrategyFactory strategyFactory;

    private ShippingService(DeliveryStrategyFactory strategyFactory) {
        this.strategyFactory = strategyFactory;
    }

    public static ShippingService getInstance() {
        return INSTANCE;
    }

    public double calculateShippingCost(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Pedido não pode ser nulo.");
        }
        if (order.getDeliveryType() == null) {
            throw new IllegalStateException("Pedido sem modalidade de entrega definida.");
        }
        DeliveryStrategy strategy = strategyFactory.createStrategy(order.getDeliveryType());
        return strategy.calculateShippingCost(order.getTotalWeightGrams());
    }
}
