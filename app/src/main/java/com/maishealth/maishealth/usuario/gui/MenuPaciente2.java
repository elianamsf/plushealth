package com.maishealth.maishealth.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.maishealth.maishealth.MarcaConsultaActivity;
import com.maishealth.maishealth.MeuHistoricoActivity;
import com.maishealth.maishealth.R;

public class MenuPaciente2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_paciente2);
    }

    public void telaMarcarConsulta(View view){
        Intent intent = new Intent(MenuPaciente2.this, MarcaConsultaActivity.class);
        startActivity(intent);
        finish();

    }
    public void telaMeuHistorico(View view){
        Intent intent= new Intent(MenuPaciente2.this, MeuHistoricoActivity.class);
        startActivity(intent);
        finish();
    }

}
