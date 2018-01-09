package com.maishealth.maishealth.usuario.negocio;

import android.content.Context;

import com.maishealth.maishealth.usuario.dominio.Consulta;
import com.maishealth.maishealth.usuario.dominio.EnumStatusConsulta;
import com.maishealth.maishealth.usuario.dominio.Medicamento;
import com.maishealth.maishealth.usuario.dominio.Medico;
import com.maishealth.maishealth.usuario.persistencia.ConsultaDAO;
import com.maishealth.maishealth.usuario.persistencia.MedicamentoDAO;
import com.maishealth.maishealth.usuario.persistencia.MedicoDAO;

public class ServicosMedico {
    private MedicoDAO medicoDAO;
    private ConsultaDAO consultaDAO;
    private MedicamentoDAO medicamentoDAO;
    Medico medico = new Medico();
    Consulta consulta = new Consulta();
    Medicamento medicamento = new Medicamento();

    public ServicosMedico(Context context) {
        medicoDAO = new MedicoDAO(context);
        consultaDAO = new ConsultaDAO(context);
        medicamentoDAO = new MedicamentoDAO(context);}

    private long cadastrarMedico(Medico medico){
        return medicoDAO.inserirMedico(medico);
    }

    public long cadastrarMedico(String crm, String estado, String especialidade, long idUsuario) {
        medico.setCrm(crm);
        medico.setEstado(estado);
        medico.setEspecialidade(especialidade);
        medico.setIdUsuario(idUsuario);

        return cadastrarMedico(medico);
    }

    public long cadastrarConsulta(Consulta consulta) { return consultaDAO.inserirConsulta(consulta); }

    public long cadastrarConsulta (String data, String descricao){
        consulta.setStatus(EnumStatusConsulta.DISPONIVEL.toString());
        consulta.setIdMedico(medico.getIdUsuario());
        consulta.setData(data);
        consulta.setDescricao(descricao);

        return cadastrarConsulta(consulta);
    }

    public long cadastrarMedicamento (Medicamento medicamento) {return medicamentoDAO.inserirMedicamento(medicamento);}

    public long cadastrarMedicamento (String nomeMedicamento){
        medicamento.setNome(nomeMedicamento);
        return cadastrarMedicamento(medicamento);
    }

}
