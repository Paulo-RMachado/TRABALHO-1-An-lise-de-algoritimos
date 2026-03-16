package com.furb;

import com.furb.domain.Investor;
import com.furb.domain.OrderType;
import com.furb.facade.StockExchangeFacade;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidationTest {

    @Test
    void naoPermiteCriarAcaoDuplicada() {
        StockExchangeFacade facade = new StockExchangeFacade();
        facade.createStock("BBAS3", 20.0);

        assertThrows(IllegalArgumentException.class, () -> facade.createStock("BBAS3", 25.0));
    }

    @Test
    void naoPermiteOrdemComPrecoInvalido() {
        Investor investor = new Investor("Mariana");
        assertThrows(IllegalArgumentException.class, () -> investor.createOrder(OrderType.BUY, 0));
    }

    @Test
    void naoPermiteConsultarAcaoInexistente() {
        StockExchangeFacade facade = new StockExchangeFacade();
        assertThrows(IllegalArgumentException.class, () -> facade.getStock("ITUB4"));
    }
}
