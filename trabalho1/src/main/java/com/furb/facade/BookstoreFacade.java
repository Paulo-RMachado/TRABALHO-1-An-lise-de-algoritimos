package com.furb.facade;

import com.furb.delivery.ShippingService;
import com.furb.domain.DeliveryType;
import com.furb.domain.Order;
import com.furb.domain.OrderSummary;
import com.furb.domain.Product;

public class BookstoreFacade {
    private final ShippingService shippingService;

    public BookstoreFacade() {
        this.shippingService = ShippingService.getInstance();
    }

    public Order createOrder() {
        return new Order();
    }

    public void addProduct(Order order, Product product) {
        if (order == null) {
            throw new IllegalArgumentException("Pedido não pode ser nulo.");
        }
        order.addProduct(product);
    }

    public void setDeliveryType(Order order, DeliveryType deliveryType) {
        if (order == null) {
            throw new IllegalArgumentException("Pedido não pode ser nulo.");
        }
        order.setDeliveryType(deliveryType);
    }

    public OrderSummary checkout(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Pedido não pode ser nulo.");
        }
        double shippingCost = shippingService.calculateShippingCost(order);
        return new OrderSummary(order.getTotalPrice(), shippingCost, order.getTotalWeightGrams(), order.getDeliveryType());
    }
}
