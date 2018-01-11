package com.maishealth.maishealth.usuario.dominio;

/**
 * Created by Wenderson on 11/01/2018.
 */

public enum EnumEspecialidade {
    CARDIOLOGIA("Cardiologia"),CLINICOGERAL("Clinico Geral"),DERMATOLOGIA("Dermatologia"),GASTROENTEROLOGIA("Gastroenterologia"),
    GENICOLOGIA("Genicologia"),HEMATOLOGIA("Hematologia"),INFECTOLOGIA("Infectologia"),NEUROLOGIA("Neurologia"),
    OFTALMOLOGISTA("Oftalmologia"),ORTOPEDIA("Ortopedia"),PNEUMOLOGIA("Pneumologia"),PSIQUIATRIA("Psiquiatria"),
    REUMATOLOGIA("Reumatologia"),UROLOGIA("Urologia");

    private final String valor;

    EnumEspecialidade(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public static String[] enumEspecialidadeLista(){
        EnumEspecialidade[] listaEspecialidade = EnumEspecialidade.values();
        String[] lista = new String[listaEspecialidade.length];
        for (int i =0; i<listaEspecialidade.length;i++){
            lista[i] = listaEspecialidade[i].getValor();
        }
        return  lista;
    }
}
