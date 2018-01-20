package com.maishealth.maishealth.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.maishealth.maishealth.R;

public class AcharPostoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achar_posto);
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
    
    public void voltarParaMenuPac(View view){
        this.mudarTela(MenuPaciente.class);
    }
    public void testeLogin(View view){
        Intent intent=new Intent(AcharPostoActivity.this,MarcacaoSintomasPacActivity.class);
        startActivity(intent);
        finish();
    }
}
