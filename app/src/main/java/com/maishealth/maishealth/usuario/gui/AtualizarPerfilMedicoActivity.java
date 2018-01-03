package com.maishealth.maishealth.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.maishealth.maishealth.R;
import com.maishealth.maishealth.usuario.gui.MenuMedicoActivity;

public class AtualizarPerfilMedicoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_perfil_medico);
    }
    public void retornoTelaMedico(View view){
        Intent intent=new Intent(AtualizarPerfilMedicoActivity.this, MenuMedicoActivity.class);
        startActivity(intent);
        finish();
    }
}