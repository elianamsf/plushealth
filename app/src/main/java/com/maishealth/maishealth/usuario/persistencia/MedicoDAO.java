package com.maishealth.maishealth.usuario.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.maishealth.maishealth.infra.DataBase;
import com.maishealth.maishealth.usuario.dominio.Medico;

public class MedicoDAO {
    private SQLiteDatabase liteDatabase;
    private DataBase dataBaseHelper;

    public MedicoDAO(Context context) {
        dataBaseHelper = new DataBase(context);
    }

    public long inserirMedico(Medico medico){
        liteDatabase = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        String tabela = DataBase.TABELA_MEDICO;

        String colunaCRM = DataBase.CRM;
        String crm = medico.getCrm();
        values.put(colunaCRM, crm);

        String colunaEstado = DataBase.ESTADO;
        String estado = medico.getEstado();
        values.put(colunaEstado, estado);

        String colunaEspecialidade = DataBase.ESPECIALIDADE;
        String especialidade = medico.getEspecialidade();
        values.put(colunaEspecialidade, especialidade);

        String colunaIdUsuario = DataBase.ID_EST_USUARIO_ME;
        long idUsuario = medico.getIdUsuario();
        values.put(colunaIdUsuario, idUsuario);

        long id = liteDatabase.insert(tabela, null, values);

        liteDatabase.close();

        return id;
    }

    public long atualizarMedico(Medico medico){
        liteDatabase = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        String tabela = DataBase.TABELA_MEDICO;

        String colunaCRM = DataBase.CRM;
        String crm = medico.getCrm();
        values.put(colunaCRM, crm);

        String colunaEstado = DataBase.ESTADO;
        String estado = medico.getEstado();
        values.put(colunaEstado, estado);

        String colunaEspecialidade = DataBase.ESPECIALIDADE;
        String especialidade = medico.getEspecialidade();
        values.put(colunaEspecialidade, especialidade);

        String whereClause = DataBase.ID_MEDICO + " = ?";
        String[] parametros = new String[1];
        parametros[0] = String.valueOf(medico.getId());

        long id = liteDatabase.update(tabela, values, whereClause, parametros);

        liteDatabase.close();

        return id;
    }

    private Medico criarMedico(Cursor cursor){
        String colunaID = DataBase.ID_MEDICO;
        int indexColunaID = cursor.getColumnIndex(colunaID);
        long id = cursor.getInt(indexColunaID);

        String colunaCRM = DataBase.CRM;
        int indexColunaCRM = cursor.getColumnIndex(colunaCRM);
        String crm = cursor.getString(indexColunaCRM);

        String colunaEstado = DataBase.ESTADO;
        int indexColunaEstado = cursor.getColumnIndex(colunaEstado);
        String estado = cursor.getString(indexColunaEstado);

        String colunaEspecialidade = DataBase.ESPECIALIDADE;
        int indexColunaEspecialidade = cursor.getColumnIndex(colunaEspecialidade);
        String especialidade = cursor.getString(indexColunaEspecialidade);

        String colunaIdUsuario = DataBase.ID_EST_USUARIO_PE;
        int indexColunaIdUsuario = cursor.getColumnIndex(colunaIdUsuario);
        long idUsuario = cursor.getInt(indexColunaIdUsuario);

        Medico medico = new Medico();

        medico.setId(id);
        medico.setCrm(crm);
        medico.setEstado(estado);
        medico.setEspecialidade(especialidade);
        medico.setIdUsuario(idUsuario);

        return medico;
    }

    private Medico getMedico(String query, String[] argumentos){
        liteDatabase = dataBaseHelper.getReadableDatabase();

        Cursor cursor = liteDatabase.rawQuery(query, argumentos);

        Medico medico = null;

        if(cursor.moveToNext()){
            medico = criarMedico(cursor);
        }
        cursor.close();
        liteDatabase.close();

        return medico;
    }

    public Medico getMedico(long id){
        String query = "SELECT * FROM " + DataBase.TABELA_MEDICO +
                " WHERE " + DataBase.ID_MEDICO + " LIKE ?";

        String idString = Long.toString(id);
        String[] argumentos = {idString};

        return this.getMedico(query, argumentos);
    }

    public Medico getMedicoByRegiaoCrm(String regiao, String crm){
        String query = "SELECT * FROM " + DataBase.TABELA_MEDICO +
                " WHERE " + DataBase.ESTADO + " LIKE ? AND " +
                DataBase.CRM + " LIKE ?";

        String[] argumentos = {regiao, crm};

        return this.getMedico(query, argumentos);
    }

    public Medico getMedicoByIdUsuario(long idUsuario){
        String query = "SELECT * FROM " + DataBase.TABELA_MEDICO +
                " WHERE " + DataBase.ID_EST_USUARIO_ME + " LIKE ?";

        String idString = Long.toString(idUsuario);
        String[] argumentos = {idString};

        return this.getMedico(query, argumentos);
    }
}
