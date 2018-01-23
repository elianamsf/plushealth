package com.maishealth.maishealth.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.maishealth.maishealth.R;

public class ConsultaAtualMedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_atual_med);

        Toolbar toolbar = (Toolbar) findViewById(R.id.materialup_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                onBackPressed();
            }
        });

    }
    private void mudarTela(Class tela){
        Intent intent=new Intent(this, tela);
        startActivity(intent);
        finish();
    }
    @Override
    public void onBackPressed() {
        this.mudarTela(ListaDeConsultasParaMedicoActivity.class);
    }
    
    public void voltarListaPacientes(View view){this.mudarTela(ListaDeConsultasParaMedicoActivity.class);

    }
    //falta atualizar real
    public void atualizarConsultaPaciente(View view) {

        this.mudarTela(MenuMedicoActivity.class);}
}
