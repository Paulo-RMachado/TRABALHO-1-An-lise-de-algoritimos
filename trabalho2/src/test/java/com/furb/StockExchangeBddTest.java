package com.furb;

import com.furb.domain.ConditionalOrder;
import com.furb.domain.Investor;
import com.furb.domain.Order;
import com.furb.domain.OrderType;
import com.furb.domain.Stock;
import com.furb.domain.TriggerConditionType;
import com.furb.facade.StockExchangeFacade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("BDD - Bolsa de valores")
class StockExchangeBddTest {

    @Nested
    @DisplayName("Given uma ação cadastrada")
    class GivenRegisteredStock {

        @Test
        @DisplayName("When agendo ordem condicional com gatilho já atendido Then a ordem é disparada imediatamente")
        void whenScheduleConditionalOrderWithAlreadyMetTriggerThenOrderTriggersImmediately() {
            StockExchangeFacade facade = new StockExchangeFacade();
            Stock stock = facade.createStock("PETR4", 23.50);
            Investor investor = new Investor("Ana");

            facade.scheduleConditionalOrder(
                    "PETR4",
                    investor.createConditionalOrder(OrderType.BUY, 22.90, TriggerConditionType.LESS_OR_EQUAL, 23.50)
            );

            assertEquals(0, stock.getConditionalOrders().size());
            assertEquals(1, stock.getOpenOrders().size());
            assertEquals(OrderType.BUY, stock.getOpenOrders().get(0).getType());
            assertEquals(22.90, stock.getOpenOrders().get(0).getPrice(), 0.0001);
        }

        @Test
        @DisplayName("When ocorre match Then a notificação contém ação, valor antigo e novo")
        void whenMatchHappensThenNotificationContainsStockAndPrices() {
            StockExchangeFacade facade = new StockExchangeFacade();
            facade.createStock("PETR4", 23.50);
            Investor seller = new Investor("Mariana");
            Investor buyer = new Investor("Joaquim");

            facade.subscribeInvestor("PETR4", seller);
            facade.subscribeInvestor("PETR4", buyer);
            facade.placeOrder("PETR4", seller.createOrder(OrderType.SELL, 24.00));
            facade.placeOrder("PETR4", buyer.createOrder(OrderType.BUY, 24.00));

            String sellerMessage = seller.getNotifications().get(0);
            String buyerMessage = buyer.getNotifications().get(0);

            assertTrue(sellerMessage.contains("PETR4"));
            assertTrue(sellerMessage.contains("23.5"));
            assertTrue(sellerMessage.contains("24.0"));
            assertTrue(buyerMessage.contains("PETR4"));
            assertTrue(buyerMessage.contains("23.5"));
            assertTrue(buyerMessage.contains("24.0"));
        }

        @Test
        @DisplayName("When tento inscrever observer nulo Then recebo exceção")
        void whenSubscribeNullObserverThenThrowsException() {
            Stock stock = new Stock("PETR4", 30.00);

            assertThrows(IllegalArgumentException.class, () -> stock.subscribe(null));
        }

        @Test
        @DisplayName("When tento registrar ordem nula Then recebo exceção")
        void whenPlaceNullOrderThenThrowsException() {
            Stock stock = new Stock("PETR4", 30.00);

            assertThrows(IllegalArgumentException.class, () -> stock.placeOrder(null));
        }

        @Test
        @DisplayName("When tento agendar ordem condicional nula Then recebo exceção")
        void whenScheduleNullConditionalOrderThenThrowsException() {
            Stock stock = new Stock("PETR4", 30.00);

            assertThrows(IllegalArgumentException.class, () -> stock.scheduleOrder(null));
        }
    }

    @Nested
    @DisplayName("Given validações de domínio")
    class GivenDomainValidations {

        @Test
        @DisplayName("When crio ação com nome vazio Then recebo exceção")
        void whenCreateStockWithBlankNameThenThrowsException() {
            assertThrows(IllegalArgumentException.class, () -> new Stock(" ", 10.00));
        }

        @Test
        @DisplayName("When crio ação com preço inválido Then recebo exceção")
        void whenCreateStockWithInvalidPriceThenThrowsException() {
            assertThrows(IllegalArgumentException.class, () -> new Stock("PETR4", 0));
        }

        @Test
        @DisplayName("When crio ordem com tipo nulo Then recebo exceção")
        void whenCreateOrderWithNullTypeThenThrowsException() {
            assertThrows(IllegalArgumentException.class, () -> new Order("Ana", null, 10.0));
        }

        @Test
        @DisplayName("When crio ordem condicional com gatilho nulo Then recebo exceção")
        void whenCreateConditionalOrderWithNullTriggerTypeThenThrowsException() {
            assertThrows(IllegalArgumentException.class, () ->
                    new ConditionalOrder("Ana", OrderType.BUY, 10.0, null, 9.5));
        }
    }

    @Nested
    @DisplayName("Given condições de disparo")
    class GivenTriggerConditions {

        @Test
        @DisplayName("When avalio GREATER_OR_EQUAL Then respeita o limite")
        void whenEvaluateGreaterOrEqualThenRespectsBoundary() {
            ConditionalOrder conditionalOrder = new ConditionalOrder(
                    "Ana", OrderType.SELL, 30.0, TriggerConditionType.GREATER_OR_EQUAL, 24.0
            );

            assertTrue(conditionalOrder.shouldTrigger(24.0));
            assertTrue(conditionalOrder.shouldTrigger(24.1));
        }

        @Test
        @DisplayName("When avalio LESS_OR_EQUAL Then respeita o limite")
        void whenEvaluateLessOrEqualThenRespectsBoundary() {
            ConditionalOrder conditionalOrder = new ConditionalOrder(
                    "Ana", OrderType.BUY, 30.0, TriggerConditionType.LESS_OR_EQUAL, 24.0
            );

            assertTrue(conditionalOrder.shouldTrigger(24.0));
            assertTrue(conditionalOrder.shouldTrigger(23.9));
        }
    }
}
