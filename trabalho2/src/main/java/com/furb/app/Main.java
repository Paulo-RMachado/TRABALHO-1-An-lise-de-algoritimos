package com.furb.app;

import com.furb.domain.Investor;
import com.furb.domain.OrderType;
import com.furb.domain.Stock;
import com.furb.domain.TriggerConditionType;
import com.furb.facade.StockExchangeFacade;

public class Main {
    public static void main(String[] args) {
        StockExchangeFacade facade = new StockExchangeFacade();
        Stock bbas3 = facade.createStock("BBAS3", 23.50);

        Investor mariana = new Investor("Mariana");
        Investor joaquim = new Investor("Joaquim");

        facade.subscribeInvestor("BBAS3", mariana);
        facade.subscribeInvestor("BBAS3", joaquim);

        facade.placeOrder("BBAS3", mariana.createOrder(OrderType.SELL, 24.00));
        facade.placeOrder("BBAS3", joaquim.createOrder(OrderType.BUY, 24.00));

        facade.scheduleConditionalOrder(
                "BBAS3",
                joaquim.createConditionalOrder(OrderType.BUY, 23.00, TriggerConditionType.LESS_OR_EQUAL, 23.50)
        );

        System.out.println("Preço atual da ação " + bbas3.getName() + ": R$" + bbas3.getPrice());
        System.out.println("Notificações da Mariana: " + mariana.getNotifications().size());
        System.out.println("Notificações do Joaquim: " + joaquim.getNotifications().size());
    }
}
