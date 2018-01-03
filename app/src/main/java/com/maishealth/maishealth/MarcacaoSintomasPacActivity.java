package com.maishealth.maishealth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.maishealth.maishealth.infra.GuiUtil;
import com.maishealth.maishealth.usuario.gui.MenuPaciente2;

public class MarcacaoSintomasPacActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marcacao_sintomas_pac);
    }
    public void voltarMarcParaMenuPac(View view){
        Intent intent= new Intent(MarcacaoSintomasPacActivity.this, MenuPaciente2.class);
        startActivity(intent);
        finish();
    }
    public void marcarEnviarSintomas(View view){
        GuiUtil.myToast(this,"Em construção");

    }

}
