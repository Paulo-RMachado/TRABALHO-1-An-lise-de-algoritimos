package com.furb.adapter;

import br.furb.analise.algoritmos.PersianaSolarius;

public class PersianaSolariusAdapter implements Persiana {

    private final PersianaSolarius persiana;

    public PersianaSolariusAdapter(PersianaSolarius persiana) {
        this.persiana = persiana;
    }

    @Override
    public void abrir() {
        persiana.subirPersiana();
    }

    @Override
    public void fechar() {
        persiana.descerPersiana();
    }

    @Override
    public boolean estaAberta() {
        return persiana.estaAberta();
    }
}
