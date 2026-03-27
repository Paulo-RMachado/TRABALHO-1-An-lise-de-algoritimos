package com.furb.adapter;

import br.furb.analise.algoritmos.LampadaPhellipes;

public class LampadaPhellipesAdapter implements Lampada {

    private final LampadaPhellipes lampada;

    public LampadaPhellipesAdapter(LampadaPhellipes lampada) {
        this.lampada = lampada;
    }

    @Override
    public void ligar() {
        lampada.setIntensidade(100);
    }

    @Override
    public void desligar() {
        lampada.setIntensidade(0);
    }

    @Override
    public boolean estaLigada() {
        return lampada.getIntensidade() > 0;
    }
}
