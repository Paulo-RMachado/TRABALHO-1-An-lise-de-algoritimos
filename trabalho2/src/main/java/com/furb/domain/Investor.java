package com.furb.domain;

import com.furb.observer.StockPriceObserver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Investor implements StockPriceObserver {
    private final String name;
    private final List<String> notifications = new ArrayList<>();

    public Investor(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Nome do investidor é obrigatório.");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<String> getNotifications() {
        return Collections.unmodifiableList(notifications);
    }

    public Order createOrder(OrderType type, double price) {
        return new Order(name, type, price);
    }

    public ConditionalOrder createConditionalOrder(OrderType type,
                                                   double orderPrice,
                                                   TriggerConditionType triggerConditionType,
                                                   double triggerPrice) {
        return new ConditionalOrder(name, type, orderPrice, triggerConditionType, triggerPrice);
    }

    @Override
    public void onStockPriceChanged(Stock stock, double oldPrice, double newPrice) {
        notifications.add("Ação " + stock.getName() + " alterou de " + oldPrice + " para " + newPrice);
    }
}
