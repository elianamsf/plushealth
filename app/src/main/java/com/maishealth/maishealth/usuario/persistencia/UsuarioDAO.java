package com.maishealth.maishealth.usuario.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.maishealth.maishealth.infra.DataBase;
import com.maishealth.maishealth.usuario.dominio.Usuario;

public class UsuarioDAO {
    private SQLiteDatabase liteDatabase;
    private DataBase dataBase;

    public UsuarioDAO(Context context) {
        dataBase = new DataBase(context);
    }

    public long inserirUsuário(Usuario usuario){
        liteDatabase = dataBase.getWritableDatabase();
        ContentValues values = new ContentValues();

        String tabela = DataBase.TABELA_USUARIO;

        String colunaEmail = DataBase.USUARIO_EMAIL;
        String email = usuario.getEmail();
        values.put(colunaEmail, email);

        String colunaSenha = DataBase.USUARIO_SENHA;
        String senha = usuario.getSenha();
        values.put(colunaSenha, senha);

        String colunaIdPessoa = DataBase.ID_EST_PESSOA;
        long idPessoa = usuario.getIdPessoa();
        values.put(colunaIdPessoa, idPessoa);

        long id = liteDatabase.insert(tabela, null, values);

        liteDatabase.close();

        return id;
    }

    public long atualizarUsuario(Usuario usuario){
        liteDatabase = dataBase.getWritableDatabase();
        ContentValues values = new ContentValues();

        String tabela = DataBase.TABELA_USUARIO;

        String colunaEmail = DataBase.USUARIO_EMAIL;
        String email = usuario.getEmail();
        values.put(colunaEmail, email);

        String colunaSenha = DataBase.USUARIO_SENHA;
        String senha = usuario.getSenha();
        values.put(colunaSenha, senha);

        String whereClause = DataBase.ID_USUARIO + " = ?";
        String[] parametros = new String[1];
        parametros[0] = String.valueOf(usuario.getId());

        long id = liteDatabase.update(tabela, values, whereClause , parametros);

        liteDatabase.close();

        return id;
    }

    public Usuario criarUsuario(Cursor cursor){
        String colunaId = DataBase.ID_USUARIO;
        int indexColunaId = cursor.getColumnIndex(colunaId);
        long id = cursor.getInt(indexColunaId);

        String colunaEmail = DataBase.USUARIO_EMAIL;
        int indexColunaEmail = cursor.getColumnIndex(colunaEmail);
        String email = cursor.getString(indexColunaEmail);

        String colunaSenha = DataBase.USUARIO_SENHA;
        int indexColunaSenha = cursor.getColumnIndex(colunaSenha);
        String senha = cursor.getString(indexColunaSenha);

        String colunaIdPessoa = DataBase.ID_EST_PESSOA;
        int indexColunaIdPessoa = cursor.getColumnIndex(colunaIdPessoa);
        long idPessoa = cursor.getInt(indexColunaIdPessoa);

        Usuario usuario = new Usuario();

        usuario.setId(id);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setIdPessoa(idPessoa);

        return usuario;
    }

    public Usuario getUsuario(long id){
        liteDatabase = dataBase.getReadableDatabase();

        String query = "SELECT * FROM " + DataBase.TABELA_USUARIO +
                " WHERE " + DataBase.ID_USUARIO + " LIKE ?";

        String idString = Long.toString(id);
        String[] argumentos = {idString};

        Cursor cursor = liteDatabase.rawQuery(query, argumentos);

        Usuario usuario = null;

        if(cursor.moveToNext()){
            usuario = criarUsuario(cursor);
        }
        cursor.close();
        liteDatabase.close();

        return usuario;

    }
}
