package com.maishealth.maishealth.usuario.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.maishealth.maishealth.R;

/**
 * Created by Eliana-DEV on 16/12/2017.
 */

public class SignUpChoiceActivity extends AppCompatActivity {

    private Button btnSouMedico, btnSouPaciente, btnVoltar;
    private Boolean mostrarOpcaoMedico;

    public Boolean getMostrarOpcaoMedico() {
        return mostrarOpcaoMedico;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_choice);

        btnSouMedico = findViewById(R.id.btnMedicoId);
        btnSouPaciente = findViewById(R.id.btnPacienteId);
        btnVoltar = findViewById(R.id.btnVoltarLoginId);

        btnSouMedico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarOpcaoMedico = true;
            }
        });

        btnSouPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarOpcaoMedico = false;
            }
        });
    }

    public void telaCadastro(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }

}
