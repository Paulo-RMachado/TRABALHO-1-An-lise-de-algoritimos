package com.furb;

import com.furb.delivery.DeliveryStrategy;
import com.furb.delivery.PacDeliveryStrategy;
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
    void sedexAcimaDe1KgCalculaAdicional() {
        DeliveryStrategy strategy = new SedexDeliveryStrategy();
        assertEquals(49.5, strategy.calculateShippingCost(1200), 0.0001);
    }
}
