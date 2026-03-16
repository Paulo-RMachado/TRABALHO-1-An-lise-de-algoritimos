package com.furb;

import com.furb.domain.DeliveryType;
import com.furb.domain.Order;
import com.furb.domain.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DomainValidationTest {

    @Test
    void productComNomeVazioLancaExcecao() {
        assertThrows(IllegalArgumentException.class, () -> new Product(" ", 10.0, 100));
    }

    @Test
    void productComPrecoNegativoLancaExcecao() {
        assertThrows(IllegalArgumentException.class, () -> new Product("Livro", -1.0, 100));
    }

    @Test
    void productComPesoInvalidoLancaExcecao() {
        assertThrows(IllegalArgumentException.class, () -> new Product("Livro", 10.0, 0));
    }

    @Test
    void orderComProdutoNuloLancaExcecao() {
        Order order = new Order();
        assertThrows(IllegalArgumentException.class, () -> order.addProduct(null));
    }

    @Test
    void orderSomaPesoEPrecoCorretamente() {
        Order order = new Order();
        order.addProduct(new Product("Livro A", 20.0, 200));
        order.addProduct(new Product("Livro B", 35.0, 350));
        order.setDeliveryType(DeliveryType.PAC);

        assertEquals(550, order.getTotalWeightGrams());
        assertEquals(55.0, order.getTotalPrice(), 0.0001);
        assertEquals(DeliveryType.PAC, order.getDeliveryType());
    }
}
