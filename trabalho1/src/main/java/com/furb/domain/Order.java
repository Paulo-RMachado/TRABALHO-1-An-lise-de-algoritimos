package com.furb.domain;

import com.furb.observer.OrderObserver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
    private final List<Product> products = new ArrayList<>();
    private final List<OrderObserver> observers = new ArrayList<>();
    private DeliveryType deliveryType;

    public void addObserver(OrderObserver observer) {
        if (observer != null) {
            observers.add(observer);
        }
    }

    public void removeObserver(OrderObserver observer) {
        observers.remove(observer);
    }

    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo.");
        }
        products.add(product);
        notifyObservers("Produto adicionado: " + product.getName());
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
        notifyObservers("Modalidade de entrega definida: " + deliveryType);
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public int getTotalWeightGrams() {
        return products.stream().mapToInt(Product::getWeightGrams).sum();
    }

    public double getTotalPrice() {
        return products.stream().mapToDouble(Product::getPrice).sum();
    }

    private void notifyObservers(String event) {
        for (OrderObserver observer : observers) {
            observer.onOrderUpdated(this, event);
        }
    }
}
