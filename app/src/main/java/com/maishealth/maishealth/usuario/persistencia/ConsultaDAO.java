package com.maishealth.maishealth.usuario.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.maishealth.maishealth.infra.DataBase;
import com.maishealth.maishealth.usuario.dominio.Consulta;

public class ConsultaDAO {
    private SQLiteDatabase liteDatabase;
    private DataBase dataBaseHelper;

    public ConsultaDAO(Context context) {
        dataBaseHelper = new DataBase(context);
    }

    public long inserirConsulta(Consulta consulta){
        liteDatabase = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        String tabela = DataBase.TABELA_CONSULTA;

        String colunaData = DataBase.CONSULTA_DATA;
        String data = consulta.getData();
        values.put(colunaData, data);

        String colunaDesc = DataBase.CONSULTA_DESCRICAO;
        String descricao = consulta.getDescricao();
        values.put(colunaDesc, descricao);

        String colunaIdPaciente = DataBase.ID_EST_PACIENTE_CON;
        long idPaciente = consulta.getIdPaciente();
        values.put(colunaIdPaciente, idPaciente);

        String colunaIdMedico = DataBase.ID_EST_MEDICO_CON;
        long idMedico = consulta.getIdMedico();
        values.put(colunaIdMedico, idMedico);

        String colunaStatus = DataBase.CONSULTA_STATUS;
        String status = consulta.getStatus();
        values.put(colunaStatus, status);

        long id = liteDatabase.insert(tabela, null, values);

        liteDatabase.close();

        return id;
    }

    public long atualizarConsulta(Consulta consulta){
        liteDatabase = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        String tabela = DataBase.TABELA_CONSULTA;

        String colunaDesc = DataBase.CONSULTA_DESCRICAO;
        String descricao = consulta.getDescricao();
        values.put(colunaDesc, descricao);

        String colunaStatus = DataBase.CONSULTA_STATUS;
        String status = consulta.getStatus();
        values.put(colunaStatus, status);

        String whereClause =    DataBase.CONSULTA_DATA + " = ? AND " +
                                DataBase.ID_EST_PACIENTE_CON + " = ? AND " +
                                DataBase.ID_EST_MEDICO_CON + " = ?";

        String[] parametros = new String[3];
        parametros[0] = consulta.getData();
        parametros[1] = String.valueOf(consulta.getIdPaciente());
        parametros[2] = String.valueOf(consulta.getIdMedico());

        long id = liteDatabase.update(tabela, values, whereClause, parametros);

        liteDatabase.close();

        return id;
    }

    private Consulta criarConsulta(Cursor cursor){

        String colunaData = DataBase.CONSULTA_DATA;
        int indexColunaData = cursor.getColumnIndex(colunaData);
        String data = cursor.getString(indexColunaData);

        String colunaDesc = DataBase.CONSULTA_DESCRICAO;
        int indexColunaDesc = cursor.getColumnIndex(colunaDesc);
        String descricao = cursor.getString(indexColunaDesc);

        String colunaIdPaciente = DataBase.ID_EST_PACIENTE_CON;
        int indexColunaIdPaciente = cursor.getColumnIndex(colunaIdPaciente);
        long idPaciente = cursor.getInt(indexColunaIdPaciente);

        String colunaIdMedico = DataBase.ID_EST_MEDICO_CON;
        int indexColunaIdMedico = cursor.getColumnIndex(colunaIdMedico);
        long idMedico = cursor.getInt(indexColunaIdMedico);

        String colunaStatus = DataBase.CONSULTA_STATUS;
        int indexColunaStatus = cursor.getColumnIndex(colunaStatus);
        String status = cursor.getString(indexColunaStatus);

        Consulta consulta = new Consulta();

        consulta.setData(data);
        consulta.setDescricao(descricao);
        consulta.setIdPaciente(idPaciente);
        consulta.setIdMedico(idMedico);
        consulta.setStatus(status);

        return consulta;
    }

    private Consulta getConsulta(String query, String[] argumentos){
        liteDatabase = dataBaseHelper.getReadableDatabase();

        Cursor cursor = liteDatabase.rawQuery(query, argumentos);

        Consulta consulta = null;

        if(cursor.moveToNext()){
            consulta = criarConsulta(cursor);
        }

        cursor.close();
        liteDatabase.close();

        return consulta;
    }

    public Consulta getConsulta(String data, long idPaciente, long idMedico){
        String query = "SELECT * FROM " + DataBase.TABELA_CONSULTA +
                        " WHERE " + DataBase.CONSULTA_DATA + " LIKE ?" +
                        " AND " + DataBase.ID_EST_PACIENTE_CON  + " LIKE ?" +
                    " AND " + DataBase.ID_EST_MEDICO_CON  + " LIKE ?";;

        String idPacienteString = Long.toString(idPaciente);
        String idMedicoString = Long.toString(idMedico);

        String[] argumentos  = {data, idPacienteString, idMedicoString};

        Consulta consulta = getConsulta(query, argumentos);

        return consulta;
    }
}