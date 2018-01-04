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
    public void voltarParaMenuPac(View view){
        Intent intent= new Intent(AcharPostoActivity.this, MenuPaciente2.class);
        startActivity(intent);
        finish();
    }
    public void testeLogin(View view){
        Intent intent=new Intent(AcharPostoActivity.this,MarcacaoSintomasPacActivity.class);
        startActivity(intent);
        finish();
    }
}
