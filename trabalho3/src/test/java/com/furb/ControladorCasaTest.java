package com.furb;

import br.furb.analise.algoritmos.ArCondicionadoVentoBaumn;
import br.furb.analise.algoritmos.LampadaShoyuMi;
import br.furb.analise.algoritmos.PersianaSolarius;
import com.furb.adapter.ArCondicionadoVentoBaumnAdapter;
import com.furb.adapter.LampadaShoyuMiAdapter;
import com.furb.adapter.PersianaSolariusAdapter;
import com.furb.facade.ControladorCasa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ControladorCasaTest {

    private ControladorCasa casa;

    @BeforeEach
    void setUp() {
        casa = new ControladorCasa();
        casa.addLampada(new LampadaShoyuMiAdapter(new LampadaShoyuMi()));
        casa.addPersiana(new PersianaSolariusAdapter(new PersianaSolarius()));
        casa.addArCondicionado(new ArCondicionadoVentoBaumnAdapter(new ArCondicionadoVentoBaumn()));
    }

    @Test
    void ligarLampadasLigaTodas() {
        casa.ligarLampadas();
        casa.getLampadas().forEach(l -> assertTrue(l.estaLigada()));
    }

    @Test
    void desligarLampadasDesligaTodas() {
        casa.ligarLampadas();
        casa.desligarLampadas();
        casa.getLampadas().forEach(l -> assertFalse(l.estaLigada()));
    }

    @Test
    void abrirPersianasAbreTodas() {
        casa.abrirPersianas();
        casa.getPersianas().forEach(p -> assertTrue(p.estaAberta()));
    }

    @Test
    void fecharPersianasFechaTodas() {
        casa.abrirPersianas();
        casa.fecharPersianas();
        casa.getPersianas().forEach(p -> assertFalse(p.estaAberta()));
    }

    @Test
    void ligarArCondicionadosLigaTodos() {
        casa.ligarArCondicionados();
        casa.getArCondicionados().forEach(ac -> assertTrue(ac.estaLigado()));
    }

    @Test
    void desligarArCondicionadosDesligaTodos() {
        casa.ligarArCondicionados();
        casa.desligarArCondicionados();
        casa.getArCondicionados().forEach(ac -> assertFalse(ac.estaLigado()));
    }

    @Test
    void aumentarTemperaturaAumentaEmTodos() {
        casa.ligarArCondicionados();
        casa.getArCondicionados().forEach(ac -> {
            int antes = ac.getTemperatura();
            casa.aumentarTemperatura();
            assertEquals(antes + 1, ac.getTemperatura());
        });
    }

    @Test
    void diminuirTemperaturaDiminuiEmTodos() {
        casa.ligarArCondicionados();
        casa.getArCondicionados().forEach(ac -> {
            int antes = ac.getTemperatura();
            casa.diminuirTemperatura();
            assertEquals(antes - 1, ac.getTemperatura());
        });
    }

    @Test
    void definirTemperaturaDefineTodos() {
        casa.ligarArCondicionados();
        casa.definirTemperatura(22);
        casa.getArCondicionados().forEach(ac -> assertEquals(22, ac.getTemperatura()));
    }
}
