package com.maishealth.maishealth.usuario.gui;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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
import java.util.ArrayList;
import java.util.List;
import static com.maishealth.maishealth.infra.ConstanteSharedPreferences.DEFAULT_ID_USER_PREFERENCES;
import static com.maishealth.maishealth.infra.ConstanteSharedPreferences.ID_USER_PREFERENCES;
import static com.maishealth.maishealth.infra.ConstanteSharedPreferences.TITLE_PREFERENCES;
public class MarcacaoSintomasPacActivity extends AppCompatActivity {
    private static final String SINTOMA_S_INSERIDO_S_COM_SUCESSO = "Sintoma(s) Inserido(s) com sucesso!";
    private Button btnConfirmaSintoma;
    private CheckedTextView febre;
    private CheckedTextView ardenciaOlho;
    private CheckedTextView dorCabeca;
    private CheckedTextView dorAbdominal;
    private CheckedTextView enjoo;
    private CheckedTextView coceira;
    private CheckedTextView bolhas;
    private CheckedTextView olhoInchado;
    private List listaCheckbox = new ArrayList( 8);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marcacao_sintomas_pac);
        Toolbar toolbar = (Toolbar) findViewById(R.id.materialup_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                onBackPressed();
            }
        });
        TextView lblNomePaciente = findViewById(R.id.textViewNomePacSint);
        febre= findViewById(R.id.sintoma_febre);
        ardenciaOlho= findViewById(R.id.sintoma_ardencia_olho);
        dorCabeca= findViewById(R.id.sintoma_dor_de_cabeca);
        dorAbdominal= findViewById(R.id.sintoma_dor_abdominal);
        enjoo= findViewById(R.id.sintoma_enjoo);
        coceira= findViewById(R.id.sintoma_coceira);
        bolhas= findViewById(R.id.sintoma_bolhas);
        olhoInchado= findViewById(R.id.sintoma_olhos_inchados);
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
        String listaSintomas[]= Mask.split(edtSintomaString);
        for (String sintoma: listaSintomas){
            ServicosPaciente servicosPaciente = new ServicosPaciente(getApplicationContext());
            servicosPaciente.inserirSintoma(sintoma);
        }
        for (Object checkedSintoma: listaCheckbox){
            ServicosPaciente servicosPaciente = new ServicosPaciente(getApplicationContext());
            servicosPaciente.inserirSintoma(checkedSintoma.toString());
        }

        GuiUtil.myToast(this, SINTOMA_S_INSERIDO_S_COM_SUCESSO);
        this.mudarTela(EscolherDiaDaConsultaActivity.class);

    }
    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckedTextView) view).isChecked();
        switch (view.getId()) {
            case R.id.sintoma_ardencia_olho:
                if (checked)
                { ardenciaOlho.setChecked(false);
                    listaCheckbox.remove(ardenciaOlho.getText().toString());
                }
                else {
                    ardenciaOlho.setChecked(true);
                    listaCheckbox.add(ardenciaOlho.getText().toString());
                }
                break;
            case R.id.sintoma_febre:
                if (checked)
                { febre.setChecked(false);
                    listaCheckbox.remove(febre.getText().toString());
                }
                else { febre.setChecked(true);
                    listaCheckbox.add(febre.getText().toString());
                }
                break;
            case R.id.sintoma_dor_abdominal:
                if (checked)
                { dorAbdominal.setChecked(false);
                    listaCheckbox.remove(dorAbdominal.getText().toString());}
                else { dorAbdominal.setChecked(true);
                    listaCheckbox.add(dorAbdominal.getText().toString());}
                break;
            case R.id.sintoma_dor_de_cabeca:
                if (checked)
                { dorCabeca.setChecked(false);
                    listaCheckbox.remove(dorCabeca.getText().toString());}
                else { dorCabeca.setChecked(true);
                    listaCheckbox.add(dorCabeca.getText().toString());}
                break;
            case R.id.sintoma_bolhas:
                if (checked)
                { bolhas.setChecked(false);
                    listaCheckbox.remove(bolhas.getText().toString());}
                else { bolhas.setChecked(true);
                    listaCheckbox.add(bolhas.getText().toString());}
                break;
            case R.id.sintoma_olhos_inchados:
                if (checked)
                { olhoInchado.setChecked(false);
                    listaCheckbox.remove(olhoInchado.getText().toString());}
                else { olhoInchado.setChecked(true);
                    listaCheckbox.add(olhoInchado.getText().toString());}
                break;
            case R.id.sintoma_enjoo:
                if (checked)
                { enjoo.setChecked(false);
                    listaCheckbox.remove(enjoo.getText().toString());}
                else { enjoo.setChecked(true);
                    listaCheckbox.add(enjoo.getText().toString());}
                break;
            case R.id.sintoma_coceira:
                if (checked)
                { coceira.setChecked(false);
                    listaCheckbox.remove(coceira.getText().toString());}
                else { coceira.setChecked(true);
                    listaCheckbox.add(coceira.getText().toString());}
                break;
        }
    }
}
