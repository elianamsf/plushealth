package com.maishealth.maishealth.usuario.gui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.maishealth.maishealth.R;
import com.maishealth.maishealth.usuario.dominio.Pessoa;
import com.maishealth.maishealth.usuario.negocio.ServicosPessoa;

import static com.maishealth.maishealth.infra.ConstantesSharedPreferences.DEFAULT_ID_USER_PREFERENCES;
import static com.maishealth.maishealth.infra.ConstantesSharedPreferences.ID_USER_PREFERENCES;
import static com.maishealth.maishealth.infra.ConstantesSharedPreferences.TITLE_PREFERENCES;

public class MenuPaciente2 extends AppCompatActivity {
    private TextView lblNomePaciente;
    private SharedPreferences sharedPreferences;
    private ServicosPessoa servicosPessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_paciente2);

        lblNomePaciente = findViewById(R.id.lblNomePaciente);

        servicosPessoa = new ServicosPessoa(getApplicationContext());
        sharedPreferences = getSharedPreferences(TITLE_PREFERENCES, MODE_PRIVATE);
        long idUsuario = sharedPreferences.getLong(ID_USER_PREFERENCES, DEFAULT_ID_USER_PREFERENCES);

        if(idUsuario != DEFAULT_ID_USER_PREFERENCES){
            Pessoa pessoa = servicosPessoa.searchPessoaByIdUsuario(idUsuario);
            try{
                lblNomePaciente.setText(pessoa.getNome());
            }catch (Exception e){
                Log.i("MenuPaciente", e.getMessage());
            }
        }
    }

    public void telaMarcarConsulta(View view){
        this.mudarTela(MarcaConsultaActivity.class);
    }

    public void telaMeuHistorico(View view){
        this.mudarTela(MeuHistoricoActivity.class);
    }

    public void telaConsultasPendentes(View view){
        this.mudarTela(ConsultasPendentesActivity.class);
    }

    public void telaAcharPosto(View view){
        this.mudarTela(AcharPostoActivity.class);
    }

    public void onClickSair(View view){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();

        this.mudarTela(Login1Activity.class);
    }

    public void mudarTela(Class tela){
        Intent intent=new Intent(MenuPaciente2.this, tela);
        startActivity(intent);
        finish();
    }
}
