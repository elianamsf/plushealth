package com.maishealth.maishealth.usuario.negocio;


import android.content.Context;

import com.maishealth.maishealth.usuario.dominio.Pessoa;
import com.maishealth.maishealth.usuario.persistencia.PessoaDAO;

public class ServicosPessoa {
    private PessoaDAO pessoaDAO;

    public ServicosPessoa(Context context) {
        pessoaDAO = new PessoaDAO(context);
    }

    public long cadastrarPessoa(Pessoa pessoa){
        long idPessoa = pessoaDAO.inserirPessoa(pessoa);

        return idPessoa;
    }
}
