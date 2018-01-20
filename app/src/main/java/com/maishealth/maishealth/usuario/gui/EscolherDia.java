package com.maishealth.maishealth.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.maishealth.maishealth.R;

public class EscolherDia extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolher_dia);

    }
    private void mudarTela(Class tela){
        Intent intent=new Intent(this, tela);
        startActivity(intent);
        finish();
    }
    @Override
    public void onBackPressed() {
        this.mudarTela(MenuMedicoActivity.class);
    }
    
    public void voltarParaTelaMenuMedico(View view) {
        this.mudarTela(MenuMedicoActivity.class);
    }
    //aq embaixo falta pegar o dia clicado na tela do calendário
    public void selecionarDiaDoCalendario(View view){

        this.mudarTela(HorarioTrabMedicoActivity.class);
    }

}
