package com.furb;

import com.furb.domain.Investor;
import com.furb.domain.OrderType;
import com.furb.domain.Stock;
import com.furb.domain.TriggerConditionType;
import com.furb.facade.StockExchangeFacade;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StockExchangeFlowTest {

    @Test
    void ordemSemMatchPermaneceAberta() {
        StockExchangeFacade facade = new StockExchangeFacade();
        Stock stock = facade.createStock("BBAS3", 23.50);
        Investor mariana = new Investor("Mariana");

        facade.placeOrder("BBAS3", mariana.createOrder(OrderType.SELL, 24.00));

        assertEquals(1, stock.getOpenOrders().size());
        assertEquals(23.50, stock.getPrice(), 0.0001);
    }

    @Test
    void matchRemoveOrdensEAtualizaPreco() {
        StockExchangeFacade facade = new StockExchangeFacade();
        Stock stock = facade.createStock("BBAS3", 23.50);
        Investor mariana = new Investor("Mariana");
        Investor joaquim = new Investor("Joaquim");

        facade.placeOrder("BBAS3", mariana.createOrder(OrderType.SELL, 24.00));
        facade.placeOrder("BBAS3", joaquim.createOrder(OrderType.BUY, 24.00));

        assertEquals(0, stock.getOpenOrders().size());
        assertEquals(24.00, stock.getPrice(), 0.0001);
    }

    @Test
    void investidoresInscritosRecebemNotificacao() {
        StockExchangeFacade facade = new StockExchangeFacade();
        facade.createStock("BBAS3", 23.50);
        Investor mariana = new Investor("Mariana");
        Investor joaquim = new Investor("Joaquim");

        facade.subscribeInvestor("BBAS3", mariana);
        facade.subscribeInvestor("BBAS3", joaquim);

        facade.placeOrder("BBAS3", mariana.createOrder(OrderType.SELL, 24.00));
        facade.placeOrder("BBAS3", joaquim.createOrder(OrderType.BUY, 24.00));

        assertEquals(1, mariana.getNotifications().size());
        assertEquals(1, joaquim.getNotifications().size());
    }

    @Test
    void ordemCondicionalDisparaQuandoCondicaoAtendida() {
        StockExchangeFacade facade = new StockExchangeFacade();
        Stock stock = facade.createStock("BBAS3", 23.50);
        Investor mariana = new Investor("Mariana");
        Investor joaquim = new Investor("Joaquim");

        facade.scheduleConditionalOrder(
                "BBAS3",
                mariana.createConditionalOrder(OrderType.SELL, 24.00, TriggerConditionType.GREATER_OR_EQUAL, 24.00)
        );

        assertEquals(1, stock.getConditionalOrders().size());

        facade.placeOrder("BBAS3", joaquim.createOrder(OrderType.BUY, 24.00));
        facade.placeOrder("BBAS3", mariana.createOrder(OrderType.SELL, 24.00));

        assertEquals(0, stock.getConditionalOrders().size());
        assertEquals(1, stock.getOpenOrders().size());
        assertEquals(OrderType.SELL, stock.getOpenOrders().get(0).getType());
    }
}
