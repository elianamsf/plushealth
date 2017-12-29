package com.maishealth.maishealth.usuario.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.maishealth.maishealth.infra.DataBase;
import com.maishealth.maishealth.usuario.dominio.Medico;

import java.util.ArrayList;

@SuppressWarnings("FieldCanBeLocal")
public class MedicoPostoDAO {
    private SQLiteDatabase liteDatabase;
    private DataBase dataBaseHelper;
    private MedicoDAO medicoDAO;
    private PostoDAO postoDAO;

    public MedicoPostoDAO(Context context) {
        dataBaseHelper = new DataBase(context);
        medicoDAO = new MedicoDAO(context);
        postoDAO = new PostoDAO(context);
    }

    public void inserirMedicoPosto(long idMedico, long idPosto){
        liteDatabase = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        String tabela = DataBase.TABELA_MEDICO_POSTO;

        String colunaIdMedico = DataBase.ID_EST_MEDICO_ME_POS;
        values.put(colunaIdMedico, idMedico);

        String colunaIdPosto = DataBase.ID_EST_POSTO_ME_POS;
        values.put(colunaIdPosto, idPosto);

        liteDatabase.insert(tabela, null, values);

        liteDatabase.close();
    }

    public ArrayList<Medico> getMedicosByPosto(long idPosto){
        liteDatabase = dataBaseHelper.getReadableDatabase();
        ArrayList<Medico> listaMedicos = new ArrayList<>();

        String query = "SELECT * FROM " + DataBase.TABELA_MEDICO_POSTO +
                " WHERE " + DataBase.ID_EST_POSTO_ME_POS + " LIKE ?" +
                " ORDER BY " + DataBase.ID_EST_MEDICO_ME_POS + " DESC";

        String idString = Long.toString(idPosto);
        String[] argumentos = {idString};

        Cursor cursor = liteDatabase.rawQuery(query, argumentos);

        Medico medico;

        String colunaIdMedico = DataBase.ID_EST_MEDICO_ME_POS;
        int indexColunaIdMedico = cursor.getColumnIndex(colunaIdMedico);

        while (cursor.moveToNext()) {
            long idMedicamento = cursor.getInt(indexColunaIdMedico);
            medico = medicoDAO.getMedico(idMedicamento);
            listaMedicos.add(medico);
        }
        cursor.close();
        liteDatabase.close();

        return listaMedicos;
    }
}
