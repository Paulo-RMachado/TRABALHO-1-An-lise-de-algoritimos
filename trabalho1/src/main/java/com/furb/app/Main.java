package com.furb.app;

import com.furb.domain.DeliveryType;
import com.furb.domain.Order;
import com.furb.domain.OrderSummary;
import com.furb.domain.Product;
import com.furb.facade.BookstoreFacade;
import com.furb.observer.ConsoleOrderObserver;

public class Main {
    public static void main(String[] args) {
        BookstoreFacade facade = new BookstoreFacade();
        Order order = facade.createOrder();
        order.addObserver(new ConsoleOrderObserver());

        facade.addProduct(order, new Product("Livro Algoritmos", 79.90, 650));
        facade.addProduct(order, new Product("Livro Estruturas de Dados", 99.90, 850));

        facade.setDeliveryType(order, DeliveryType.SEDEX);

        OrderSummary summary = facade.checkout(order);
        System.out.println(summary);
    }
}
