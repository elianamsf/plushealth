package com.maishealth.maishealth;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import org.w3c.dom.Text;


import java.util.List;


public class HistoricoAdapter extends RecyclerView.Adapter<HistoricoAdapter.MyViewHolder> {

    private List<Historico> mListHistorico;
    //LayoutInflater que vai ser responsavel por capturar o nosso layout inflater de PessoaFragment.
    private LayoutInflater mLayoutInflate;
    private RecycleViewOnClickListenerHack recycleViewOnClickListenerHack;

    public HistoricoAdapter(List<Historico> mListHistorico, Context context){
        this.mListHistorico=mListHistorico;

        /*getSystemService- estamos capturando o layout inflate que criamos no arquivo HistoricoFragment*/
        this.mLayoutInflate= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    //ViewHolder é quem trabalha com o cache de nossa view, vai guardar a view para ser reutilizada
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView tvNomeMed;
        public TextView tvDataConsHist;
        public TextView tvEspecMed;

        public MyViewHolder(View itemView){
            super(itemView);

            tvNomeMed=(TextView) itemView.findViewById(R.id.tvNomeMed);
            tvDataConsHist=(TextView) itemView.findViewById(R.id.tvDataConsHist);
            tvEspecMed=(TextView) itemView.findViewById(R.id.tvEspecMed);

            itemView.setOnClickListener(this);
        }
        @Override
        // responsavel por realizar o evento do clique do nosso item.
        public void onClick(View v){
            if (recycleViewOnClickListenerHack !=null){
                recycleViewOnClickListenerHack.onClickListener(v, getAdapterPosition());
            }
        }

    }
    //metodo que vai excluir o item clicado do nosso recycleView
    public void removeListItem(int position){
        this.mListHistorico.remove(position);
        notifyItemRemoved(position);

    }

    public void setRecycleViewOnClickListenerHack(RecycleViewOnClickListenerHack recycleViewOnClickListenerHack){
        this.recycleViewOnClickListenerHack =recycleViewOnClickListenerHack;

    }

    @Override
    //É onde é carregado o arquivo de layout que representa o item da nossa lista, nesse metodos não preenchemos nenhuma informação da view, apenas criamos as configurações necessarias.
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view =mLayoutInflate.inflate(R.layout.item_consulta_historico,parent,false);
        MyViewHolder myViewHolder =new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    //Esse é o metodo responsavel por preencher os dados na nossa view que sera exibido pelo nosso RecycleView
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvNomeMed.setText(mListHistorico.get(position).getNome());
        holder.tvDataConsHist.setText(mListHistorico.get(position).getData());
        holder.tvEspecMed.setText(mListHistorico.get(position).getEspecialidade());
    }

    @Override
    //Ele é o responsavel por retornar a quantidade de itens que serão exibidos na tela
    public int getItemCount() {
        return mListHistorico.size();
    }
}
