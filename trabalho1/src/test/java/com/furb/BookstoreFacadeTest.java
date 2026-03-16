package com.furb;

import com.furb.domain.DeliveryType;
import com.furb.domain.Order;
import com.furb.domain.OrderSummary;
import com.furb.domain.Product;
import com.furb.facade.BookstoreFacade;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BookstoreFacadeTest {

    @Test
    void checkoutCalculaTotaisCorretamente() {
        BookstoreFacade facade = new BookstoreFacade();
        Order order = facade.createOrder();

        facade.addProduct(order, new Product("Livro 1", 50.0, 400));
        facade.addProduct(order, new Product("Livro 2", 60.0, 700));

        facade.setDeliveryType(order, DeliveryType.SEDEX);

        OrderSummary summary = facade.checkout(order);

        assertEquals(110.0, summary.getProductsTotal());
        assertEquals(48.0, summary.getShippingCost(), 0.0001);
        assertEquals(158.0, summary.getGrandTotal(), 0.0001);
        assertEquals(1100, summary.getTotalWeightGrams());
    }

    @Test
    void checkoutComPacCalculaFreteCorretamente() {
        BookstoreFacade facade = new BookstoreFacade();
        Order order = facade.createOrder();

        facade.addProduct(order, new Product("Livro", 80.0, 900));
        facade.setDeliveryType(order, DeliveryType.PAC);

        OrderSummary summary = facade.checkout(order);

        assertEquals(80.0, summary.getProductsTotal(), 0.0001);
        assertEquals(10.0, summary.getShippingCost(), 0.0001);
        assertEquals(90.0, summary.getGrandTotal(), 0.0001);
    }

    @Test
    void checkoutComRetiradaTemFreteZero() {
        BookstoreFacade facade = new BookstoreFacade();
        Order order = facade.createOrder();

        facade.addProduct(order, new Product("Livro", 30.0, 300));
        facade.setDeliveryType(order, DeliveryType.PICKUP);

        OrderSummary summary = facade.checkout(order);

        assertEquals(0.0, summary.getShippingCost(), 0.0001);
        assertEquals(30.0, summary.getGrandTotal(), 0.0001);
    }

    @Test
    void checkoutSemTipoEntregaLancaExcecao() {
        BookstoreFacade facade = new BookstoreFacade();
        Order order = facade.createOrder();
        facade.addProduct(order, new Product("Livro", 20.0, 200));

        assertThrows(IllegalStateException.class, () -> facade.checkout(order));
    }

    @Test
    void checkoutComPedidoNuloLancaExcecao() {
        BookstoreFacade facade = new BookstoreFacade();
        assertThrows(IllegalArgumentException.class, () -> facade.checkout(null));
    }

    @Test
    void addProductComPedidoNuloLancaExcecao() {
        BookstoreFacade facade = new BookstoreFacade();
        Product product = new Product("Livro", 20.0, 200);

        assertThrows(IllegalArgumentException.class, () -> facade.addProduct(null, product));
    }

    @Test
    void setDeliveryTypeComPedidoNuloLancaExcecao() {
        BookstoreFacade facade = new BookstoreFacade();

        assertThrows(IllegalArgumentException.class, () -> facade.setDeliveryType(null, DeliveryType.PAC));
    }
}
