package com.maishealth.maishealth.usuario.negocio;

import android.content.Context;
import android.content.SharedPreferences;

import com.maishealth.maishealth.usuario.dominio.Consulta;
import com.maishealth.maishealth.usuario.dominio.EnumStatusConsulta;
import com.maishealth.maishealth.usuario.dominio.Paciente;
import com.maishealth.maishealth.usuario.dominio.Sintoma;
import com.maishealth.maishealth.usuario.persistencia.ConsultaDAO;
import com.maishealth.maishealth.usuario.persistencia.PacienteDAO;
import com.maishealth.maishealth.usuario.persistencia.SintomaDAO;

import static com.maishealth.maishealth.infra.ConstanteSharedPreferences.ID_PACIENTE_PREFERENCES;
import static com.maishealth.maishealth.infra.ConstanteSharedPreferences.TITLE_PREFERENCES;

public class ServicosPaciente {
    private PacienteDAO pacienteDAO;
    private ConsultaDAO consultaDAO;
    private SintomaDAO sintomaDAO;
    private SharedPreferences sharedPreferences;

    public ServicosPaciente(Context context) {
        sharedPreferences = context.getSharedPreferences(TITLE_PREFERENCES, Context.MODE_PRIVATE);
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

    public long marcarConsulta( String data){
        long idPaciente = 0;
        Paciente paciente = pacienteDAO.getPaciente(sharedPreferences.getLong(ID_PACIENTE_PREFERENCES,idPaciente));

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
