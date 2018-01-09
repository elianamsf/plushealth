package com.maishealth.maishealth.usuario.negocio;

import android.content.Context;

import com.maishealth.maishealth.usuario.dominio.Consulta;
import com.maishealth.maishealth.usuario.dominio.EnumStatusConsulta;
import com.maishealth.maishealth.usuario.dominio.Paciente;
import com.maishealth.maishealth.usuario.persistencia.ConsultaDAO;
import com.maishealth.maishealth.usuario.persistencia.PacienteDAO;

public class ServicosPaciente {
    private PacienteDAO pacienteDAO;
    private ConsultaDAO consultaDAO;
    Paciente paciente = new Paciente();
    Consulta consulta = new Consulta();
    Long busca = Long.parseLong(null);


    public ServicosPaciente(Context context) {
        pacienteDAO = new PacienteDAO(context);
        consultaDAO = new ConsultaDAO(context);
    }

    private long cadastrarPaciente(Paciente paciente){
        return pacienteDAO.inserirPaciente(paciente);
    }

    public long cadastrarPaciente(long idUsuario, String tipoSangue) {
        paciente.setIdUsuario(idUsuario);
        paciente.setTipoSangue(tipoSangue);

        return cadastrarPaciente(paciente);
    }
    public long marcarConsulta(Consulta consulta) {return consultaDAO.atualizarConsulta(consulta);}

    public long marcarConsulta(String data){
        consulta.setStatus(EnumStatusConsulta.MARCADA.toString());
        consulta.setIdPaciente(paciente.getIdUsuario());

        return marcarConsulta(consulta);
    }
}
