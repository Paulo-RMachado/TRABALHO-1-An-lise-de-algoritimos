package com.furb.facade;

import com.furb.domain.ConditionalOrder;
import com.furb.domain.Investor;
import com.furb.domain.Order;
import com.furb.domain.Stock;
import com.furb.service.StockExchangeService;

public class StockExchangeFacade {
    private final StockExchangeService service = new StockExchangeService();

    public Stock createStock(String name, double initialPrice) {
        return service.registerStock(name, initialPrice);
    }

    public void subscribeInvestor(String stockName, Investor investor) {
        service.getStock(stockName).subscribe(investor);
    }

    public void placeOrder(String stockName, Order order) {
        service.placeOrder(stockName, order);
    }

    public void scheduleConditionalOrder(String stockName, ConditionalOrder order) {
        service.scheduleOrder(stockName, order);
    }

    public Stock getStock(String stockName) {
        return service.getStock(stockName);
    }
}
