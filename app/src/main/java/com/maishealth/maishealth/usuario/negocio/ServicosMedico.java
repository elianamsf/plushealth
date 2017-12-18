package com.maishealth.maishealth.usuario.negocio;

import android.content.Context;

import com.maishealth.maishealth.usuario.dominio.Medico;
import com.maishealth.maishealth.usuario.persistencia.MedicoDAO;

public class ServicosMedico {
    private MedicoDAO medicoDAO;

    public ServicosMedico(Context context) {
        medicoDAO = new MedicoDAO(context);
    }

    public long cadastrarMedico(Medico medico){
        long idMedico = medicoDAO.inserirMedico(medico);

        return idMedico;
    }
}
