package com.example.eurocup2020.main.Mecz;

import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eurocup2020.R;

public class CaptionedImagesAdapter extends RecyclerView.Adapter<CaptionedImagesAdapter.ViewHolder>{


    //tabele z danymi
    private int[] imageIds, imageIds1;
    private String[] nazwa1, nazwa2, grupa;
    private Listener listener;

    public interface Listener{
        void onClick(int position);
    }

    public CaptionedImagesAdapter(int[] imageIds, int[] imageIds1, String[] nazwa1, String[] nazwa2, String[] grupa) {
        this.imageIds = imageIds;
        this.imageIds1 = imageIds1;
        this.nazwa1=nazwa1;
        this.nazwa2=nazwa2;
        this.grupa=grupa;
    }

    @NonNull
    @Override
    public CaptionedImagesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv=(CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_captioned_image, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull CaptionedImagesAdapter.ViewHolder holder, final int position) {
        final CardView cardView = holder.cardView;

        ImageView imageView = cardView.findViewById(R.id.flaga1);
        Drawable drawable = ContextCompat.getDrawable(cardView.getContext(), imageIds[position]);
        imageView.setImageDrawable(drawable);

        ImageView imageView1 = cardView.findViewById(R.id.flaga2);
        Drawable drawable1 = ContextCompat.getDrawable(cardView.getContext(), imageIds1[position]);
        imageView1.setImageDrawable(drawable1);

        TextView nazwaPanstwa1= cardView.findViewById(R.id.nazwa1);
        nazwaPanstwa1.setText(nazwa1[position]);

        TextView nazwaPanstwa2= cardView.findViewById(R.id.nazwa2);
        nazwaPanstwa2.setText(nazwa2[position]);

        TextView nazwaGrupy= cardView.findViewById(R.id.grupa);
        nazwaGrupy.setText(grupa[position]);

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
        return imageIds.length;
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
