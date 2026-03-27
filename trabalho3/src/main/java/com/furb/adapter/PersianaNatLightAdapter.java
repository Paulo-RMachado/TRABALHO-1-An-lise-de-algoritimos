package com.furb.adapter;

import br.furb.analise.algoritmos.PersianaNatLight;

public class PersianaNatLightAdapter implements Persiana {

    private final PersianaNatLight persiana;

    public PersianaNatLightAdapter(PersianaNatLight persiana) {
        this.persiana = persiana;
    }

    @Override
    public void abrir() {
        try {
            if (!persiana.estaPalhetaAberta()) {
                persiana.abrirPalheta();
            }
            persiana.subirPalheta();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao abrir persiana NatLight", e);
        }
    }

    @Override
    public void fechar() {
        try {
            if (persiana.estaPalhetaErguida()) {
                persiana.descerPalheta();
            }
            if (persiana.estaPalhetaAberta()) {
                persiana.fecharPalheta();
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao fechar persiana NatLight", e);
        }
    }

    @Override
    public boolean estaAberta() {
        return persiana.estaPalhetaErguida();
    }
}
