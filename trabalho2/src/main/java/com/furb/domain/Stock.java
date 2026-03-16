package com.furb.domain;

import com.furb.observer.StockPriceObserver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Stock {
    private final String name;
    private double price;
    private final List<Order> openOrders = new ArrayList<>();
    private final List<ConditionalOrder> conditionalOrders = new ArrayList<>();
    private final List<StockPriceObserver> observers = new ArrayList<>();

    public Stock(String name, double price) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Nome da ação é obrigatório.");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Valor da ação deve ser maior que zero.");
        }
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public List<Order> getOpenOrders() {
        return Collections.unmodifiableList(openOrders);
    }

    public List<ConditionalOrder> getConditionalOrders() {
        return Collections.unmodifiableList(conditionalOrders);
    }

    public void subscribe(StockPriceObserver observer) {
        if (observer == null) {
            throw new IllegalArgumentException("Observer não pode ser nulo.");
        }
        observers.add(observer);
    }

    public void placeOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Ordem não pode ser nula.");
        }

        Order matchedOrder = findMatch(order);
        if (matchedOrder != null) {
            openOrders.remove(matchedOrder);
            updatePrice(order.getPrice());
            processConditionalOrders();
            return;
        }

        openOrders.add(order);
    }

    public void scheduleOrder(ConditionalOrder conditionalOrder) {
        if (conditionalOrder == null) {
            throw new IllegalArgumentException("Ordem condicional não pode ser nula.");
        }
        conditionalOrders.add(conditionalOrder);
        processConditionalOrders();
    }

    private Order findMatch(Order incoming) {
        for (Order openOrder : openOrders) {
            boolean oppositeType = openOrder.getType() != incoming.getType();
            boolean samePrice = Double.compare(openOrder.getPrice(), incoming.getPrice()) == 0;
            if (oppositeType && samePrice) {
                return openOrder;
            }
        }
        return null;
    }

    private void updatePrice(double newPrice) {
        double oldPrice = this.price;
        this.price = newPrice;
        notifyObservers(oldPrice, newPrice);
    }

    private void notifyObservers(double oldPrice, double newPrice) {
        for (StockPriceObserver observer : observers) {
            observer.onStockPriceChanged(this, oldPrice, newPrice);
        }
    }

    private void processConditionalOrders() {
        boolean triggeredOrder;
        do {
            triggeredOrder = false;
            Iterator<ConditionalOrder> iterator = conditionalOrders.iterator();
            while (iterator.hasNext()) {
                ConditionalOrder conditionalOrder = iterator.next();
                if (conditionalOrder.shouldTrigger(price)) {
                    iterator.remove();
                    placeOrder(conditionalOrder.toOrder());
                    triggeredOrder = true;
                    break;
                }
            }
        } while (triggeredOrder);
    }
}
