package com.furb.adapter;

import br.furb.analise.algoritmos.ArCondicionadoVentoBaumn;

public class ArCondicionadoVentoBaumnAdapter implements ArCondicionado {

    private final ArCondicionadoVentoBaumn ac;
    private boolean ligado;

    public ArCondicionadoVentoBaumnAdapter(ArCondicionadoVentoBaumn ac) {
        this.ac = ac;
    }

    @Override
    public void ligar() {
        ac.ligar();
        ligado = true;
    }

    @Override
    public void desligar() {
        ac.desligar();
        ligado = false;
    }

    @Override
    public boolean estaLigado() {
        return ligado;
    }

    @Override
    public void aumentarTemperatura() {
        ac.definirTemperatura(ac.getTemperatura() + 1);
    }

    @Override
    public void diminuirTemperatura() {
        ac.definirTemperatura(ac.getTemperatura() - 1);
    }

    @Override
    public void definirTemperatura(int temperatura) {
        ac.definirTemperatura(temperatura);
    }

    @Override
    public int getTemperatura() {
        return ac.getTemperatura();
    }
}
