package com.maishealth.maishealth.usuario.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.maishealth.maishealth.infra.DataBase;
import com.maishealth.maishealth.usuario.dominio.Usuario;

public class UsuarioDAO {
    private SQLiteDatabase liteDatabase;
    private DataBase dataBaseHelper;

    public UsuarioDAO(Context context) {
        dataBaseHelper = new DataBase(context);
    }

    public long inserirUsuário(Usuario usuario){
        liteDatabase = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        String tabela = DataBase.TABELA_USUARIO;

        String colunaEmail = DataBase.USUARIO_EMAIL;
        String email = usuario.getEmail();
        values.put(colunaEmail, email);

        String colunaSenha = DataBase.USUARIO_SENHA;
        String senha = usuario.getSenha();
        values.put(colunaSenha, senha);

        long id = liteDatabase.insert(tabela, null, values);

        liteDatabase.close();

        return id;
    }

    public long atualizarUsuario(Usuario usuario){
        liteDatabase = dataBaseHelper.getWritableDatabase();
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

    private Usuario criarUsuario(Cursor cursor){
        String colunaId = DataBase.ID_USUARIO;
        int indexColunaId = cursor.getColumnIndex(colunaId);
        long id = cursor.getInt(indexColunaId);

        String colunaEmail = DataBase.USUARIO_EMAIL;
        int indexColunaEmail = cursor.getColumnIndex(colunaEmail);
        String email = cursor.getString(indexColunaEmail);

        String colunaSenha = DataBase.USUARIO_SENHA;
        int indexColunaSenha = cursor.getColumnIndex(colunaSenha);
        String senha = cursor.getString(indexColunaSenha);

        Usuario usuario = new Usuario();

        usuario.setId(id);
        usuario.setEmail(email);
        usuario.setSenha(senha);

        return usuario;
    }

    private Usuario getUsuario(String query, String[] argumentos){
        liteDatabase = dataBaseHelper.getReadableDatabase();

        Cursor cursor = liteDatabase.rawQuery(query, argumentos);

        Usuario usuario = null;

        if(cursor.moveToNext()){
            usuario = criarUsuario(cursor);
        }
        cursor.close();
        liteDatabase.close();

        return usuario;
    }

    public Usuario getUsuario(long id){
        String query = "SELECT * FROM " + DataBase.TABELA_USUARIO +
                " WHERE " + DataBase.ID_USUARIO + " LIKE ?";

        String idString = Long.toString(id);
        String[] argumentos = {idString};

        Usuario usuario = getUsuario(query, argumentos);

        return usuario;
    }

    public Usuario getUsuarioByEmail(String email){
        liteDatabase = dataBaseHelper.getReadableDatabase();

        String query = "SELECT * FROM " + DataBase.TABELA_USUARIO +
                " WHERE " + DataBase.USUARIO_EMAIL + " LIKE ?";

        String[] argumentos = {email};

        Usuario usuario = getUsuario(query, argumentos);

        return usuario;

    }
    //public Usuario getSenhaByEmail(String email){
        //liteDatabase = dataBaseHelper.getReadableDatabase();
        //String query ="SELECT * FROM " +DataBase.TABELA_USUARIO +
                //"WHERE" +DataBase.USUARIO_SENHA +" LIKE ?";

        //String[] argumentos ={email};
        //Usuario senha =getSenha(query,argumentos);
        //return senha;
    //}
}
