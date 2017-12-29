package com.maishealth.maishealth.usuario.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.maishealth.maishealth.infra.DataBase;
import com.maishealth.maishealth.usuario.dominio.Posto;

public class PostoDAO {
    private SQLiteDatabase liteDatabase;
    private DataBase dataBaseHelper;

    public PostoDAO(Context context) {
        dataBaseHelper = new DataBase(context);
    }

    public long inserirPosto(Posto posto){
        liteDatabase = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        String tabela = DataBase.TABELA_POSTO;

        String colunaNome = DataBase.POSTO_NOME;
        String nome = posto.getNome();
        values.put(colunaNome, nome);

        String colunaLocal = DataBase.POSTO_LOCAL;
        String local = posto.getLocal();
        values.put(colunaLocal, local);

        long id = liteDatabase.insert(tabela, null, values);

        liteDatabase.close();

        return id;
    }

    public long atualizarPosto(Posto posto){
        liteDatabase = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        String tabela = DataBase.TABELA_POSTO;

        String colunaNome = DataBase.POSTO_NOME;
        String nome = posto.getNome();
        values.put(colunaNome, nome);

        String colunaLocal = DataBase.POSTO_LOCAL;
        String local = posto.getLocal();
        values.put(colunaLocal, local);

        String whereClause = DataBase.ID_POSTO + " = ?";
        String[] parametros = new String[1];
        parametros[0] = String.valueOf(posto.getId());

        long id = liteDatabase.update(tabela, values, whereClause , parametros);

        liteDatabase.close();

        return id;
    }

    private Posto criarPosto(Cursor cursor){
        String colunaId = DataBase.ID_POSTO;
        int indexColunaId = cursor.getColumnIndex(colunaId);
        long id = cursor.getInt(indexColunaId);

        String colunaNome = DataBase.POSTO_NOME;
        int indexColunaNome = cursor.getColumnIndex(colunaNome);
        String nome = cursor.getString(indexColunaNome);

        String colunaLocal = DataBase.POSTO_LOCAL;
        int indexColunaLocal = cursor.getColumnIndex(colunaLocal);
        String local = cursor.getString(indexColunaLocal);

        Posto posto = new Posto();

        posto.setId(id);
        posto.setNome(nome);
        posto.setLocal(local);

        return posto;
    }

    private Posto getPosto(String query, String[] argumentos) {
        liteDatabase = dataBaseHelper.getReadableDatabase();

        Cursor cursor = liteDatabase.rawQuery(query, argumentos);

        Posto posto = null;

        if (cursor.moveToNext()) {
            posto = criarPosto(cursor);
        }
        cursor.close();
        liteDatabase.close();

        return posto;
    }

    public Posto getPosto(long id){
        String query = "SELECT * FROM " + DataBase.TABELA_POSTO +
                " WHERE " + DataBase.ID_POSTO + " LIKE ?";

        String idString = Long.toString(id);
        String[] argumentos = {idString};

        return this.getPosto(query, argumentos);
    }
}
