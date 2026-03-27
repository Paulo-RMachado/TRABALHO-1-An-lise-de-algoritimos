package com.furb.adapter;

import br.furb.analise.algoritmos.ArCondicionadoGellaKaza;

public class ArCondicionadoGellaKazaAdapter implements ArCondicionado {

    private final ArCondicionadoGellaKaza ac;

    public ArCondicionadoGellaKazaAdapter(ArCondicionadoGellaKaza ac) {
        this.ac = ac;
    }

    @Override
    public void ligar() {
        ac.ativar();
    }

    @Override
    public void desligar() {
        ac.desativar();
    }

    @Override
    public boolean estaLigado() {
        return ac.estaLigado();
    }

    @Override
    public void aumentarTemperatura() {
        ac.aumentarTemperatura();
    }

    @Override
    public void diminuirTemperatura() {
        ac.diminuirTemperatura();
    }

    @Override
    public void definirTemperatura(int temperatura) {
        while (ac.getTemperatura() < temperatura) {
            ac.aumentarTemperatura();
        }
        while (ac.getTemperatura() > temperatura) {
            ac.diminuirTemperatura();
        }
    }

    @Override
    public int getTemperatura() {
        return ac.getTemperatura();
    }
}
