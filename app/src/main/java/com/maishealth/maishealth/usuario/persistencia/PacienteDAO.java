package com.maishealth.maishealth.usuario.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.maishealth.maishealth.infra.DataBase;
import com.maishealth.maishealth.usuario.dominio.Paciente;

public class PacienteDAO {
    private SQLiteDatabase liteDatabase;
    private DataBase dataBase;

    public PacienteDAO(Context context) {
        dataBase = new DataBase(context);
    }

    public long inserirPaciente(Paciente paciente){
        liteDatabase = dataBase.getWritableDatabase();
        ContentValues values = new ContentValues();

        String tabela = DataBase.TABELA_PACIENTE;

        String colunaIdUsuario = DataBase.ID_EST_USUARIO_P;
        long idUsuario = paciente.getIdUsuario();
        values.put(colunaIdUsuario, idUsuario);

        long id = liteDatabase.insert(tabela, null, values);

        liteDatabase.close();

        return id;

    }

    public long atualizarPaciente(Paciente paciente){
        liteDatabase = dataBase.getWritableDatabase();
        ContentValues values = new ContentValues();

        String tabela = DataBase.TABELA_PACIENTE;

        String colunaIdUsuario = DataBase.ID_EST_USUARIO_P;
        long idUsuario = paciente.getIdUsuario();
        values.put(colunaIdUsuario, idUsuario);

        String whereClause = DataBase.ID_PACIENTE + " = ?";
        String[] parametros = new String[1];
        parametros[0] = String.valueOf(paciente.getId());

        long id = liteDatabase.update(tabela, values, whereClause , parametros);

        liteDatabase.close();

        return id;
    }

    public Paciente criarPaciente(Cursor cursor){
        String colunaId = DataBase.ID_PACIENTE;
        int indexColunaId = cursor.getColumnIndex(colunaId);
        long id = cursor.getInt(indexColunaId);

        String colunaIdUsuario = DataBase.ID_EST_USUARIO_P;
        int indexColunaIdUsuario = cursor.getColumnIndex(colunaIdUsuario);
        long idUsuario = cursor.getInt(indexColunaIdUsuario);

        Paciente paciente = new Paciente();

        paciente.setId(id);
        paciente.setIdUsuario(idUsuario);

        return paciente;
    }

    public Paciente getPaciente(long id){
        liteDatabase = dataBase.getReadableDatabase();

        String query = "SELECT * FROM " + DataBase.TABELA_PACIENTE +
                " WHERE " + DataBase.ID_PACIENTE + " LIKE ?";

        String idString = Long.toString(id);
        String[] argumentos = {idString};

        Cursor cursor = liteDatabase.rawQuery(query, argumentos);

        Paciente paciente = null;

        if(cursor.moveToNext()){
            paciente = criarPaciente(cursor);
        }
        cursor.close();
        liteDatabase.close();

        return paciente;
    }
}
