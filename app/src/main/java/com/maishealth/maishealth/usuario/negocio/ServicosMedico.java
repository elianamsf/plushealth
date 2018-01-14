package com.maishealth.maishealth.usuario.negocio;

import android.content.Context;
import android.content.SharedPreferences;

import com.maishealth.maishealth.usuario.dominio.Consulta;
import com.maishealth.maishealth.usuario.dominio.EnumStatusConsulta;
import com.maishealth.maishealth.usuario.dominio.Medicamento;
import com.maishealth.maishealth.usuario.dominio.Medico;
import com.maishealth.maishealth.usuario.persistencia.ConsultaDAO;
import com.maishealth.maishealth.usuario.persistencia.MedicamentoDAO;
import com.maishealth.maishealth.usuario.persistencia.MedicoDAO;

import static com.maishealth.maishealth.infra.ConstanteSharedPreferences.ID_MEDICO_PREFERENCES;
import static com.maishealth.maishealth.infra.ConstanteSharedPreferences.TITLE_PREFERENCES;

public class ServicosMedico {
    private MedicoDAO medicoDAO;
    private ConsultaDAO consultaDAO;
    private MedicamentoDAO medicamentoDAO;
    private SharedPreferences sharedPreferences;


    public ServicosMedico(Context context) {
        sharedPreferences = context.getSharedPreferences(TITLE_PREFERENCES, Context.MODE_PRIVATE);
        medicoDAO = new MedicoDAO(context);
        consultaDAO = new ConsultaDAO(context);
        medicamentoDAO = new MedicamentoDAO(context);
    }


    private long cadastrarMedico(Medico medico){
        return medicoDAO.inserirMedico(medico);
    }

    public long cadastrarMedico(String crm, String estado, String especialidade, long idUsuario) {
        Medico medico = new Medico();
        medico.setCrm(crm);
        medico.setEstado(estado);
        medico.setEspecialidade(especialidade);
        medico.setIdUsuario(idUsuario);

        return cadastrarMedico(medico);
    }


    private long criarConsulta (Consulta consulta){return consultaDAO.inserirConsulta(consulta); }
    public long criarConsulta (Medico medico, String data){
        Consulta consulta = new Consulta();
        consulta.setIdMedico(medico.getId());
        consulta.setData(data);
        consulta.setStatus(EnumStatusConsulta.DISPONIVEL.toString());

        return criarConsulta(consulta);
    }

    private long criarMedicamento(Medicamento medicamento) {return medicamentoDAO.inserirMedicamento(medicamento);}

    public long criarMedicamento (String nomeMedicamento){
        Medicamento medicamento = new Medicamento();
        medicamento.setNome(nomeMedicamento);

        return criarMedicamento(medicamento);
    }

    public void registrarConsultas(String data, int qtdVagas){
        long idMedico = 0;
        Medico medico = medicoDAO.getMedico(sharedPreferences.getLong(ID_MEDICO_PREFERENCES,idMedico));
        int contador;
        contador = 1;
        while (contador <= qtdVagas){ criarConsulta(medico, data); contador++; }

    }
}
