package com.furb;

import com.furb.delivery.DeliveryStrategy;
import com.furb.delivery.PacDeliveryStrategy;
import com.furb.delivery.PickupDeliveryStrategy;
import com.furb.delivery.SedexDeliveryStrategy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeliveryStrategyTest {

    @Test
    void pacAte1KgCustaDez() {
        DeliveryStrategy strategy = new PacDeliveryStrategy();
        assertEquals(10.0, strategy.calculateShippingCost(1000));
    }

    @Test
    void pacEntre1e2KgCustaQuinze() {
        DeliveryStrategy strategy = new PacDeliveryStrategy();
        assertEquals(15.0, strategy.calculateShippingCost(1001));
        assertEquals(15.0, strategy.calculateShippingCost(2000));
    }

    @Test
    void pacComPesoInvalidoLancaExcecao() {
        DeliveryStrategy strategy = new PacDeliveryStrategy();
        assertThrows(IllegalArgumentException.class, () -> strategy.calculateShippingCost(0));
    }

    @Test
    void pacAcimaDe2KgNaoAceita() {
        DeliveryStrategy strategy = new PacDeliveryStrategy();
        assertThrows(IllegalArgumentException.class, () -> strategy.calculateShippingCost(2500));
    }

    @Test
    void sedexAte500gCustaDozeE50() {
        DeliveryStrategy strategy = new SedexDeliveryStrategy();
        assertEquals(12.5, strategy.calculateShippingCost(500));
    }

    @Test
    void sedexDe500Ate1KgCustaVinte() {
        DeliveryStrategy strategy = new SedexDeliveryStrategy();
        assertEquals(20.0, strategy.calculateShippingCost(501));
        assertEquals(20.0, strategy.calculateShippingCost(1000));
    }

    @Test
    void sedexAcimaDe1KgCalculaAdicional() {
        DeliveryStrategy strategy = new SedexDeliveryStrategy();
        assertEquals(48.0, strategy.calculateShippingCost(1100), 0.0001);
        assertEquals(49.5, strategy.calculateShippingCost(1200), 0.0001);
    }

    @Test
    void sedexComPesoInvalidoLancaExcecao() {
        DeliveryStrategy strategy = new SedexDeliveryStrategy();
        assertThrows(IllegalArgumentException.class, () -> strategy.calculateShippingCost(0));
    }

    @Test
    void retiradaSempreSemCusto() {
        DeliveryStrategy strategy = new PickupDeliveryStrategy();
        assertEquals(0.0, strategy.calculateShippingCost(1));
        assertEquals(0.0, strategy.calculateShippingCost(5000));
    }
}
