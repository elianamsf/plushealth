package com.maishealth.maishealth.usuario.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.maishealth.maishealth.infra.DataBase;
import com.maishealth.maishealth.usuario.dominio.DoencaCronica;

public class DoencaCronicaDAO {
    private static SQLiteDatabase liteDatabase;
    private static DataBase dataBaseHelper;

    public DoencaCronicaDAO(Context context) {
        dataBaseHelper = new DataBase(context);
    }

    public long inserirDoenca(DoencaCronica doencaCronica){
        liteDatabase = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        String tabela = DataBase.TABELA_DOENCACRONICA;

        String colunaNome = DataBase.DOENCA_NOME;
        String nome = doencaCronica.getNome();
        values.put(colunaNome, nome);

        String colunaDescricao = DataBase.DOENCA_DESCRICAO;
        String descricao = doencaCronica.getDescricao();
        values.put(colunaDescricao, descricao);

        long id = liteDatabase.insert(tabela, null, values);

        liteDatabase.close();

        return id;
    }

    public long atualizarDoenca(DoencaCronica doencaCronica){
        liteDatabase = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        String tabela = DataBase.TABELA_DOENCACRONICA;

        String colunaNome = DataBase.DOENCA_NOME;
        String nome = doencaCronica.getNome();
        values.put(colunaNome, nome);

        String colunaDescricao = DataBase.DOENCA_DESCRICAO;
        String descricao = doencaCronica.getDescricao();
        values.put(colunaDescricao, descricao);

        String whereClause = DataBase.ID_DOENCA + " = ?";
        String[] parametros = new String[1];
        parametros[0] = String.valueOf(doencaCronica.getId());

        long id = liteDatabase.update(tabela, values, whereClause, parametros);

        liteDatabase.close();

        return id;
    }

    public DoencaCronica criarDoenca(Cursor cursor){
        String colunaId = DataBase.ID_DOENCA;
        int indexColunaId = cursor.getColumnIndex(colunaId);
        long id = cursor.getInt(indexColunaId);

        String colunaNome = DataBase.DOENCA_NOME;
        int indexColunaNome = cursor.getColumnIndex(colunaNome);
        String nome = cursor.getString(indexColunaNome);

        String colunaDescricao = DataBase.DOENCA_DESCRICAO;
        int indexColunaDescricao = cursor.getColumnIndex(colunaDescricao);
        String descricao = cursor.getString(indexColunaDescricao);

        DoencaCronica doencaCronica = new DoencaCronica();

        doencaCronica.setId(id);
        doencaCronica.setNome(nome);
        doencaCronica.setDescricao(descricao);

        return doencaCronica;
    }

    private DoencaCronica getDoenca(String query, String[] argumentos){
        liteDatabase = dataBaseHelper.getReadableDatabase();

        Cursor cursor = liteDatabase.rawQuery(query, argumentos);

        DoencaCronica doencaCronica = null;

        if(cursor.moveToNext()){
            doencaCronica = criarDoenca(cursor);
        }
        cursor.close();
        liteDatabase.close();

        return doencaCronica;
    }

    public DoencaCronica getDoenca(long id){
        String query = "SELECT * FROM " + DataBase.TABELA_DOENCACRONICA +
                " WHERE " + DataBase.ID_DOENCA + " LIKE ?";

        String idString = Long.toString(id);
        String[] argumentos = {idString};

        return this.getDoenca(query, argumentos);
    }
}
