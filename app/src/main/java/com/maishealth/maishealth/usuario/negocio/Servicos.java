package com.maishealth.maishealth.usuario.negocio;

import android.content.Context;

import com.maishealth.maishealth.usuario.dominio.Pessoa;
import com.maishealth.maishealth.usuario.dominio.Usuario;
import com.maishealth.maishealth.usuario.persistencia.UsuarioDAO;


public class Servicos {
    private UsuarioDAO usuarioDAO;
    private ServicosPessoa servicosPessoa;
    private ServicosUsuario servicosUsuario;

    public Servicos(Context context) {
        usuarioDAO = new UsuarioDAO(context);
        servicosPessoa = new ServicosPessoa(context);
        servicosUsuario = new ServicosUsuario(context);
    }

    public void cadastrarPaciente(String email, String senha, String nome, String sexo, String dataNasc, String cpf) throws Exception {
        Usuario verificarEmail = usuarioDAO.getUsuarioByEmail(email);

        if(verificarEmail != null){
            throw new Exception("Email já cadastrado");
        } else {
            Pessoa pessoa = new Pessoa();
            pessoa.setNome(nome);
            pessoa.setSexo(sexo);
            pessoa.setDataNasc(dataNasc);
            pessoa.setCpf(cpf);

            long idPessoa = servicosPessoa.cadastrarPessoa(pessoa);

            Usuario usuario = new Usuario();
            usuario.setEmail(email);
            usuario.setSenha(senha);
            usuario.setIdPessoa(idPessoa);

            long idUsuario = servicosUsuario.cadastrarUsuario(usuario);

        }
    }
}
