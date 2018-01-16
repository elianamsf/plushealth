package com.maishealth.maishealth.usuario.gui;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.maishealth.maishealth.R;

public class AtualizarPerfilPacActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_perfil_pac);
    }
    public void telaRetornoMenuPac(View view){
        Intent intent=new Intent(AtualizarPerfilPacActivity.this, MenuPaciente.class);
        startActivity(intent);
        finish();
    }
}