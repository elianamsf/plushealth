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

public class ListaDeConsultasParaMedicoActivity extends AppCompatActivity {

    //isso aq dos nomes e descricoes foram feitas para os testes, mas vcs q tem q adptar para na vdd
    //vir os nomes dos PACIENTES
    int[] IMAGES2 ={R.drawable.ic_event_38dp};
    String[] NAMES2 = {"Paciente João","Paciente Guilherme","CopiChand"};
    String[] DESCRIPTION2={"NÃO SEI OQ COLOCO AQ HM"};

    @Override
    public void onBackPressed(){this.retornoMenuMedico();}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_consultas_para_medico);

        ListView listView=(ListView) findViewById(R.id.listView);
        ListaDeConsultasParaMedicoActivity.CustomAdapter customAdapter=new ListaDeConsultasParaMedicoActivity.CustomAdapter();

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
    private void mudarTela(Class tela){
            Intent intent=new Intent(this, tela);
            startActivity(intent);
            finish();
    }
    private  void retornoMenuMedico(){this.mudarTela(MenuMedicoActivity.class);}

    //falta metodo para mostrar detalhes da consulta atual do medico(pegar o cliq da pessoa e mostrar essa tela)
    public void telaConsultaAtualMedico(View view){this.mudarTela(ConsultaAtualMedActivity.class);}
}
