package com.maishealth.maishealth.usuario.negocio;

import android.text.TextUtils;
import android.util.Patterns;

import com.maishealth.maishealth.infra.FormataData;
import com.maishealth.maishealth.usuario.gui.SignUpActivity;

import java.util.regex.Pattern;

/**
 * Created by lucas on 16/12/17.
 */

public class ValidaCadastro {
    private int TAMANHO_SENHA = 6;
    private int TAMANHO_CPF = 14;
    private int TAMANHO_CRM = 6;
    private int TAMANHO_DATA = 10;

    public boolean isCampoVazio(String texto){
        return (texto.trim().isEmpty() || TextUtils.isEmpty(texto));
    }

    public boolean isEmail(String texto){
        return (Patterns.EMAIL_ADDRESS.matcher(texto).matches());
    }

    public boolean isSenhaValida(String texto){
        if (isCampoVazio(texto)){
            return false;
        }else{
            return texto.length()>=TAMANHO_SENHA;
        }
    }

    public boolean isCpfValida(String texto){
        if (isCampoVazio(texto)){
            return false;
        }else{
            return texto.length()==TAMANHO_CPF;
        }
    }

    public  boolean isCrmValido (String texto){
        if (isCampoVazio(texto)){
            return false;
        }else{
            return texto.length()==TAMANHO_CRM;
        }

    }

    public boolean isDataNascimento (String data){

        return (FormataData.dataExiste(data) && FormataData.dataMenorOuIgualQueAtual(data) && data.length() == TAMANHO_DATA);

    }

}
