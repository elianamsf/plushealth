package com.maishealth.maishealth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.maishealth.maishealth.usuario.gui.MenuPaciente2;

public class AtualizarPerfilPacActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_perfil_pac);
    }
    public void telaRetornoMenuPac(View view){
        Intent intent=new Intent(AtualizarPerfilPacActivity.this, MenuPaciente2.class);
        startActivity(intent);
        finish();
    }
}
