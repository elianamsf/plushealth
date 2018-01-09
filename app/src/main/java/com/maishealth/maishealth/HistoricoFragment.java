package com.maishealth.maishealth;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
//import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.maishealth.maishealth.Historico;
import com.maishealth.maishealth.HistoricoAdapter;
import com.maishealth.maishealth.R;
import com.maishealth.maishealth.usuario.gui.MeuHistoricoActivity;

import java.util.List;


public class HistoricoFragment extends Fragment implements RecycleViewOnClickListenerHack   {

    //private RecyclerView mRecycleView;
    //private List<Historico> mListHistorico;
    protected RecyclerView mRecycleView;
    private HistoricoAdapter adapter;

    @Nullable
    @Override
    //A Classe LayoutInflater nesse context serve para transformar o xml do nosso fragment em uma view.
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_historicos,container,false);

        mRecycleView=(RecyclerView) view.findViewById(R.id.rv_list_historico);
        //setHasFixedSize. Estamos avisando que o tamanho do recycleView não vai mudar
        mRecycleView.setHasFixedSize(true);

        //vamos utilizar o LinearLayoutManager, ele tem as mesmas propriedade do nosso LinearLayout
        LinearLayoutManager llm=new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleView.setLayoutManager(llm);

        //mListHistorico =((MeuHistoricoActivity) getActivity()).getListHistorico();
        List<Historico> mListHistorico = ((MeuHistoricoActivity) getActivity()).getListHistorico();
        adapter =new HistoricoAdapter(mListHistorico,getActivity());
        adapter.setRecycleViewOnClickListenerHack(this);
        mRecycleView.setAdapter(adapter);

        return view;

    }
    @Override
    public  void onClickListener(View view ,int position){
        Toast.makeText(getActivity(),"Position:" +position,Toast.LENGTH_SHORT).show();
        //aqui embaixo um metodo p remover mas n precisa né..
        //adapter.removeListItem(position);
    }

}
