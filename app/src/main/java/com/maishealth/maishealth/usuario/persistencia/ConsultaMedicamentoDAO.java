package com.maishealth.maishealth.usuario.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.maishealth.maishealth.infra.DataBase;
import com.maishealth.maishealth.usuario.dominio.Medicamento;

import java.util.ArrayList;

public class ConsultaMedicamentoDAO {
    private SQLiteDatabase liteDatabase;
    private DataBase dataBaseHelper;
    private ConsultaDAO consultaDAO;
    private MedicamentoDAO medicamentoDAO;

    public ConsultaMedicamentoDAO(Context context) {
        dataBaseHelper = new DataBase(context);
        consultaDAO = new ConsultaDAO(context);
        medicamentoDAO = new MedicamentoDAO(context);

    }

    public void inserirCoseultaMedicamento(long idConsulta, long idMedicamento){
        liteDatabase = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        String tabela = DataBase.TABELA_CONSULTA_MEDICAMENTO;

        String colunaIdConsulta = DataBase.ID_EST_CONSULTA_CON_MEM;
        values.put(colunaIdConsulta, idConsulta);

        String colunaIdMedicamento = DataBase.ID_EST_MEDICAMENTO_CON_MEM;
        values.put(colunaIdMedicamento, idMedicamento);

        liteDatabase.insert(tabela, null, values);

        liteDatabase.close();
    }

    public ArrayList<Medicamento> getMedicamentosByConsulta(long idConsulta){
        liteDatabase = dataBaseHelper.getReadableDatabase();
        ArrayList<Medicamento> listaMedicamentos = new ArrayList<>();

        String query = "SELECT * FROM " + DataBase.TABELA_CONSULTA_MEDICAMENTO +
                " WHERE " + DataBase.ID_EST_CONSULTA_CON_MEM + " LIKE ?" +
                " ORDER BY " + DataBase.ID_EST_MEDICAMENTO_CON_MEM + " DESC";

        String idString = Long.toString(idConsulta);
        String[] argumentos = {idString};

        Cursor cursor = liteDatabase.rawQuery(query, argumentos);

        Medicamento medicamento;

        String colunaIdMedicamento = DataBase.ID_EST_MEDICAMENTO_CON_MEM;
        int indexColunaIdMedicamento = cursor.getColumnIndex(colunaIdMedicamento);

        while (cursor.moveToNext()) {
            long idMedicamento = cursor.getInt(indexColunaIdMedicamento);
            medicamento = medicamentoDAO.getMedicamento(idMedicamento);
            listaMedicamentos.add(medicamento);
        }
        cursor.close();
        liteDatabase.close();

        return listaMedicamentos;
    }
}
