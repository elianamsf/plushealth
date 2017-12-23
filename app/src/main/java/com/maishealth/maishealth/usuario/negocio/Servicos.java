package com.maishealth.maishealth.usuario.negocio;

import android.content.Context;

import com.maishealth.maishealth.usuario.dominio.Medico;
import com.maishealth.maishealth.usuario.dominio.Pessoa;
import com.maishealth.maishealth.usuario.dominio.Usuario;
import com.maishealth.maishealth.usuario.persistencia.MedicoDAO;
import com.maishealth.maishealth.usuario.persistencia.PessoaDAO;
import com.maishealth.maishealth.usuario.persistencia.UsuarioDAO;


public class Servicos {
    private UsuarioDAO usuarioDAO;
    private ServicosPessoa servicosPessoa;
    private ServicosUsuario servicosUsuario;
    private ServicosPaciente servicosPaciente;
    private ServicosMedico servicosMedico;
    private PessoaDAO pessoaDAO;
    private MedicoDAO medicoDAO;

    public Servicos(Context context) {
        usuarioDAO = new UsuarioDAO(context);
        pessoaDAO = new PessoaDAO(context);
        medicoDAO = new MedicoDAO(context);
        servicosPessoa = new ServicosPessoa(context);
        servicosUsuario = new ServicosUsuario(context);
        servicosPaciente = new ServicosPaciente(context);
        servicosMedico = new ServicosMedico(context);

    }

    public long cadastrarPaciente(String email, String senha, String nome, String sexo, String dataNasc, String cpf, String tipoSangue) throws Exception {
        Usuario verificarEmail = usuarioDAO.getUsuarioByEmail(email);
        Pessoa verificarCpf = pessoaDAO.getPessoaByCpf(cpf);

        if(verificarEmail != null) {
            throw new Exception("Email já cadastrado");
        }
        if (verificarCpf != null) {
                throw new Exception("CPF já cadastrado");
        }else{

            long idUsuario = servicosUsuario.cadastrarUsuario(email, senha);
            servicosPessoa.cadastrarPessoa(nome, sexo, dataNasc, cpf, idUsuario);
            servicosPaciente.cadastrarPaciente(idUsuario, tipoSangue);

            return idUsuario;
        }
    }

    public void cadastrarMedico(String email, String senha, String nome, String sexo, String dataNasc, String cpf, String tipoSangue, String crm, String estado, String especialidade) throws Exception {
        Medico verificarRegiaoCrm = medicoDAO.getMedicoByRegiaoCrm(estado, crm);

        if(verificarRegiaoCrm != null) {
            throw new Exception(" Estado/CRM já cadastrado");
        }
        try{
            long idUsuario = this.cadastrarPaciente(email, senha, nome, sexo, dataNasc, cpf, tipoSangue);
            servicosMedico.cadastrarMedico(crm, estado, especialidade, idUsuario);

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}

