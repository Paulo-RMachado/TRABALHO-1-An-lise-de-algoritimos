package com.furb.observer;

import com.furb.domain.Order;

public class ConsoleOrderObserver implements OrderObserver {
    @Override
    public void onOrderUpdated(Order order, String event) {
        System.out.println("[Pedido] " + event);
    }
}
