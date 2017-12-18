package com.maishealth.maishealth.usuario.persistencia;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.maishealth.maishealth.infra.DataBase;
import com.maishealth.maishealth.usuario.dominio.Pessoa;


public class PessoaDAO {
    private SQLiteDatabase liteDatabase;
    private DataBase dataBaseHelper;

    public PessoaDAO(Context context) {
        dataBaseHelper = new DataBase(context);
    }

    public long inserirPessoa(Pessoa pessoa){
        liteDatabase = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        String tabela = DataBase.TABELA_PESSOA;

        String colunaNome = DataBase.PESSOA_NOME;
        String nome = pessoa.getNome();
        values.put(colunaNome, nome);

        String colunaSexo = DataBase.PESSOA_SEXO;
        String sexo = pessoa.getSexo();
        values.put(colunaSexo, sexo);

        String colunaDataNasc = DataBase.PESSOA_DATANASC;
        String dataNasc = pessoa.getDataNasc();
        values.put(colunaDataNasc, dataNasc);

        String colunaCPF = DataBase.CPF;
        String cpf = pessoa.getCpf();
        values.put(colunaCPF, cpf);

        String colunaIdUsuario = DataBase.ID_EST_USUARIO_PE;
        long idUsuario = pessoa.getIdUsuario();
        values.put(colunaIdUsuario, idUsuario);

        long id = liteDatabase.insert(tabela, null, values);

        liteDatabase.close();

        return id;
    }

    public long atualizarPessoa (Pessoa pessoa){
        liteDatabase = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        String tabela = DataBase.TABELA_PESSOA;

        String colunaNome = DataBase.PESSOA_NOME;
        String nome = pessoa.getNome();
        values.put(colunaNome, nome);

        String colunaSexo = DataBase.PESSOA_SEXO;
        String sexo = pessoa.getSexo();
        values.put(colunaSexo, sexo);

        String colunaDataNasc = DataBase.PESSOA_DATANASC;
        String dataNasc = pessoa.getDataNasc();
        values.put(colunaDataNasc, dataNasc);

        String colunaCPF = DataBase.CPF;
        String cpf = pessoa.getCpf();
        values.put(colunaCPF, cpf);

        String whereClause = DataBase.ID_PESSOA + " = ?";
        String[] parametros = new String[1];
        parametros[0] = String.valueOf(pessoa.getId());

        long id = liteDatabase.update(tabela, values, whereClause, parametros);

        liteDatabase.close();

        return id;
    }

    private Pessoa criarPessoa(Cursor cursor){

        String colunaId = DataBase.ID_PESSOA;
        int indexColunaId = cursor.getColumnIndex(colunaId);
        long id = cursor.getInt(indexColunaId);

        String colunaNome = DataBase.PESSOA_NOME;
        int indexColunaNome = cursor.getColumnIndex(colunaNome);
        String nome = cursor.getString(indexColunaNome);

        String colunaSexo = DataBase.PESSOA_SEXO;
        int indexColunaSexo = cursor.getColumnIndex(colunaSexo);
        String sexo = cursor.getString(indexColunaSexo);

        String colunaDataNasc = DataBase.PESSOA_DATANASC;
        int indexColunaDataNasc = cursor.getColumnIndex(colunaDataNasc);
        String dataNasc = cursor.getString(indexColunaDataNasc);

        String colunaCPF = DataBase.CPF;
        int indexColunaCPF = cursor.getColumnIndex(colunaCPF);
        String cpf = cursor.getString(indexColunaCPF);

        String colunaIdUsuario = DataBase.ID_EST_USUARIO_PE;
        int indexColunaIdUsuario = cursor.getColumnIndex(colunaIdUsuario);
        long idUsuario = cursor.getInt(indexColunaIdUsuario);

        Pessoa pessoa =  new Pessoa();

        pessoa.setId(id);
        pessoa.setNome(nome);
        pessoa.setSexo(sexo);
        pessoa.setDataNasc(dataNasc);
        pessoa.setCpf(cpf);
        pessoa.setIdUsuario(idUsuario);

        return pessoa;
    }

    private Pessoa getPessoa(String query, String[] argumentos){
        liteDatabase = dataBaseHelper.getReadableDatabase();

        Cursor cursor = liteDatabase.rawQuery(query, argumentos);

        Pessoa pessoa = null;

        if(cursor.moveToNext()){
            pessoa = criarPessoa(cursor);
        }

        cursor.close();
        liteDatabase.close();

        return pessoa;
    }

    public Pessoa getPessoa(long id){
        String query = "SELECT * FROM " + DataBase.TABELA_PESSOA +
                " WHERE " + DataBase.ID_PESSOA + " LIKE ?";

        String idString = Long.toString(id);
        String[] argumentos = {idString};

        Pessoa pessoa = getPessoa(query, argumentos);

        return pessoa;
    }
}
