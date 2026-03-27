package com.furb.app;

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

public class Main {

    public static void main(String[] args) {
        ControladorCasa casa = new ControladorCasa();

        casa.addLampada(new LampadaShoyuMiAdapter(new LampadaShoyuMi()));
        casa.addLampada(new LampadaPhellipesAdapter(new LampadaPhellipes()));
        casa.addPersiana(new PersianaSolariusAdapter(new PersianaSolarius()));
        casa.addPersiana(new PersianaNatLightAdapter(new PersianaNatLight()));
        casa.addArCondicionado(new ArCondicionadoVentoBaumnAdapter(new ArCondicionadoVentoBaumn()));
        casa.addArCondicionado(new ArCondicionadoGellaKazaAdapter(new ArCondicionadoGellaKaza()));

        System.out.println("=== Modo Trabalho ===");
        casa.modoTrabalho();
        imprimirEstado(casa);

        System.out.println("\n=== Modo Sono ===");
        casa.modoSono();
        imprimirEstado(casa);
    }

    private static void imprimirEstado(ControladorCasa casa) {
        casa.getLampadas().forEach(l ->
                System.out.println("Lampada ligada: " + l.estaLigada()));
        casa.getPersianas().forEach(p ->
                System.out.println("Persiana aberta: " + p.estaAberta()));
        casa.getArCondicionados().forEach(ac ->
                System.out.println("AC ligado: " + ac.estaLigado() + " | Temperatura: " + ac.getTemperatura()));
    }
}
