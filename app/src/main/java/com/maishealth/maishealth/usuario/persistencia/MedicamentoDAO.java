package com.maishealth.maishealth.usuario.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.maishealth.maishealth.infra.DataBase;
import com.maishealth.maishealth.usuario.dominio.Medicamento;

public class MedicamentoDAO {
    private SQLiteDatabase liteDatabase;
    private DataBase dataBaseHelper;

    public MedicamentoDAO(Context context) {
        dataBaseHelper = new DataBase(context);
    }

    public long inserirMedicamento(Medicamento medicamento){
        liteDatabase = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        String tabela = DataBase.TABELA_MEDICAMENTO;

        String colunaNome = DataBase.MEDICAMENTO_NOME;
        String nome = medicamento.getNome();
        values.put(colunaNome, nome);

        long id = liteDatabase.insert(tabela, null, values);

        liteDatabase.close();

        return id;
    }

    public long atualizarMedicamento(Medicamento medicamento){
        liteDatabase = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        String tabela = DataBase.TABELA_MEDICAMENTO;

        String colunaNome = DataBase.MEDICAMENTO_NOME;
        String nome = medicamento.getNome();
        values.put(colunaNome, nome);

        String whereClause = DataBase.ID_MEDICAMENTO + " = ?";
        String[] parametros = new String[1];
        parametros[0] = String.valueOf(medicamento.getId());

        long id = liteDatabase.update(tabela, values, whereClause , parametros);

        liteDatabase.close();

        return id;
    }

    private Medicamento criarMedicamento(Cursor cursor){
        String colunaId = DataBase.ID_MEDICAMENTO;
        int indexColunaId = cursor.getColumnIndex(colunaId);
        long id = cursor.getInt(indexColunaId);

        String colunaNome = DataBase.MEDICAMENTO_NOME;
        int indexColunaNome = cursor.getColumnIndex(colunaNome);
        String nome = cursor.getString(indexColunaNome);

        Medicamento medicamento = new Medicamento();

        medicamento.setId(id);
        medicamento.setNome(nome);

        return medicamento;
    }

    private Medicamento getMedicamento(String query, String[] argumentos){
        liteDatabase = dataBaseHelper.getReadableDatabase();

        Cursor cursor = liteDatabase.rawQuery(query, argumentos);

        Medicamento medicamento = null;

        if(cursor.moveToNext()){
            medicamento = criarMedicamento(cursor);
        }
        cursor.close();
        liteDatabase.close();

        return medicamento;
    }

    public Medicamento getMedicamento(long id){
        String query = "SELECT * FROM " + DataBase.TABELA_MEDICAMENTO +
                " WHERE " + DataBase.ID_MEDICAMENTO + " LIKE ?";

        String idString = Long.toString(id);
        String[] argumentos = {idString};

        return this.getMedicamento(query, argumentos);

    }
}
