package com.maishealth.maishealth.usuario.negocio;


import android.content.Context;

import com.maishealth.maishealth.usuario.dominio.Consulta;
import com.maishealth.maishealth.usuario.persistencia.ConsultaDAO;
import com.maishealth.maishealth.usuario.dominio.Sintoma;
import com.maishealth.maishealth.usuario.persistencia.SintomaDAO;
import com.maishealth.maishealth.usuario.persistencia.ConsultaSintomaDAO;

import java.util.ArrayList;

public class ServicosConsulta {

    private ConsultaDAO consultaDAO;
    private SintomaDAO sintomaDAO;
    private ConsultaSintomaDAO consultaSintomaDAO;

    public ServicosConsulta(Context context) {
        consultaDAO = new ConsultaDAO(context);
        sintomaDAO = new SintomaDAO(context);
        consultaSintomaDAO = new ConsultaSintomaDAO(context);
    }

    private void cadastrarConsulta (Consulta consulta){
        consultaDAO.inserirConsulta(consulta);
    }

    public void cadastrarConsulta (long idConsulta){
        Consulta consulta = new Consulta();
        consulta.setId(idConsulta);

        cadastrarConsulta(consulta);
    }

    private void cadastrarSintoma (Sintoma sintoma){
        sintomaDAO.InserirSintoma(sintoma);
    }

    public void cadastrarSintoma (String nomeSintoma){
        Sintoma sintoma = new Sintoma();
        sintoma.setSintoma(nomeSintoma);

        cadastrarSintoma(sintoma);
    }

    public ArrayList<Sintoma> searchSintomaByConsulta(long idConsulta){
        return consultaSintomaDAO.getSintomasByConsulta(idConsulta);
    }

    public void cadastarConstultaSintoma (long idConsultaComSintoma, String sintoma){
        consultaSintomaDAO.inserirConsultaSintoma(idConsultaComSintoma, sintoma);
        cadastrarConsulta(idConsultaComSintoma);
        cadastrarSintoma(sintoma);

    }

}
