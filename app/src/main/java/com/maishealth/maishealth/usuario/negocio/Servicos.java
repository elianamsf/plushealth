package com.maishealth.maishealth.usuario.negocio;

import android.content.Context;

import com.maishealth.maishealth.usuario.dominio.Usuario;
import com.maishealth.maishealth.usuario.persistencia.UsuarioDAO;


public class Servicos {
    private UsuarioDAO usuarioDAO;
    private ServicosPessoa servicosPessoa;
    private ServicosUsuario servicosUsuario;
    private ServicosPaciente servicosPaciente;
    private ServicosMedico servicosMedico;

    public Servicos(Context context) {
        usuarioDAO = new UsuarioDAO(context);
        servicosPessoa = new ServicosPessoa(context);
        servicosUsuario = new ServicosUsuario(context);
        servicosPaciente = new ServicosPaciente(context);
        servicosMedico = new ServicosMedico(context);
    }

    public void cadastrarPaciente(String email, String senha, String nome, String sexo, String dataNasc, String cpf) throws Exception {
        Usuario verificarEmail = usuarioDAO.getUsuarioByEmail(email);
        ///Usuario verificarCPF = usuarioDAO.getSenhaByEmail(email);

        if(verificarEmail != null){
            throw new Exception("Email já cadastrado");
        } else {

            long idUsuario = servicosUsuario.cadastrarUsuario(email, senha);
            servicosPessoa.cadastrarPessoa(nome, sexo, dataNasc, cpf, idUsuario);
            servicosPaciente.cadastrarPaciente(idUsuario);
        }
    }

    public void cadastrarMedico(String email, String senha, String nome, String sexo, String dataNasc, String cpf, String crm, String estado, String especialidade) throws Exception {
        Usuario verificarEmail = usuarioDAO.getUsuarioByEmail(email);

        if(verificarEmail != null){
            throw new Exception("Email já cadastrado");
        } else {
            long idUsuario = servicosUsuario.cadastrarUsuario(email, senha);
            servicosPessoa.cadastrarPessoa(nome, sexo, dataNasc, cpf, idUsuario);
            servicosPaciente.cadastrarPaciente(idUsuario);
            servicosMedico.cadastrarMedico(crm, estado, especialidade, idUsuario);
        }
    }
}

