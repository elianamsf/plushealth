package com.maishealth.maishealth.usuario.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.maishealth.maishealth.R;
import com.maishealth.maishealth.usuario.negocio.ServicosMedico;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class HorarioTrabMedicoActivity extends AppCompatActivity {
    private DatePicker datePicker;
    private EditText editTextQtdVagasMed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario_trab_medico);

        datePicker = findViewById(R.id.datePicker);
        editTextQtdVagasMed = findViewById(R.id.editTextQtdVagasMed);
        Button btnConfirmarConsultas = findViewById(R.id.bt_confirmar_hor_montado_med);

        btnConfirmarConsultas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth() + 1;
                int year = datePicker.getYear();

                SimpleDateFormat dateFormatter = new SimpleDateFormat("MM-dd-yyyy");
                GregorianCalendar date = new GregorianCalendar(year, month, day);
                String strDate = dateFormatter.format(date);

                String qtdVagas = editTextQtdVagasMed.getText().toString();
                int qtdVagasInt = Integer.parseInt(qtdVagas);

                ServicosMedico servicosMedico = new ServicosMedico(getApplicationContext());
                servicosMedico.registrarConsultas(strDate,qtdVagasInt);
            }
        });

    }

}
