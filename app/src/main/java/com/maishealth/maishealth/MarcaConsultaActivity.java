package com.maishealth.maishealth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.maishealth.maishealth.usuario.gui.MenuPaciente2;

public class MarcaConsultaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marca_consulta);
    }

    public void voltarMenu(View view){
        Intent intent = new Intent(MarcaConsultaActivity.this, MenuPaciente2.class);
        startActivity(intent);
        finish();
    }
}
