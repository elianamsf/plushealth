package com.maishealth.maishealth.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.maishealth.maishealth.R;

public class ConsultasPendentesActivity extends AppCompatActivity {

    //isso aq dos nomes e descricoes foram feitas para os testes, mas vcs q tem q adptar para na vdd
    //vir os nomes dos medicos
    int[] IMAGES2 ={R.drawable.ic_event_38dp};
    String[] NAMES2 = {"Doutora Kimbelly-Pediatra kkk","GANDHI","CopiChand"};
    String[] DESCRIPTION2={"Data 16/03/2018","Data 17/08/2019","Data 23/05/2021"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas_pendentes);

        ListView listView=(ListView) findViewById(R.id.listView);
            ConsultasPendentesActivity.CustomAdapter customAdapter=new ConsultasPendentesActivity.CustomAdapter();

        listView.setAdapter(customAdapter);
    }
    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            //return 0;
            return IMAGES2.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        //@SuppressLint("ViewHolder")
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.customlayout, null);

            ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
            TextView textView_name = (TextView) view.findViewById(R.id.textView_name);
            TextView textView_description = (TextView) view.findViewById(R.id.textView_descriptions);


            //imageView.setImageResource(IMAGES2[i]);
            textView_name.setText(NAMES2[i]);
            textView_description.setText(DESCRIPTION2[i]);

            return view;
        }
    }
    //falta metodo para mostrar detalhes da consulta pendente(pegar o cliq da pessoa e mostrar)

    public void mudarTela(Class tela){
        Intent intent = new Intent(this, tela);
        startActivity(intent);
        finish();
    }
    
    @Override
    public void onBackPressed() {
        this.mudarTela(MenuPaciente.class);
    }
    
    public void voltarMenuPac(View view){
        this.mudarTela(MenuPaciente.class);
    }
}
