package com.maishealth.maishealth.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.maishealth.maishealth.R;
import com.maishealth.maishealth.usuario.negocio.ServicosMedico;

public class CadastrarMedicamentoActivity extends AppCompatActivity {
    private EditText nomeMedicamento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_medicamentos);
        Button btnCadastrar = findViewById(R.id.button4);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nomeMedicamento = findViewById(R.id.nomeMedicamento);
                String nomeMedicamentoString = nomeMedicamento.getText().toString();
                ServicosMedico servicosMedico = new ServicosMedico(getApplicationContext());
                servicosMedico.criarMedicamento(nomeMedicamentoString);
            }
        });
    }

    private void mudarTela(Class tela){
        Intent intent=new Intent(this, tela);
        startActivity(intent);
        finish();
    }
    public void cancelarCadastroMedicamento(View view){this.mudarTela(MenuMedicoActivity.class);
    }
}
