package com.maishealth.maishealth.infra;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper; // Cria banco de dados

/**
 * Classe responsável por criar tabelas e o banco de dados
 */

public class DataBase extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 5;
    private static final String DATABASE_NAME = "dbmaishealth";

    //TABELA PESSOA
    public static final String TABELA_PESSOA = "pessoa";
    public static final String ID_PESSOA = "id_pessoa";
    public static final String PESSOA_NOME = "nome";
    public static final String PESSOA_SEXO = "sexo";
    public static final String PESSOA_DATANASC = "data_nasc";
    public static final String CPF = "cpf";
    public static final String ID_EST_USUARIO_PE = "id_est_usuario";

    // TABELA USUÁRIO
    public static final String TABELA_USUARIO = "usuario";
    public static final String ID_USUARIO = "id_usuario";
    public static final String USUARIO_EMAIL = "email";
    public static final String USUARIO_SENHA = "senha";

    //TABELA PACIENTE
    public static final String TABELA_PACIENTE = "paciente";
    public static final String ID_PACIENTE = "id_paciente";
    public static final String PACIENTE_SANGUE = "tipo_sangue";
    public static final String ID_EST_USUARIO_PA = "id_est_usuario";
    
    //TABELA MEDICO
    public static final String TABELA_MEDICO = "medico";
    public static final String ID_MEDICO = "id_medico";
    public static final String CRM = "crm";
    public static final String ESTADO = "estado";
    public static final String ESPECIALIDADE = "especialidade";
    public static final String ID_EST_USUARIO_ME = "id_est_usuario";

    //TABELA CONSULTA
    public static final String TABELA_CONSULTA = "consulta";
    public static final String CONSULTA_DATA = "data";
    public static final String CONSULTA_DESCRICAO = "descricao";
    public static final String ID_EST_PACIENTE_CON = "id_est_paciente";
    public static final String ID_EST_MEDICO_CON = "id_est_medico";
    public static final String CONSULTA_STATUS = "status";

    //TABELA DOENCA CRONICA
    public static final String TABELA_DOENCACRONICA = "doenca";
    public static final String ID_DOENCA = "id_doenca";
    public static final String DOENCA_NOME = "nome";
    public static final String DOENCA_DESCRICAO = "descricao";

    //TABELA MEDICAMENTO
    public static final String TABELA_MEDICAMENTO = "medicamento";
    public static final String ID_MEDICAMENTO = "id_medicamento";
    public static final String MEDICAMENTO_NOME = "nome";

    //TABELA POSTO
    public static final String TABELA_POSTO = "posto";
    public static final String ID_POSTO = "id_posto";
    public static final String POSTO_NOME = "nome";
    public static final String POSTO_LOCAL = "local";

    //TABELA PACIENTE-DOENCA
    public static final String TABELA_PACIENTE_DOENCA = "paciente_doenca";
    public static final String ID_EST_PACIENTE_PA_DO = "id_paciente";
    public static final String ID_EST_DOENCA_PA_DO = "id_doenca";

    //TABELA MEDICO-POSTO
    public static final String TABELA_MEDICO_POSTO = "medico_posto";
    public static final String ID_EST_MEDICO_ME_POS = "id_medico";
    public static final String ID_EST_POSTO_ME_POS = "id_posto";

    //TABELA CONSULTA-MEDICAMENTO
    public static final String TABELA_CONSULTA_MEDICAMENTO = "consulta_medicamento";
    public static final String ID_EST_CONSULTA_CON_MEM = "id_consulta";
    public static final String ID_EST_MEDICAMENTO_CON_MEM = "id_medicamento";


    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABELA_USUARIO + " (" +
                ID_USUARIO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USUARIO_EMAIL + " TEXT NOT NULL UNIQUE, " +
                USUARIO_SENHA + " TEXT NOT NULL);");
                
        db.execSQL("CREATE TABLE " + TABELA_PESSOA + " (" +
                ID_PESSOA + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PESSOA_NOME + " TEXT NOT NULL, " +
                PESSOA_SEXO + " TEXT NOT NULL, " +
                PESSOA_DATANASC + " TEXT NOT NULL, " +
                CPF + " TEXT NOT NULL, " +
                ID_EST_USUARIO_PE + " INTEGER);");

        db.execSQL("CREATE TABLE " + TABELA_PACIENTE + " (" +
                ID_PACIENTE + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PACIENTE_SANGUE + " TEXT NOT NULL, " +
                ID_EST_USUARIO_PA + " INTEGER);");
    
        db.execSQL("CREATE TABLE " + TABELA_MEDICO + " (" +
                ID_MEDICO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CRM + " TEXT NOT NULL, " +
                ESTADO + " TEXT NOT NULL, " +
				ESPECIALIDADE + " TEXT NOT NULL, " +
                ID_EST_USUARIO_ME + " INTEGER);");

        db.execSQL("CREATE TABLE " + TABELA_CONSULTA + " (" +
                CONSULTA_DATA + " TEXT NOT NULL, " +
                CONSULTA_DESCRICAO + " TEXT, " +
                ID_EST_PACIENTE_CON + " INTEGER, " +
                ID_EST_MEDICO_CON + " INTEGER, " +
                CONSULTA_STATUS + " TEXT);");

        db.execSQL("CREATE TABLE " + TABELA_DOENCACRONICA + " (" +
                ID_DOENCA + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DOENCA_NOME + " TEXT NOT NULL, " +
                DOENCA_DESCRICAO + " TEXT NOT NULL);");

        db.execSQL("CREATE TABLE " + TABELA_MEDICAMENTO + " (" +
                ID_MEDICAMENTO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MEDICAMENTO_NOME + " TEXT NOT NULL);");

        db.execSQL("CREATE TABLE " + TABELA_POSTO + " (" +
                ID_POSTO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                POSTO_NOME + " TEXT NOT NULL, " +
                POSTO_LOCAL + " TEXT NOT NULL);");

        db.execSQL("CREATE TABLE " + TABELA_PACIENTE_DOENCA + " (" +
                ID_EST_PACIENTE_PA_DO + " INTEGER, " +
                ID_EST_DOENCA_PA_DO + " INTEGER);");

        db.execSQL("CREATE TABLE " + TABELA_MEDICO_POSTO + " (" +
                ID_EST_MEDICO_ME_POS + " INTEGER, " +
                ID_EST_POSTO_ME_POS + " INTEGER);");

        db.execSQL("CREATE TABLE " + TABELA_CONSULTA_MEDICAMENTO + " (" +
                ID_EST_CONSULTA_CON_MEM + " INTEGER, " +
                ID_EST_MEDICAMENTO_CON_MEM + " INTEGER);");
    }

    //Atualização da tabela
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        String query1 = "DROP TABLE IF EXISTS " + TABELA_USUARIO;
        db.execSQL(query1);

        String query2 = "DROP TABLE IF EXISTS " + TABELA_PESSOA;
        db.execSQL(query2);

        String query3 = "DROP TABLE IF EXISTS " + TABELA_PACIENTE;
        db.execSQL(query3);

        String query4 = "DROP TABLE IF EXISTS " + TABELA_MEDICO;
        db.execSQL(query4);
        
        this.onCreate(db);
    }
}
