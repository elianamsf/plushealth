package com.maishealth.maishealth.usuario.persistencia;

/**
 * Created by Eliana-DEV on 06/01/2018.
 */
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

    public void inserirConsultaSintoma (long idConsultaComSintoma, long sintoma ){
        liteDatabase = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        String tabela = DataBase.TABELA_CONSULTA_SINTOMA;

        String colunaIdConsulta = DataBase.ID_EST_SINTOMA_CON_SIN;
        values.put(colunaIdConsulta, idConsultaComSintoma);

        String colunaNomeSintomaComConsulta = DataBase.NOME_EST_SINTOMA_CON_SIN;
        values.put(colunaIdConsulta, sintoma);

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

        Sintoma sintoma;

        String colunaSintoma = DataBase.NOME_EST_SINTOMA_CON_SIN;
        int indexColunaSintoma = cursor.getColumnIndex(colunaSintoma);

        while (cursor.moveToNext()) {
            long nomeSintoma = cursor.getInt(indexColunaSintoma);
            sintoma = sintomaDAO.getSintoma(nomeSintoma);
            listaSintomas.add(sintoma);

        }
        cursor.close();
        liteDatabase.close();

        return listaSintomas;
    }

}
