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
    public void mudarTela(Class tela){
        Intent intent = new Intent(this, tela);
        startActivity(intent);
        finish();
    }
    
    public void onBackPressed() {
        this.mudarTela(MenuPaciente.class);
    }
    
    public void telaRetornoMenuPac(View view){
        this.mudarTela(MenuPaciente.class);
    }
}
