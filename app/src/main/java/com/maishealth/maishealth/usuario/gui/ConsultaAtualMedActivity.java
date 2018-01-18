package com.maishealth.maishealth.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.maishealth.maishealth.R;

public class ConsultaAtualMedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_atual_med);
    }
    private void mudarTela(Class tela){
        Intent intent=new Intent(this, tela);
        startActivity(intent);
        finish();
    }
    public void voltarListaPacientes(View view){this.mudarTela(ListaDeConsultasParaMedicoActivity.class);

    }
    //falta atualizar real
    public void atualizarConsultaPaciente(View view) {

        this.mudarTela(MenuMedicoActivity.class);}
}
