package com.maishealth.maishealth.usuario.negocio;

import android.text.TextUtils;
import android.util.Patterns;

import com.maishealth.maishealth.usuario.dominio.Usuario;

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
    //public void verificarConta(String email, String senha) throws Exception {
        //Usuario verificarEmail = usuarioDAO.getUsuarioByEmail(email);
        //Usuario verificarSenha = usuarioDAO.getSenhaByEmail(email);


        //if(verificarEmail != null){
            //throw new Exception("Email já cadastrado");
    //ADPTAR AQ BA
}
