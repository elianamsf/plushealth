package com.maishealth.maishealth.infra;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper; // Cria banco de dados



/**
 * Classe responsável por criar tabelas e o banco de dados
 */

public class DataBase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "dbmaishealth";
    
    
    //TABELA PESSOA
    public static final String TABELA_PESSOA = "pessoa";
    public static final String ID_PESSOA = "id_pessoa";
    public static final String PESSOA_NOME = "nome";
    public static final String PESSOA_SEXO = "sexo";
    public static final String PESSOA_DATANASC = "data_nasc";
    public static final String CPF = "cpf";

    // TABELA USUÁRIO
    public static final String TABELA_USUARIO = "usuario";
    public static final String ID_USUARIO = "id_usuario";
    public static final String USUARIO_EMAIL = "email";
    public static final String USUARIO_SENHA = "senha";
    public static final String ID_EST_PESSOA = "id_est_pessoa";
    
    //TABELA PACIENTE
    public static final String TABELA_PACIENTE = "paciente";
    public static final String ID_PACIENTE = "id_paciente";
    public static final String TABELA_HISTORICO = "historico";
    public static final String ID_EST_USUARIO_P = "id_est_usuario";
    
    //TABELA MEDICO
    public static final String TABELA_MEDICO = "medico";
    public static final String ID_MEDICO = "id_medico";
    public static final String CRM = "crm";
    public static final String ESTADO = "estado";
    public static final String ESPECIALIDADE = "especialidade";
    public static final String ID_EST_USUARIO_M = "id_est_usuario";



    private Context context;

    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABELA_USUARIO + " (" +
                ID_USUARIO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USUARIO_EMAIL + " TEXT NOT NULL, " +
                USUARIO_SENHA + " TEXT NOT NULL, " +
                ID_EST_PESSOA + "INTEGER);");
                
        db.execSQL("CREATE TABLE " + TABELA_PESSOA + " (" +
                ID_PESSOA + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PESSOA_NOME +" TEXT NOT NULL, " +
                PESSOA_SEXO +" TEXT NOT NULL, " +
                PESSOA_DATANASC +" TEXT NOT NULL, " +
                CPF + " TEXT NOT NULL);");

        db.execSQL("CREATE TABLE " + TABELA_PACIENTE + " (" +
                ID_PACIENTE +" TEXT NOT NULL, " +
                TABELA_HISTORICO +" TEXT NOT NULL, " + // ID da tabela, ainda não existe, tem criar no futuro.
                ID_EST_USUARIO_P + "INTEGER);");
    
        db.execSQL("CREATE TABLE " + TABELA_MEDICO + " (" +
                ID_MEDICO +" TEXT NOT NULL, " +
                CRM +" TEXT NOT NULL, " +
                ESTADO +" TEXT NOT NULL, " +
				ESPECIALIDADE +" TEXT NOT NULL, " +
                ID_EST_USUARIO_M + "INTEGER);");

    }

    //Atualização da tabela
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        

        String query1 = "DROP TABLE IF EXISTS " + TABELA_PESSOA;
        db.execSQL(query1);

        String query2 = "DROP TABLE IF EXISTS " + TABELA_USUARIO;
        db.execSQL(query2);
        
        String query3 = "DROP TABLE IF EXISTS " + TABELA_PACIENTE;
        db.execSQL(query3);

        String query4 = "DROP TABLE IF EXISTS " + TABELA_MEDICO;
        db.execSQL(query4);
        
        this.onCreate(db);

    }
}
