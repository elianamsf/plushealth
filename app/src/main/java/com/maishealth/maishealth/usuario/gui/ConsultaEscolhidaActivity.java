package com.maishealth.maishealth.usuario.gui;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.maishealth.maishealth.R;
import com.maishealth.maishealth.usuario.dominio.Pessoa;
import com.maishealth.maishealth.usuario.negocio.ServicosPessoa;

import static com.maishealth.maishealth.infra.ConstanteSharedPreferences.DEFAULT_ID_USER_PREFERENCES;
import static com.maishealth.maishealth.infra.ConstanteSharedPreferences.ID_USER_PREFERENCES;
import static com.maishealth.maishealth.infra.ConstanteSharedPreferences.TITLE_PREFERENCES;

public class ConsultaEscolhidaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_escolhida);

        TextView lblNomePacienteCons = findViewById(R.id.textViewNomePacConsEsc);

        ServicosPessoa servicosPessoa = new ServicosPessoa(getApplicationContext());
        SharedPreferences sharedPreferences = getSharedPreferences(TITLE_PREFERENCES, MODE_PRIVATE);
        long idUsuario = sharedPreferences.getLong(ID_USER_PREFERENCES, DEFAULT_ID_USER_PREFERENCES);

        if (idUsuario != DEFAULT_ID_USER_PREFERENCES) {
            Pessoa pessoa = servicosPessoa.searchPessoaByIdUsuario(idUsuario);
            try {
                lblNomePacienteCons.setText(pessoa.getNome());
            } catch (Exception e) {
                Log.i("ConsultaEscolhida", e.getMessage());
            }
        }
    }
}

