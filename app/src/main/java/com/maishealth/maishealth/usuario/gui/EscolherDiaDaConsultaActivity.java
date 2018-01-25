package com.maishealth.maishealth.usuario.gui;

//import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
//import android.widget.Toolbar;

import com.maishealth.maishealth.R;
import com.maishealth.maishealth.infra.Mask;
import com.maishealth.maishealth.usuario.dominio.Consulta;
import com.maishealth.maishealth.usuario.negocio.ServicosPaciente;

import java.util.ArrayList;
import java.util.List;

public class EscolherDiaDaConsultaActivity extends AppCompatActivity {

    private EditText dataConsulta;
    private Spinner spinnerTurno;
    private final String[] listaTurno = {"Manhã", "Tarde", "Noite"};
    private Button btConfirma;
    private ListView lvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolher_dia_da_consulta);
        dataConsulta = findViewById(R.id.dataConsulta);
        dataConsulta.addTextChangedListener(Mask.insert("##/##/####", dataConsulta));
        btConfirma = findViewById(R.id.confirm);
        lvResultado = (ListView) findViewById(R.id.listView);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                onBackPressed();
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, listaTurno);


        spinnerTurno = findViewById(R.id.spinner);
        spinnerTurno.setAdapter(adapter);
        spinnerTurno.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public  void onClickConfirmar(View view){
        String data = dataConsulta.getText().toString();
        String turno = (String) spinnerTurno.getSelectedItem();
        ServicosPaciente servicosPaciente = new ServicosPaciente(getApplicationContext());
        Consulta consulta = new Consulta();
        //List<String> dadosConsulta = new ArrayList<>();
        consulta = servicosPaciente.mostrarConsultas(data, turno);
        //dadosConsulta.add(consulta.getData());
        //dadosConsulta.add(consulta.getTurno());
        //dadosConsulta.add(consulta.getDescricao());
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,dadosConsulta);
        //lvResultado.setAdapter(adapter);
        btConfirma.setVisibility(View.INVISIBLE);
        lvResultado.setVisibility(View.VISIBLE);

    }

    public void mudarTela(Class tela){
        Intent intent = new Intent(this, tela);
        startActivity(intent);
        finish();
    }
    
    @Override
    public void onBackPressed() {
        this.mudarTela(MarcacaoSintomasPacActivity.class);
    }

    // metodo atrelado aos objetos no listView responsavel por exibir os detalhes
    public void telaConfirmarConsulta(View view){
        this.mudarTela(ConfirmarConsulta.class);
        
    }

}
