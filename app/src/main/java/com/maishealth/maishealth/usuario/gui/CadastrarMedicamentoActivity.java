package com.maishealth.maishealth.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.maishealth.maishealth.R;

public class CadastrarMedicamentoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_medicamentos);
    }

    private void mudarTela(Class tela){
        Intent intent=new Intent(this, tela);
        startActivity(intent);
        finish();
    }
    public void cancelarCadastroMedicamento(View view){this.mudarTela(MenuMedicoActivity.class);
    }
}
