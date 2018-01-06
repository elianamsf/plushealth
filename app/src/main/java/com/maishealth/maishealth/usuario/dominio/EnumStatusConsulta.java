package com.maishealth.maishealth.usuario.dominio;

/**
 * Created by Eliana-DEV on 06/01/2018.
 */

public enum EnumStatusConsulta {

    MARCADA("Marcada"),
    CANCELADA ("Cancelada"),
    REALIZADA("Realizada"),
    REAGENDADA ("Reagendada");

    private final String valor;

    EnumStatusConsulta(String valor){
        this.valor = valor;
    }

    public String getValor(){
        return valor;
    }

    public static String[] enumStatusConsulta(){
        EnumStatusConsulta[] listaStatusConsulta = EnumStatusConsulta.values();
        String[] lista = new String[listaStatusConsulta.length];
        for (int i =0; i<listaStatusConsulta.length; i++){
            lista[i] = listaStatusConsulta[i].getValor();
        }
        return lista;
    }


}
