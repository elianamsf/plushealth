package com.maishealth.maishealth.usuario.negocio;

import android.content.Context;

import com.maishealth.maishealth.usuario.dominio.Consulta;
import com.maishealth.maishealth.usuario.dominio.EnumStatusConsulta;
import com.maishealth.maishealth.usuario.dominio.Medico;
import com.maishealth.maishealth.usuario.persistencia.ConsultaDAO;
import com.maishealth.maishealth.usuario.persistencia.MedicoDAO;

public class ServicosMedico {
    private MedicoDAO medicoDAO;
    private ConsultaDAO consultaDAO;

    public ServicosMedico(Context context) {
        medicoDAO = new MedicoDAO(context);
        consultaDAO = new ConsultaDAO(context);
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

}
