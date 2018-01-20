package com.maishealth.maishealth.usuario.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.maishealth.maishealth.infra.DataBase;
import com.maishealth.maishealth.usuario.dominio.Consulta;
import com.maishealth.maishealth.usuario.dominio.EnumStatusConsulta;

public class ConsultaDAO {
    public static final String LIKE = " LIKE ?";
    public static final String SELECT_FROM = "SELECT * FROM ";
    public static final String AND = " AND ";
    public static final String WHERE = " WHERE ";
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

        String colunaPaciente = DataBase.ID_EST_PACIENTE_CON;
        long idPaciente = consulta.getIdPaciente();
        values.put(colunaPaciente, idPaciente);

        String whereClause = DataBase.ID_CONSULTA + " = ? ";
        String[] parametros = new String[1];
        parametros[0] = String.valueOf(consulta.getId());

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

        String colunaIdConsulta = DataBase.ID_CONSULTA;
        int  indexColunaIdConsulta = cursor.getColumnIndex(colunaIdConsulta);
        long idConsulta = cursor.getInt(indexColunaIdConsulta);

        Consulta consulta = new Consulta();

        consulta.setData(data);
        consulta.setDescricao(descricao);
        consulta.setIdPaciente(idPaciente);
        consulta.setIdMedico(idMedico);
        consulta.setStatus(status);
        consulta.setId(idConsulta);

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

    public Consulta getConsulta(String data, long idPaciente, long idMedico, long idConsulta){
        String query = SELECT_FROM + DataBase.TABELA_CONSULTA +
                WHERE + DataBase.CONSULTA_DATA + LIKE +
                AND + DataBase.ID_EST_PACIENTE_CON  + LIKE +
                AND + DataBase.ID_EST_MEDICO_CON  + LIKE +
                AND + DataBase.ID_CONSULTA + LIKE;

        String idPacienteString = Long.toString(idPaciente);
        String idMedicoString = Long.toString(idMedico);
        String idConsultaString   = Long.toString(idConsulta);

        String[] argumentos  = {data, idPacienteString, idMedicoString, idConsultaString};

        return this.getConsulta(query, argumentos);
    }

    public Consulta getConsultaByData(String data){
        String query = SELECT_FROM + DataBase.TABELA_CONSULTA +
                WHERE + DataBase.CONSULTA_DATA + LIKE +
                AND + DataBase.CONSULTA_STATUS + LIKE;

        String status = EnumStatusConsulta.DISPONIVEL.toString();

        String[] argumentos  = {data, status };

        return this.getConsulta(query, argumentos);
    }

}
