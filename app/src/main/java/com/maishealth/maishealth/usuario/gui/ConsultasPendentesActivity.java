package com.maishealth.maishealth.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.maishealth.maishealth.R;

public class ConsultasPendentesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas_pendentes);
    }
    public void voltarMenuPac(View view){
        Intent intent= new Intent(ConsultasPendentesActivity.this, MenuPaciente2.class);
        startActivity(intent);
        finish();
    }
}
