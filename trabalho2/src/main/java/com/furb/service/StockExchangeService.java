package com.furb.service;

import com.furb.domain.ConditionalOrder;
import com.furb.domain.Order;
import com.furb.domain.Stock;

import java.util.HashMap;
import java.util.Map;

public class StockExchangeService {
    private final Map<String, Stock> stocks = new HashMap<>();

    public Stock registerStock(String name, double initialPrice) {
        if (stocks.containsKey(name)) {
            throw new IllegalArgumentException("Ação já cadastrada: " + name);
        }
        Stock stock = new Stock(name, initialPrice);
        stocks.put(name, stock);
        return stock;
    }

    public Stock getStock(String name) {
        Stock stock = stocks.get(name);
        if (stock == null) {
            throw new IllegalArgumentException("Ação não encontrada: " + name);
        }
        return stock;
    }

    public void placeOrder(String stockName, Order order) {
        getStock(stockName).placeOrder(order);
    }

    public void scheduleOrder(String stockName, ConditionalOrder order) {
        getStock(stockName).scheduleOrder(order);
    }
}
