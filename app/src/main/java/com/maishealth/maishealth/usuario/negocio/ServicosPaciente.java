package com.maishealth.maishealth.usuario.negocio;

import android.content.Context;

import com.maishealth.maishealth.usuario.dominio.Paciente;
import com.maishealth.maishealth.usuario.persistencia.PacienteDAO;

public class ServicosPaciente {
    private PacienteDAO pacienteDAO;

    public ServicosPaciente(Context context) {
        pacienteDAO = new PacienteDAO(context);
    }

    public long cadastrarPaciente(Paciente paciente){
        long idPaciente = pacienteDAO.inserirPaciente(paciente);

        return idPaciente;
    }

    public long cadastrarPaciente(long idUsuario) {
        Paciente paciente = new Paciente();
        paciente.setIdUsuario(idUsuario);

        return cadastrarPaciente(paciente);
    }
}
