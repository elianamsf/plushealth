package com.maishealth.maishealth.usuario.dominio;

public enum EnumTipoSangue {
    NAOSABE("Não sei"), APOSITIVO("A+"), ANEGATIVO("A-"), BPOSITIVO("B+"), BNEGATIVO("B-"),
    ABPOSITIVO("AB+"), ABNEGATIVO("AB-"), OPOSITIVO("O+"), ONEGATIVO("O-");

    private String valor;

    EnumTipoSangue(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public static String[] enumTipoSangueLista(){
        EnumTipoSangue[] listaSangue = EnumTipoSangue.values();
        String[] lista = new String[listaSangue.length];
        for (int i =0; i<listaSangue.length;i++){
            lista[i] = listaSangue[i].getValor();
        }
        return  lista;
    }
}
