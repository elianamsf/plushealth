package com.maishealth.maishealth.usuario.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.maishealth.maishealth.infra.DataBase;
import com.maishealth.maishealth.usuario.dominio.Paciente;

public class PacienteDAO {
    private SQLiteDatabase liteDatabase;
    private DataBase dataBaseHelper;

    public PacienteDAO(Context context) {
        dataBaseHelper = new DataBase(context);
    }

    public long inserirPaciente(Paciente paciente){
        liteDatabase = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        String tabela = DataBase.TABELA_PACIENTE;

        String colunaIdUsuario = DataBase.ID_EST_USUARIO_PA;
        long idUsuario = paciente.getIdUsuario();
        values.put(colunaIdUsuario, idUsuario);

        String colunaTipoSangue = DataBase.PACIENTE_SANGUE;
        String tipoSangue = paciente.getTipoSangue();
        values.put(colunaTipoSangue, tipoSangue);

        long id = liteDatabase.insert(tabela, null, values);

        liteDatabase.close();

        return id;

    }

    public long atualizarPaciente(Paciente paciente){
        liteDatabase = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        String tabela = DataBase.TABELA_PACIENTE;

        String colunaIdUsuario = DataBase.ID_EST_USUARIO_PA;
        long idUsuario = paciente.getIdUsuario();
        values.put(colunaIdUsuario, idUsuario);

        String colunaTipoSangue = DataBase.PACIENTE_SANGUE;
        String tipoSangue = paciente.getTipoSangue();
        values.put(colunaTipoSangue, tipoSangue);

        String whereClause = DataBase.ID_PACIENTE + " = ?";
        String[] parametros = new String[1];
        parametros[0] = String.valueOf(paciente.getId());

        long id = liteDatabase.update(tabela, values, whereClause , parametros);

        liteDatabase.close();

        return id;
    }

    private Paciente criarPaciente(Cursor cursor){
        String colunaId = DataBase.ID_PACIENTE;
        int indexColunaId = cursor.getColumnIndex(colunaId);
        long id = cursor.getInt(indexColunaId);

        String colunaIdUsuario = DataBase.ID_EST_USUARIO_PA;
        int indexColunaIdUsuario = cursor.getColumnIndex(colunaIdUsuario);
        long idUsuario = cursor.getInt(indexColunaIdUsuario);

        String colunaTipoSangue = DataBase.PACIENTE_SANGUE;
        int indexColunaTipoSangue = cursor.getColumnIndex(colunaTipoSangue);
        String tipoSangue = cursor.getString(indexColunaTipoSangue);

        Paciente paciente = new Paciente();

        paciente.setId(id);
        paciente.setIdUsuario(idUsuario);
        paciente.setTipoSangue(tipoSangue);

        return paciente;
    }

    private Paciente getPaciente(String query, String[] argumentos){
        liteDatabase = dataBaseHelper.getReadableDatabase();

        Cursor cursor = liteDatabase.rawQuery(query, argumentos);

        Paciente paciente = null;

        if(cursor.moveToNext()){
            paciente = criarPaciente(cursor);
        }
        cursor.close();
        liteDatabase.close();

        return paciente;
    }

    public Paciente getPaciente(long id){
        String query = "SELECT * FROM " + DataBase.TABELA_PACIENTE +
                " WHERE " + DataBase.ID_PACIENTE + " LIKE ?";

        String idString = Long.toString(id);
        String[] argumentos = {idString};

        return this.getPaciente(query, argumentos);
    }

    public Paciente getPacienteByIdUsuario(long idUsuario){
        String query = "SELECT * FROM " + DataBase.TABELA_PACIENTE +
                " WHERE " + DataBase.ID_EST_USUARIO_PA + " LIKE ?";

        String idString = Long.toString(idUsuario);
        String[] argumentos = {idString};

        return this.getPaciente(query, argumentos);
    }
}
