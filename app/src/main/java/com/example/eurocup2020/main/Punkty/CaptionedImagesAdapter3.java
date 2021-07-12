package com.example.eurocup2020.main.Punkty;

import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.eurocup2020.R;

public class CaptionedImagesAdapter3 extends RecyclerView.Adapter<CaptionedImagesAdapter3.ViewHolder>{


    //tabele z danymi
    private String[] gosp, gosc;
    private int[] betGosp, betGosc, wynGosp, wynGosc;
    private Listener listener;

    public interface Listener{
        void onClick(int position);
    }

    public CaptionedImagesAdapter3(String[] gosp, String[] gosc, int[]betGosp, int[] betGosc, int[] wynGosp, int[] wynGosc) {
        this.gosp=gosp;
        this.gosc=gosc;
        this.betGosp=betGosp;
        this.betGosc=betGosc;
        this.wynGosp=wynGosp;
        this.wynGosc=wynGosc;

    }

    @NonNull
    @Override
    public CaptionedImagesAdapter3.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv=(CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_znajomy_detail, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull CaptionedImagesAdapter3.ViewHolder holder, final int position) {
        final CardView cardView = holder.cardView;

        TextView txtgosp = cardView.findViewById(R.id.txtGosp);
        TextView txtgosc = cardView.findViewById(R.id.txtGosc);
        TextView txtbetGosp = cardView.findViewById(R.id.txtBetGosp);
        TextView txtbetGosc = cardView.findViewById(R.id.txtBetGosc);
        TextView txtwynGosp = cardView.findViewById(R.id.txtWynGosp);
        TextView txtwynGosc = cardView.findViewById(R.id.txtWynGosc);

        txtgosp.setText(gosp[position]);
        txtgosc.setText(gosc[position]);
        txtbetGosp.setText(String.valueOf(betGosp[position]));
        txtbetGosc.setText(String.valueOf(betGosc[position]));
        txtwynGosp.setText(String.valueOf(wynGosp[position]));
        txtwynGosc.setText(String.valueOf(wynGosc[position]));

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null){
                    listener.onClick(position);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return gosp.length;
    }

    public void setListener(Listener listener){
        this.listener=listener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private CardView cardView;

        ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }
}
