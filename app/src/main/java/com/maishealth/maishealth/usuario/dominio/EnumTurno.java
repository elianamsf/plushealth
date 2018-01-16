package com.maishealth.maishealth.usuario.dominio;

/**
 * Created by carol and wenderson on 16/01/2018.
 */

public enum EnumTurno {
    MANHA("Manhã"), TARDE("Tarde");

    private final String valor;

    EnumTurno(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public static String[] enumTurno(){
        EnumTurno[] listaTurno = EnumTurno.values();
        String[] lista = new String[listaTurno.length];
        for (int i =0; i<listaTurno.length;i++){
            lista[i] = listaTurno[i].getValor();
        }
        return  lista;
    }
}
