package com.maishealth.maishealth.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

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
    public void telaConsultasPendentes(View view){
        Intent intent= new Intent(MenuPaciente2.this, ConsultasPendentesActivity.class);
        startActivity(intent);
        finish();
    }
    public void telaAcharPosto(View view){
        Intent intent=new Intent(MenuPaciente2.this, AcharPostoActivity.class);
        startActivity(intent);
        finish();
    }
    public void SairPac(View view){
        Intent intent=new Intent(MenuPaciente2.this, Login1Activity.class);
        startActivity(intent);
        finish();
    }

}
