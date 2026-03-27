package com.furb;

import br.furb.analise.algoritmos.ArCondicionadoGellaKaza;
import br.furb.analise.algoritmos.ArCondicionadoVentoBaumn;
import br.furb.analise.algoritmos.LampadaPhellipes;
import br.furb.analise.algoritmos.LampadaShoyuMi;
import br.furb.analise.algoritmos.PersianaNatLight;
import br.furb.analise.algoritmos.PersianaSolarius;
import com.furb.adapter.ArCondicionadoGellaKazaAdapter;
import com.furb.adapter.ArCondicionadoVentoBaumnAdapter;
import com.furb.adapter.LampadaPhellipesAdapter;
import com.furb.adapter.LampadaShoyuMiAdapter;
import com.furb.adapter.PersianaNatLightAdapter;
import com.furb.adapter.PersianaSolariusAdapter;
import com.furb.facade.ControladorCasa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ModosTest {

    private ControladorCasa casa;

    @BeforeEach
    void setUp() {
        casa = new ControladorCasa();
        casa.addLampada(new LampadaShoyuMiAdapter(new LampadaShoyuMi()));
        casa.addLampada(new LampadaPhellipesAdapter(new LampadaPhellipes()));
        casa.addPersiana(new PersianaSolariusAdapter(new PersianaSolarius()));
        casa.addPersiana(new PersianaNatLightAdapter(new PersianaNatLight()));
        casa.addArCondicionado(new ArCondicionadoVentoBaumnAdapter(new ArCondicionadoVentoBaumn()));
        casa.addArCondicionado(new ArCondicionadoGellaKazaAdapter(new ArCondicionadoGellaKaza()));
    }

    @Test
    void modoTrabalhoLigaLampadasEAcAbrePersianas() {
        casa.modoTrabalho();

        casa.getLampadas().forEach(l -> assertTrue(l.estaLigada()));
        casa.getArCondicionados().forEach(ac -> assertTrue(ac.estaLigado()));
        casa.getArCondicionados().forEach(ac -> assertEquals(25, ac.getTemperatura()));
        casa.getPersianas().forEach(p -> assertTrue(p.estaAberta()));
    }

    @Test
    void modoSonoDesligaTudoEFechaPersianas() {
        casa.modoTrabalho();
        casa.modoSono();

        casa.getLampadas().forEach(l -> assertFalse(l.estaLigada()));
        casa.getArCondicionados().forEach(ac -> assertFalse(ac.estaLigado()));
        casa.getPersianas().forEach(p -> assertFalse(p.estaAberta()));
    }

    @Test
    void modoTrabalhoDepoisModoSonoReativaCorretamente() {
        casa.modoSono();
        casa.modoTrabalho();

        casa.getLampadas().forEach(l -> assertTrue(l.estaLigada()));
        casa.getArCondicionados().forEach(ac -> assertTrue(ac.estaLigado()));
        casa.getArCondicionados().forEach(ac -> assertEquals(25, ac.getTemperatura()));
        casa.getPersianas().forEach(p -> assertTrue(p.estaAberta()));
    }

    @Test
    void modoTrabalhoComTodosDispositivosDeTodasMarcas() {
        casa.modoTrabalho();

        assertEquals(2, casa.getLampadas().size());
        assertEquals(2, casa.getPersianas().size());
        assertEquals(2, casa.getArCondicionados().size());

        casa.getLampadas().forEach(l -> assertTrue(l.estaLigada()));
        casa.getPersianas().forEach(p -> assertTrue(p.estaAberta()));
        casa.getArCondicionados().forEach(ac -> {
            assertTrue(ac.estaLigado());
            assertEquals(25, ac.getTemperatura());
        });
    }

    @Test
    void modoSonoComTodosDispositivosDeTodasMarcas() {
        casa.modoTrabalho();
        casa.modoSono();

        casa.getLampadas().forEach(l -> assertFalse(l.estaLigada()));
        casa.getPersianas().forEach(p -> assertFalse(p.estaAberta()));
        casa.getArCondicionados().forEach(ac -> assertFalse(ac.estaLigado()));
    }
}
