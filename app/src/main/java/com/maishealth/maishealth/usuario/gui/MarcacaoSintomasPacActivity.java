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
    CheckedTextView febre;
    CheckedTextView ardenciaOlho;
    CheckedTextView dorCabeca;
    CheckedTextView dorAbdominal;
    CheckedTextView enjoo;
    CheckedTextView coceira;
    CheckedTextView bolhas;
    CheckedTextView olhoInchado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marcacao_sintomas_pac);
        TextView lblNomePaciente = findViewById(R.id.textViewNomePacSint);
        febre=(CheckedTextView)findViewById(R.id.sintoma_febre);
        ardenciaOlho=(CheckedTextView)findViewById(R.id.sintoma_ardencia_olho);
        dorCabeca=(CheckedTextView)findViewById(R.id.sintoma_dor_de_cabeca);
        dorAbdominal=(CheckedTextView)findViewById(R.id.sintoma_dor_abdominal);
        enjoo=(CheckedTextView)findViewById(R.id.sintoma_enjoo);
        coceira=(CheckedTextView)findViewById(R.id.sintoma_coceira);
        bolhas=(CheckedTextView)findViewById(R.id.sintoma_bolhas);
        olhoInchado=(CheckedTextView)findViewById(R.id.sintoma_olhos_inchados);

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
    
    public void mudarTela(Class tela){
        Intent intent = new Intent(this, tela);
        startActivity(intent);
        finish();
    }
    
    @Override
    public void onBackPressed() {
        this.mudarTela(MenuPaciente.class);
    }
    
    public void voltarMarcParaMenuPac(View view){
        this.mudarTela(MenuPaciente.class);
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

        this.mudarTela(EscolherDiaDaConsultaActivity.class);
        
    }

    public void onCheckboxClicked(View view) {

        boolean checked = ((CheckedTextView) view).isChecked();
        switch (view.getId()) {
            case R.id.sintoma_ardencia_olho:
                if (checked)
                    { ardenciaOlho.setChecked(false);}
                else { ardenciaOlho.setChecked(true); }

                break;

            case R.id.sintoma_febre:
                if (checked)
                    { febre.setChecked(false); }
                else { febre.setChecked(true); }

                break;

            case R.id.sintoma_dor_abdominal:
                if (checked)
                    { dorAbdominal.setChecked(false); }
                else { dorAbdominal.setChecked(true); }

                break;

            case R.id.sintoma_dor_de_cabeca:
                if (checked)
                    { dorCabeca.setChecked(false); }
                else { dorCabeca.setChecked(true); }

                break;

            case R.id.sintoma_bolhas:
                if (checked)
                    { bolhas.setChecked(false); }
                else { bolhas.setChecked(true); }

                break;

            case R.id.sintoma_olhos_inchados:
                if (checked)
                    { olhoInchado.setChecked(false); }
                else { olhoInchado.setChecked(true); }

                break;

            case R.id.sintoma_enjoo:
                if (checked)
                    { enjoo.setChecked(false); }
                else { enjoo.setChecked(true); }

                break;

            case R.id.sintoma_coceira:
                if (checked)
                    { coceira.setChecked(false); }
                else { coceira.setChecked(true); }

                break;

        }


    }

}
