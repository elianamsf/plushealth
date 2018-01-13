package com.maishealth.maishealth.usuario.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.maishealth.maishealth.usuario.dominio.Sintoma;
import com.maishealth.maishealth.infra.DataBase;

public class SintomaDAO {

    private SQLiteDatabase liteDatabase;
    private DataBase dataBaseHelper;

    public SintomaDAO(Context context) {
        dataBaseHelper = new DataBase(context);
    }

    public long inserirSintoma (Sintoma sintoma){
        liteDatabase = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        String tabela = DataBase.TABELA_SINTOMA;

        String colunaNomeSintoma = DataBase.SINTOMA_NOME;
        String nomeSintoma = sintoma.getSintoma();
        values.put (colunaNomeSintoma, nomeSintoma);

        long id = liteDatabase.insert(tabela, null, values);

        liteDatabase.close();

        return id;
    }

    private Sintoma criarSintoma (Cursor cursor){
     String colunaSintoma = DataBase.SINTOMA_NOME;
     int indexColunaSintoma = cursor.getColumnIndex(colunaSintoma);
     String nomeSintoma = cursor.getString(indexColunaSintoma);

     Sintoma sintoma = new Sintoma();

     sintoma.setSintoma(nomeSintoma);

     return sintoma;
    }

    public Sintoma getSintoma ( String query, String [] argumentos){
       liteDatabase = dataBaseHelper.getReadableDatabase();

       Cursor cursor = liteDatabase.rawQuery(query, argumentos);

       Sintoma sintoma = null;

       if (cursor.moveToNext()){
           sintoma = criarSintoma(cursor);
       }

       cursor.close();
       liteDatabase.close();

       return sintoma;
    }

    public Sintoma getSintoma (long id){
        String query = "SELECT * FROM " + DataBase.TABELA_SINTOMA +
                " WHERE " + DataBase.SINTOMA_NOME + " LIKE ?";

        String idString = Long.toString(id);

        String[] argumentos = {idString};

        return  this.getSintoma(query, argumentos);
    }
}
