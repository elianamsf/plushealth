package com.maishealth.maishealth.usuario.gui;
//import android.support.v4.app....

//import android.app.Fragment;

//app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.maishealth.maishealth.Historico;
import com.maishealth.maishealth.R;

        import java.util.ArrayList;
import java.util.List;

public class MeuHistoricoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meu_historico);

        //getSupport....
        //HistoricoFragment fragment =((HistoricoFragment) getSupportFragmentManager()).findFragmentByTag("mainFrag");
        //if (fragment==null){
         //   fragment=new HistoricoFragment();
           // FragmentTransaction ft =getSupportFragmentManager().beginTransaction();
            //ft replace Adiciona o fragmente que criamos manualmente ao nosso relativeLayout que declaramos no MeuHistorico.xml
            //ft.replace(R.id.rl_fragment_historico,fragment,"mainFrag");
            //ft commit Realiza as alterações do nosso FragmentTransaction.
            //ft.commit();
        }
    //}
    //mudança de tela - retornando para a tela de paciente
    public void voltarMenuPacI(View view){
        Intent intent= new Intent(MeuHistoricoActivity.this, MenuPaciente.class);
        startActivity(intent);
        finish();

    }
    //metodo que vai retornar uma lista de historico(consultas no caso)
    public List<Historico>getListHistorico(){
        List<Historico>mlist=new ArrayList<>();
        for (int i=0; i<20 ;i++){
            Historico historico= new Historico("Data"+i, "Clinico geral", "Pessoa de nome"+i);
            mlist.add(historico);
        }
        return mlist;
    }

}
