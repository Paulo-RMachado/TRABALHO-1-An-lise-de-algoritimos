package com.furb.adapter;

public interface ArCondicionado {

    void ligar();

    void desligar();

    boolean estaLigado();

    void aumentarTemperatura();

    void diminuirTemperatura();

    void definirTemperatura(int temperatura);

    int getTemperatura();
}
