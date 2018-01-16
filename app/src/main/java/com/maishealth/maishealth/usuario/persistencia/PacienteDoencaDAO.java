package com.maishealth.maishealth.usuario.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.maishealth.maishealth.infra.DataBase;
import com.maishealth.maishealth.usuario.dominio.DoencaCronica;

import java.util.ArrayList;

public class PacienteDoencaDAO {
    private SQLiteDatabase liteDatabase;
    private DataBase dataBaseHelper;
    private DoencaCronicaDAO doencaCronicaDAO;

    public PacienteDoencaDAO(Context context) {
        dataBaseHelper = new DataBase(context);
        PacienteDAO pacienteDAO = new PacienteDAO(context);
        doencaCronicaDAO = new DoencaCronicaDAO(context);
    }

    public void inserirPacienteDoenca(long idPaciente, long idDoenca){
        liteDatabase = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        String tabela = DataBase.TABELA_PACIENTE_DOENCA;

        String colunaIdPaciente = DataBase.ID_EST_PACIENTE_PA_DO;
        values.put(colunaIdPaciente, idPaciente);

        String colunaIdDoenca = DataBase.ID_EST_DOENCA_PA_DO;
        values.put(colunaIdDoenca, idDoenca);

        liteDatabase.insert(tabela, null, values);

        liteDatabase.close();
    }

    public ArrayList<DoencaCronica> getDoencasByPaciente(long idPaciente){
        liteDatabase = dataBaseHelper.getReadableDatabase();
        ArrayList<DoencaCronica> listaDoencas = new ArrayList<>();

        String query = "SELECT * FROM " + DataBase.TABELA_PACIENTE_DOENCA +
                " WHERE " + DataBase.ID_EST_PACIENTE_PA_DO + " LIKE ?" +
                " ORDER BY " + DataBase.ID_EST_DOENCA_PA_DO + " DESC";

        String idString = Long.toString(idPaciente);
        String[] argumentos = {idString};

        Cursor cursor = liteDatabase.rawQuery(query, argumentos);

        DoencaCronica doencaCronica;

        String colunaIdDoenca = DataBase.ID_EST_DOENCA_PA_DO;
        int indexColunaIdDoenca = cursor.getColumnIndex(colunaIdDoenca);

        while (cursor.moveToNext()) {
            long idDoenca = cursor.getInt(indexColunaIdDoenca);
            doencaCronica = doencaCronicaDAO.getDoenca(idDoenca);
            listaDoencas.add(doencaCronica);
        }
        cursor.close();
        liteDatabase.close();

        return listaDoencas;
    }
}
