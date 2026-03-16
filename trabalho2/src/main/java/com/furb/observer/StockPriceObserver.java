package com.furb.observer;

import com.furb.domain.Stock;

public interface StockPriceObserver {
    void onStockPriceChanged(Stock stock, double oldPrice, double newPrice);
}
