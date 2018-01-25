package com.maishealth.maishealth.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.maishealth.maishealth.R;
import com.maishealth.maishealth.usuario.negocio.ServicosMedico;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;


public class HorarioTrabMedicoActivity extends AppCompatActivity {
    private EditText editTextQtdVagasMed;
    private Spinner spinnerHorarioMedico;
    private DatePicker datePicker;
    private final String[] listaHorarioMedico = {"Manhã", "Tarde", "Noite"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario_trab_medico);

        datePicker = findViewById(R.id.datePicker);
        editTextQtdVagasMed = findViewById(R.id.editTextQtdVagasMed);
        spinnerHorarioMedico = findViewById(R.id.editTextInicioHorMed);
        Button btnConfirmarConsultas = findViewById(R.id.bt_confirmar_hor_montado_med);

        // inicia os valores do spinner:
        spinnerHorarioMedico.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, listaHorarioMedico));
        spinnerHorarioMedico.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnConfirmarConsultas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String qtdVagas = editTextQtdVagasMed.getText().toString();
                int qtdVagasInt = Integer.parseInt(qtdVagas);

                String turno = (String) spinnerHorarioMedico.getSelectedItem();

                setContentView(R.layout.activity_escolher_dia);
                datePicker = findViewById(R.id.datePicker);
                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth();
                int year = datePicker.getYear();

                setContentView(R.layout.activity_horario_trab_medico);

                SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
                Date date = new Date(year - 1900, month, day);
                String strDate = dateFormatter.format(date);

                ServicosMedico servicosMedico = new ServicosMedico(getApplicationContext());
                servicosMedico.registrarConsultas(strDate, qtdVagasInt, turno);
                

            }
        });




    }



}

