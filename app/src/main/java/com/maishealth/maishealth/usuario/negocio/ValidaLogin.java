package com.maishealth.maishealth.usuario.negocio;

import android.text.TextUtils;
import android.util.Patterns;

public class ValidaLogin {
    private int TAMANHO_SENHA = 6;

    public boolean isCampoVazio(String texto) {
        return (texto.trim().isEmpty() || TextUtils.isEmpty(texto));
    }

    public boolean isEmail(String texto) {
        return (Patterns.EMAIL_ADDRESS.matcher(texto).matches());
    }

    public boolean isSenhaValida(String texto) {
        if (isCampoVazio(texto)) {
            return false;
        } else {
            return texto.length() >= TAMANHO_SENHA;
        }
    }
    //ADPTAR AQ BA
}
