package com.maishealth.maishealth.usuario.gui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.maishealth.maishealth.EscolherDiaDaConsultaActivity;
import com.maishealth.maishealth.R;
import com.maishealth.maishealth.infra.GuiUtil;
import com.maishealth.maishealth.infra.Mask;
import com.maishealth.maishealth.usuario.dominio.Pessoa;
import com.maishealth.maishealth.usuario.negocio.ServicosPaciente;
import com.maishealth.maishealth.usuario.negocio.ServicosPessoa;

import static com.maishealth.maishealth.infra.ConstanteSharedPreferences.DEFAULT_ID_USER_PREFERENCES;
import static com.maishealth.maishealth.infra.ConstanteSharedPreferences.ID_USER_PREFERENCES;
import static com.maishealth.maishealth.infra.ConstanteSharedPreferences.TITLE_PREFERENCES;

public class MarcacaoSintomasPacActivity extends AppCompatActivity {
    private Button btnConfirmaSintoma;
    CheckedTextView teste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marcacao_sintomas_pac);
        TextView lblNomePaciente = findViewById(R.id.textViewNomePacSint);
        teste=(CheckedTextView)findViewById(R.id.sintoma_febre);
        teste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (teste.isChecked() ){
                    teste.setChecked(false);
            }
                else teste.setChecked(true);  }
        });

        ServicosPessoa servicosPessoa = new ServicosPessoa(getApplicationContext());
        SharedPreferences sharedPreferences = getSharedPreferences(TITLE_PREFERENCES, MODE_PRIVATE);
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
        Intent intent= new Intent(MarcacaoSintomasPacActivity.this, MenuPaciente.class);
        startActivity(intent);
        finish();
    }

    //**olhar comentário na Activity de EscolherDiaDaConsulta
    public void marcarEnviarSintomas(View view){
        EditText edtSintoma = findViewById(R.id.editTextOutrosSint);
        String edtSintomaString = edtSintoma.getText().toString();
        String listaSintomas[]= Mask.split(edtSintomaString, ",");

        for (String sintoma: listaSintomas){
        ServicosPaciente servicosPaciente = new ServicosPaciente(getApplicationContext());
        servicosPaciente.inserirSintoma(sintoma);
        }

        GuiUtil.myToast(this,"Sintoma(s) Inserido(s) com sucesso!");

        Intent intent=new Intent(MarcacaoSintomasPacActivity.this, EscolherDiaDaConsultaActivity.class);
        startActivity(intent);
        finish();
    }

}
