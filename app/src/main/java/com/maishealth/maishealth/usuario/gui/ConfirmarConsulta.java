package com.maishealth.maishealth.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.maishealth.maishealth.R;

public class ConfirmarConsulta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_consulta);
    }
    public void voltarParaMenu(View view){
        Intent intent= new Intent(ConfirmarConsulta.this,MenuPaciente.class);
        startActivity(intent);
        finish();
    }
}
