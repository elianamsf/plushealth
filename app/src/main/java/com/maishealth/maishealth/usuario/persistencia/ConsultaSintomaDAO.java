package com.maishealth.maishealth.usuario.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.maishealth.maishealth.infra.DataBase;
import com.maishealth.maishealth.usuario.dominio.Sintoma;

import java.util.ArrayList;

@SuppressWarnings("FieldCanBeLocal")
public class ConsultaSintomaDAO {

    private SQLiteDatabase liteDatabase;
    private DataBase dataBaseHelper;
    private ConsultaDAO consultaDAO;
    private SintomaDAO sintomaDAO;

    public ConsultaSintomaDAO (Context context){
        dataBaseHelper = new DataBase(context);
        consultaDAO = new ConsultaDAO(context);
        sintomaDAO = new SintomaDAO(context);
    }

    public void inserirConsultaSintoma (long idConsultaComSintoma, String sintoma ){
        liteDatabase = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        String tabela = DataBase.TABELA_CONSULTA_SINTOMA;

        String colunaIdConsulta = DataBase.ID_EST_SINTOMA_CON_SIN;
        values.put(colunaIdConsulta, idConsultaComSintoma);

        String colunaNomeSintomaComConsulta = DataBase.NOME_EST_SINTOMA_CON_SIN;
        values.put(colunaNomeSintomaComConsulta, sintoma);

        liteDatabase.insert(tabela, null, values);

        liteDatabase.close();
    }

    public ArrayList<Sintoma> getSintomasByConsulta(long idConsulta) {
        liteDatabase = dataBaseHelper.getReadableDatabase();
        ArrayList<Sintoma> listaSintomas = new ArrayList<>();

        String query = "SELECT * FROM " + DataBase.TABELA_CONSULTA_SINTOMA +
                " WHERE " + DataBase.ID_EST_SINTOMA_CON_SIN + " LIKE ?" +
                " ORDER BY " + DataBase.NOME_EST_SINTOMA_CON_SIN + " DESC";

        String idString = Long.toString(idConsulta);
        String[] argumentos = {idString};

        Cursor cursor = liteDatabase.rawQuery(query, argumentos);

        String colunaSintoma = DataBase.NOME_EST_SINTOMA_CON_SIN;
        int indexColunaSintoma = cursor.getColumnIndex(colunaSintoma);

        while (cursor.moveToNext()) {
            String nomeSintoma = cursor.getString(indexColunaSintoma);
            Sintoma sintoma = new Sintoma();
            sintoma.setSintoma(nomeSintoma);
            listaSintomas.add(sintoma);

        }
        cursor.close();
        liteDatabase.close();

        return listaSintomas;
    }
}
