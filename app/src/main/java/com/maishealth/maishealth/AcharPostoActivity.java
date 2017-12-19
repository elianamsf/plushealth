package com.maishealth.maishealth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.maishealth.maishealth.usuario.gui.Login1Activity;
import com.maishealth.maishealth.usuario.gui.MenuPaciente2;

public class AcharPostoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achar_posto);
    }
    public void voltarParaMenuPac(View view){
        Intent intent= new Intent(AcharPostoActivity.this, MenuPaciente2.class);
        startActivity(intent);
        finish();
    }
    public void testeLogin(View view){
        Intent intent=new Intent(AcharPostoActivity.this,Login1Activity.class);
        startActivity(intent);
        finish();
    }
}
