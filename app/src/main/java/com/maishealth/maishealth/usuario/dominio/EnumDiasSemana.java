package com.maishealth.maishealth.usuario.dominio;

/**
 * Created by Carol and Wenderson on 16/01/2018.
 */

public enum EnumDiasSemana {
    SEGUNDA("Segunda-Feira"),TERCA("Terca-Feira"),QUARTA("Quarta-Feira"),QUINTA("Quinta-Feira"),SEXTA("Sexta-Feira");

    private final String valor;

    EnumDiasSemana(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public static String[] enumDiasSemanaLista(){
        EnumDiasSemana[] listaDiasSemana = EnumDiasSemana.values();
        String[] lista = new String[listaDiasSemana.length];
        for (int i =0; i<listaDiasSemana.length;i++){
            lista[i] = listaDiasSemana[i].getValor();
        }
        return  lista;
    }
}
