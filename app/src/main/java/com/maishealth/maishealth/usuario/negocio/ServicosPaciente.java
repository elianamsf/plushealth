package com.maishealth.maishealth.usuario.negocio;

import android.content.Context;

import com.maishealth.maishealth.usuario.dominio.Paciente;
import com.maishealth.maishealth.usuario.persistencia.PacienteDAO;

public class ServicosPaciente {
    private PacienteDAO pacienteDAO;

    public ServicosPaciente(Context context) {
        pacienteDAO = new PacienteDAO(context);
    }

    private long cadastrarPaciente(Paciente paciente){
        return pacienteDAO.inserirPaciente(paciente);
    }

    public long cadastrarPaciente(long idUsuario, String tipoSangue) {
        Paciente paciente = new Paciente();
        paciente.setIdUsuario(idUsuario);
        paciente.setTipoSangue(tipoSangue);

        return cadastrarPaciente(paciente);
    }
}
