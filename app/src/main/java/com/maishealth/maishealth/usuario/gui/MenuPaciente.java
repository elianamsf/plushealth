package com.maishealth.maishealth.usuario.gui;

import android.annotation.SuppressLint;
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

import static com.maishealth.maishealth.infra.ConstanteSharedPreferences.DEFAULT_ID_USER_PREFERENCES;
import static com.maishealth.maishealth.infra.ConstanteSharedPreferences.ID_USER_PREFERENCES;
import static com.maishealth.maishealth.infra.ConstanteSharedPreferences.TITLE_PREFERENCES;

public class MenuPaciente extends AppCompatActivity {
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_paciente);

        TextView lblNomePaciente = findViewById(R.id.lblNomePaciente);

        ServicosPessoa servicosPessoa = new ServicosPessoa(getApplicationContext());
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
        this.mudarTela(MarcacaoSintomasPacActivity.class);
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

    @SuppressLint("ApplySharedPref")
    public void onClickSair(View view){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();

        this.mudarTela(LoginActivity.class);
    }
    public void telaAtualizarPerfilPaciente(View view){
        this.mudarTela(AtualizarPerfilMedicoActivity.class);
    }

    private void mudarTela(Class tela){
        Intent intent=new Intent(MenuPaciente.this, tela);
        startActivity(intent);
        finish();
    }
}
