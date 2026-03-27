package com.furb.adapter;

import br.furb.analise.algoritmos.LampadaShoyuMi;

public class LampadaShoyuMiAdapter implements Lampada {

    private final LampadaShoyuMi lampada;

    public LampadaShoyuMiAdapter(LampadaShoyuMi lampada) {
        this.lampada = lampada;
    }

    @Override
    public void ligar() {
        lampada.ligar();
    }

    @Override
    public void desligar() {
        lampada.desligar();
    }

    @Override
    public boolean estaLigada() {
        return lampada.estaLigada();
    }
}
