package com.furb;

import com.furb.domain.DeliveryType;
import com.furb.domain.Order;
import com.furb.domain.OrderSummary;
import com.furb.domain.Product;
import com.furb.facade.BookstoreFacade;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
