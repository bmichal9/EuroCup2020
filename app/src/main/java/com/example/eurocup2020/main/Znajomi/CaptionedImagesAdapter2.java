package com.example.eurocup2020.main.Znajomi;

import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.eurocup2020.R;

public class CaptionedImagesAdapter2 extends RecyclerView.Adapter<CaptionedImagesAdapter2.ViewHolder>{


    //tabele z danymi
    private String[] listaznajomych;
    private int[] punkty, miejsce;
    private Listener listener;

    public interface Listener{
        void onClick(int position);
    }

    public CaptionedImagesAdapter2(String[] listaznajomych, int[] punkty, int[] miejsce) {
        this.listaznajomych = listaznajomych;
        this.punkty = punkty;
        this.miejsce=miejsce;
    }

    @NonNull
    @Override
    public CaptionedImagesAdapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv=(CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_captioned_image2, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull CaptionedImagesAdapter2.ViewHolder holder, final int position) {
        final CardView cardView = holder.cardView;

        TextView znajomy = cardView.findViewById(R.id.znajomy);
        znajomy.setText(listaznajomych[position]);

        TextView punkty1 = cardView.findViewById(R.id.punkty);
        punkty1.setText(String.valueOf(punkty[position]));

        TextView miejsce1 = cardView.findViewById(R.id.miejsce);
        miejsce1.setText(String.valueOf(miejsce[position]));

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
        return listaznajomych.length;
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
