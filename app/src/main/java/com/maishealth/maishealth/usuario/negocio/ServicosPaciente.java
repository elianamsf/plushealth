package com.maishealth.maishealth.usuario.negocio;

import android.content.Context;

import com.maishealth.maishealth.usuario.dominio.Consulta;
import com.maishealth.maishealth.usuario.dominio.EnumStatusConsulta;
import com.maishealth.maishealth.usuario.dominio.Paciente;
import com.maishealth.maishealth.usuario.dominio.Sintoma;
import com.maishealth.maishealth.usuario.persistencia.ConsultaDAO;
import com.maishealth.maishealth.usuario.persistencia.PacienteDAO;
import com.maishealth.maishealth.usuario.persistencia.SintomaDAO;

public class ServicosPaciente {
    private PacienteDAO pacienteDAO;
    private ConsultaDAO consultaDAO;
    private SintomaDAO sintomaDAO;

    public ServicosPaciente(Context context) {
        pacienteDAO = new PacienteDAO(context);
        consultaDAO = new ConsultaDAO(context);
        sintomaDAO = new SintomaDAO(context);
    }

    private long cadastrarPaciente(Paciente paciente){ return pacienteDAO.inserirPaciente(paciente);
    }

    public long cadastrarPaciente(long idUsuario, String tipoSangue) {
        Paciente paciente = new Paciente();
        paciente.setIdUsuario(idUsuario);
        paciente.setTipoSangue(tipoSangue);

        return cadastrarPaciente(paciente);
    }

    private long marcarConsulta(Consulta consulta){ return consultaDAO.atualizarConsulta(consulta);
    }

    public long marcarConsulta(Paciente paciente, String data){
        Consulta consulta = consultaDAO.getConsultaByData(data);
        consulta.setIdPaciente(paciente.getId());
        consulta.setStatus(EnumStatusConsulta.MARCADA.toString());

        return marcarConsulta(consulta);
    }

    private long inserirSintoma(Sintoma sintoma){ return sintomaDAO.inserirSintoma(sintoma);    }

    public long inserirSintoma(String nomeSintoma){
        Sintoma sintoma = new Sintoma();
        sintoma.setSintoma(nomeSintoma);

        return inserirSintoma(sintoma);
    }
}
