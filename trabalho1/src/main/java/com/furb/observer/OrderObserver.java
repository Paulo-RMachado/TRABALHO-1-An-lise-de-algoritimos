package com.furb.observer;

import com.furb.domain.Order;

public interface OrderObserver {
    void onOrderUpdated(Order order, String event);
}
