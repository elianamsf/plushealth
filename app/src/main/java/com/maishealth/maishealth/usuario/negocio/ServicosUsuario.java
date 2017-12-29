package com.maishealth.maishealth.usuario.negocio;

import android.content.Context;

import com.maishealth.maishealth.usuario.dominio.Usuario;
import com.maishealth.maishealth.usuario.persistencia.UsuarioDAO;

public class ServicosUsuario {
    private UsuarioDAO usuarioDAO;
    public ServicosUsuario(Context context) {
        usuarioDAO = new UsuarioDAO(context);
    }

    private long cadastrarUsuario(Usuario usuario){
        return usuarioDAO.inserirUsuário(usuario);
    }

    public long cadastrarUsuario(String email, String senha) {
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setSenha(senha);

        return cadastrarUsuario(usuario);
    }

}
