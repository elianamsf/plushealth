package com.maishealth.maishealth.usuario.gui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.maishealth.maishealth.R;
import com.maishealth.maishealth.infra.GuiUtil;
import com.maishealth.maishealth.usuario.dominio.Pessoa;
import com.maishealth.maishealth.usuario.negocio.ServicosPessoa;

import static com.maishealth.maishealth.infra.ConstanteSharedPreferences.DEFAULT_ID_USER_PREFERENCES;
import static com.maishealth.maishealth.infra.ConstanteSharedPreferences.ID_USER_PREFERENCES;
import static com.maishealth.maishealth.infra.ConstanteSharedPreferences.TITLE_PREFERENCES;

public class MarcacaoSintomasPacActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marcacao_sintomas_pac);
        TextView lblNomePaciente = findViewById(R.id.textViewNomePacSint);

        ServicosPessoa servicosPessoa = new ServicosPessoa(getApplicationContext());
        sharedPreferences = getSharedPreferences(TITLE_PREFERENCES, MODE_PRIVATE);
        long idUsuario = sharedPreferences.getLong(ID_USER_PREFERENCES, DEFAULT_ID_USER_PREFERENCES);

        if(idUsuario != DEFAULT_ID_USER_PREFERENCES){
            Pessoa pessoa = servicosPessoa.searchPessoaByIdUsuario(idUsuario);
            try{
                lblNomePaciente.setText(pessoa.getNome());
            }catch (Exception e){
                Log.i("MarcSint", e.getMessage());
            }
        }
    }
    public void voltarMarcParaMenuPac(View view){
        Intent intent= new Intent(MarcacaoSintomasPacActivity.this, MenuPaciente2.class);
        startActivity(intent);
        finish();
    }
    public void marcarEnviarSintomas(View view){
        GuiUtil.myToast(this,"Em construção");

    }

}
