package com.maishealth.maishealth.usuario.negocio;

import android.content.Context;
import android.content.SharedPreferences;

import com.maishealth.maishealth.infra.FormataData;
import com.maishealth.maishealth.usuario.dominio.Medico;
import com.maishealth.maishealth.usuario.dominio.Pessoa;
import com.maishealth.maishealth.usuario.dominio.Usuario;
import com.maishealth.maishealth.usuario.persistencia.MedicoDAO;
import com.maishealth.maishealth.usuario.persistencia.PessoaDAO;
import com.maishealth.maishealth.usuario.persistencia.UsuarioDAO;

import static com.maishealth.maishealth.infra.ConstanteSharedPreferences.ID_USER_PREFERENCES;
import static com.maishealth.maishealth.infra.ConstanteSharedPreferences.IS_MEDICO_PREFERENCES;
import static com.maishealth.maishealth.infra.ConstanteSharedPreferences.LOGIN_PREFERENCES;
import static com.maishealth.maishealth.infra.ConstanteSharedPreferences.PASSWORD_PREFERENCES;
import static com.maishealth.maishealth.infra.ConstanteSharedPreferences.TITLE_PREFERENCES;


public class Servicos {
    private UsuarioDAO usuarioDAO;
    private ServicosPessoa servicosPessoa;
    private ServicosUsuario servicosUsuario;
    private ServicosPaciente servicosPaciente;
    private ServicosMedico servicosMedico;
    private PessoaDAO pessoaDAO;
    private MedicoDAO medicoDAO;
    private SharedPreferences sharedPreferences;

    public Servicos(Context context) {
        sharedPreferences = context.getSharedPreferences(TITLE_PREFERENCES, context.MODE_PRIVATE);
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
        dataNasc = FormataData.americano(dataNasc);

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
            throw new Exception("CRM já cadastrado em "+estado);
        }
        try{
            long idUsuario = this.cadastrarPaciente(email, senha, nome, sexo, dataNasc, cpf, tipoSangue);
            servicosMedico.cadastrarMedico(crm, estado, especialidade, idUsuario);

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public void login(String email, String senha) throws Exception {
        Usuario usuario = usuarioDAO.getUsuarioByEmail(email);

        if(usuario == null){
            throw new Exception("E-mail não cadastrado");
        } else if(!senha.equals(usuario.getSenha())){
            throw new Exception("E-mail ou Senha incorretos");
        }
        Medico medico = medicoDAO.getMedicoByIdUsuario(usuario.getId());
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putLong(ID_USER_PREFERENCES, usuario.getId());
        editor.putString(LOGIN_PREFERENCES, usuario.getEmail());
        editor.putString(PASSWORD_PREFERENCES, usuario.getSenha());
        editor.putBoolean(IS_MEDICO_PREFERENCES, medico != null);

        editor.commit();
    }
}

