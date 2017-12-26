package com.maishealth.maishealth.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.maishealth.maishealth.R;

public class MeuHistoricoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meu_historico);
    }
    public void voltarMenuPacI(View view){
        Intent intent= new Intent(MeuHistoricoActivity.this, MenuPaciente2.class);
        startActivity(intent);
        finish();

    }

}
