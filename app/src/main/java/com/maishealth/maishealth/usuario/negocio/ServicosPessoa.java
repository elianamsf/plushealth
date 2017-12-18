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

    public long cadastrarPessoa(String nome, String sexo, String dataNasc, String cpf, long idUsuario) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(nome);
        pessoa.setSexo(sexo);
        pessoa.setDataNasc(dataNasc);
        pessoa.setCpf(cpf);
        pessoa.setIdUsuario(idUsuario);

        return cadastrarPessoa(pessoa);
    }
}
