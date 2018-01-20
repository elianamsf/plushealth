package com.maishealth.maishealth.usuario.gui;
//import android.support.v4.app....

//import android.app.Fragment;

//app.FragmentTransaction;
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

import java.util.ArrayList;
import java.util.List;

public class MeuHistoricoActivity extends AppCompatActivity {

    int[] IMAGES2 = {R.drawable.ic_event_38dp};
    String[] NAMES2 = {"Doutor Felipe-Clínico geral", "GANDHI", "CopiChand"};
    String[] DESCRIPTION2 = {"Data 11/02/2015", "Data 17/08/2019", "Data 23/05/2021"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meu_historico);

        ListView listView = (ListView) findViewById(R.id.listView);
        MeuHistoricoActivity.CustomAdapter customAdapter = new MeuHistoricoActivity.CustomAdapter();

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

    public void mudarTela(Class tela){
        Intent intent = new Intent(this, tela);
        startActivity(intent);
        finish();
    }
    
    @Override
    public void onBackPressed() {
        this.mudarTela(MenuPaciente.class);
    }
    
    //mudança de tela - retornando para a tela de paciente
    public void voltarMenuPacI(View view) {
        this.mudarTela(MenuPaciente.class);

    }
}
