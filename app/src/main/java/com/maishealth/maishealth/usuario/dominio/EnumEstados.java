package com.maishealth.maishealth.usuario.dominio;

public enum EnumEstados {
    acre("AC"), Alagoas("AL");

    private String valor;

    EnumEstados(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
