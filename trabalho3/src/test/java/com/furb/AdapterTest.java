package com.furb;

import br.furb.analise.algoritmos.ArCondicionadoGellaKaza;
import br.furb.analise.algoritmos.ArCondicionadoVentoBaumn;
import br.furb.analise.algoritmos.LampadaPhellipes;
import br.furb.analise.algoritmos.LampadaShoyuMi;
import br.furb.analise.algoritmos.PersianaNatLight;
import br.furb.analise.algoritmos.PersianaSolarius;
import com.furb.adapter.ArCondicionado;
import com.furb.adapter.ArCondicionadoGellaKazaAdapter;
import com.furb.adapter.ArCondicionadoVentoBaumnAdapter;
import com.furb.adapter.Lampada;
import com.furb.adapter.LampadaPhellipesAdapter;
import com.furb.adapter.LampadaShoyuMiAdapter;
import com.furb.adapter.Persiana;
import com.furb.adapter.PersianaNatLightAdapter;
import com.furb.adapter.PersianaSolariusAdapter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AdapterTest {

    // ==================== Lâmpada ShoyuMi ====================

    @Test
    void lampadaShoyuMiLigaCorretamente() {
        Lampada lampada = new LampadaShoyuMiAdapter(new LampadaShoyuMi());
        assertFalse(lampada.estaLigada());

        lampada.ligar();
        assertTrue(lampada.estaLigada());
    }

    @Test
    void lampadaShoyuMiDesligaCorretamente() {
        Lampada lampada = new LampadaShoyuMiAdapter(new LampadaShoyuMi());
        lampada.ligar();
        assertTrue(lampada.estaLigada());

        lampada.desligar();
        assertFalse(lampada.estaLigada());
    }

    // ==================== Lâmpada Phellipes ====================

    @Test
    void lampadaPhellipesLigaComIntensidade100() {
        LampadaPhellipes phellipes = new LampadaPhellipes();
        Lampada lampada = new LampadaPhellipesAdapter(phellipes);
        assertFalse(lampada.estaLigada());

        lampada.ligar();
        assertTrue(lampada.estaLigada());
        assertEquals(100, phellipes.getIntensidade());
    }

    @Test
    void lampadaPhellipesDesligaComIntensidade0() {
        LampadaPhellipes phellipes = new LampadaPhellipes();
        Lampada lampada = new LampadaPhellipesAdapter(phellipes);
        lampada.ligar();

        lampada.desligar();
        assertFalse(lampada.estaLigada());
        assertEquals(0, phellipes.getIntensidade());
    }

    // ==================== Persiana Solarius ====================

    @Test
    void persianaSolariusIniciaAberta() {
        Persiana persiana = new PersianaSolariusAdapter(new PersianaSolarius());
        assertTrue(persiana.estaAberta());
    }

    @Test
    void persianaSolariusFechaEAbreCorretamente() {
        Persiana persiana = new PersianaSolariusAdapter(new PersianaSolarius());
        persiana.fechar();
        assertFalse(persiana.estaAberta());

        persiana.abrir();
        assertTrue(persiana.estaAberta());
    }

    // ==================== Persiana NatLight ====================

    @Test
    void persianaNatLightIniciaAberta() {
        Persiana persiana = new PersianaNatLightAdapter(new PersianaNatLight());
        assertTrue(persiana.estaAberta());
    }

    @Test
    void persianaNatLightFechaEAbreCorretamente() {
        Persiana persiana = new PersianaNatLightAdapter(new PersianaNatLight());
        persiana.fechar();
        assertFalse(persiana.estaAberta());

        persiana.abrir();
        assertTrue(persiana.estaAberta());
    }

    // ==================== Ar-condicionado VentoBaumn ====================

    @Test
    void acVentoBaumnLigaDesligaCorretamente() {
        ArCondicionado ac = new ArCondicionadoVentoBaumnAdapter(new ArCondicionadoVentoBaumn());
        assertFalse(ac.estaLigado());

        ac.ligar();
        assertTrue(ac.estaLigado());

        ac.desligar();
        assertFalse(ac.estaLigado());
    }

    @Test
    void acVentoBaumnIniciaTemperatura24() {
        ArCondicionado ac = new ArCondicionadoVentoBaumnAdapter(new ArCondicionadoVentoBaumn());
        assertEquals(24, ac.getTemperatura());
    }

    @Test
    void acVentoBaumnDefineTemperaturaCorretamente() {
        ArCondicionado ac = new ArCondicionadoVentoBaumnAdapter(new ArCondicionadoVentoBaumn());
        ac.ligar();
        ac.definirTemperatura(20);
        assertEquals(20, ac.getTemperatura());
    }

    @Test
    void acVentoBaumnAumentaTemperaturaEm1() {
        ArCondicionado ac = new ArCondicionadoVentoBaumnAdapter(new ArCondicionadoVentoBaumn());
        ac.ligar();
        int tempInicial = ac.getTemperatura();

        ac.aumentarTemperatura();
        assertEquals(tempInicial + 1, ac.getTemperatura());
    }

    @Test
    void acVentoBaumnDiminuiTemperaturaEm1() {
        ArCondicionado ac = new ArCondicionadoVentoBaumnAdapter(new ArCondicionadoVentoBaumn());
        ac.ligar();
        int tempInicial = ac.getTemperatura();

        ac.diminuirTemperatura();
        assertEquals(tempInicial - 1, ac.getTemperatura());
    }

    // ==================== Ar-condicionado GellaKaza ====================

    @Test
    void acGellaKazaLigaDesligaCorretamente() {
        ArCondicionado ac = new ArCondicionadoGellaKazaAdapter(new ArCondicionadoGellaKaza());
        assertFalse(ac.estaLigado());

        ac.ligar();
        assertTrue(ac.estaLigado());

        ac.desligar();
        assertFalse(ac.estaLigado());
    }

    @Test
    void acGellaKazaIniciaTemperatura28() {
        ArCondicionadoGellaKaza gellaKaza = new ArCondicionadoGellaKaza();
        gellaKaza.ativar();
        ArCondicionado ac = new ArCondicionadoGellaKazaAdapter(gellaKaza);
        assertEquals(28, ac.getTemperatura());
    }

    @Test
    void acGellaKazaDefineTemperaturaCorretamente() {
        ArCondicionadoGellaKaza gellaKaza = new ArCondicionadoGellaKaza();
        gellaKaza.ativar();
        ArCondicionado ac = new ArCondicionadoGellaKazaAdapter(gellaKaza);

        ac.definirTemperatura(22);
        assertEquals(22, ac.getTemperatura());
    }

    @Test
    void acGellaKazaAumentaTemperaturaEm1() {
        ArCondicionadoGellaKaza gellaKaza = new ArCondicionadoGellaKaza();
        gellaKaza.ativar();
        ArCondicionado ac = new ArCondicionadoGellaKazaAdapter(gellaKaza);
        int tempInicial = ac.getTemperatura();

        ac.aumentarTemperatura();
        assertEquals(tempInicial + 1, ac.getTemperatura());
    }

    @Test
    void acGellaKazaDiminuiTemperaturaEm1() {
        ArCondicionadoGellaKaza gellaKaza = new ArCondicionadoGellaKaza();
        gellaKaza.ativar();
        ArCondicionado ac = new ArCondicionadoGellaKazaAdapter(gellaKaza);
        int tempInicial = ac.getTemperatura();

        ac.diminuirTemperatura();
        assertEquals(tempInicial - 1, ac.getTemperatura());
    }
}
