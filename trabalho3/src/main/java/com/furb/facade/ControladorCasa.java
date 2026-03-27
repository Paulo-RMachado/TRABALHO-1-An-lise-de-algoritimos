package com.furb.facade;

import com.furb.adapter.ArCondicionado;
import com.furb.adapter.Lampada;
import com.furb.adapter.Persiana;

import java.util.ArrayList;
import java.util.List;

public class ControladorCasa {

    private final List<Lampada> lampadas = new ArrayList<>();
    private final List<Persiana> persianas = new ArrayList<>();
    private final List<ArCondicionado> arCondicionados = new ArrayList<>();

    public void addLampada(Lampada lampada) {
        lampadas.add(lampada);
    }

    public void addPersiana(Persiana persiana) {
        persianas.add(persiana);
    }

    public void addArCondicionado(ArCondicionado ac) {
        arCondicionados.add(ac);
    }

    public void ligarLampadas() {
        lampadas.forEach(Lampada::ligar);
    }

    public void desligarLampadas() {
        lampadas.forEach(Lampada::desligar);
    }

    public void abrirPersianas() {
        persianas.forEach(Persiana::abrir);
    }

    public void fecharPersianas() {
        persianas.forEach(Persiana::fechar);
    }

    public void ligarArCondicionados() {
        arCondicionados.forEach(ArCondicionado::ligar);
    }

    public void desligarArCondicionados() {
        arCondicionados.forEach(ArCondicionado::desligar);
    }

    public void aumentarTemperatura() {
        arCondicionados.forEach(ArCondicionado::aumentarTemperatura);
    }

    public void diminuirTemperatura() {
        arCondicionados.forEach(ArCondicionado::diminuirTemperatura);
    }

    public void definirTemperatura(int temperatura) {
        arCondicionados.forEach(ac -> ac.definirTemperatura(temperatura));
    }

    public void modoSono() {
        desligarArCondicionados();
        desligarLampadas();
        fecharPersianas();
    }

    public void modoTrabalho() {
        ligarLampadas();
        ligarArCondicionados();
        definirTemperatura(25);
        abrirPersianas();
    }

    public List<Lampada> getLampadas() {
        return lampadas;
    }

    public List<Persiana> getPersianas() {
        return persianas;
    }

    public List<ArCondicionado> getArCondicionados() {
        return arCondicionados;
    }
}
