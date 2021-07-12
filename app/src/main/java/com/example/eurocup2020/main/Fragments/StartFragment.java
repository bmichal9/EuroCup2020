package com.example.eurocup2020.main.Fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eurocup2020.R;
import com.example.eurocup2020.log_reg.SessionManager;
import com.example.eurocup2020.main.StartActivities.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


/**
 * A simple {@link Fragment} subclass.
 */

public class StartFragment extends  Fragment {

    private TextView txtNumber1, welcome, time1;
    private long diff;
    public SessionManager sessionManager;
    SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy, HH:mm");
    private final String oldTime = formatter.format(new Date());


    public StartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_start, container, false);

        sessionManager= new SessionManager(Objects.requireNonNull(getActivity()));

        HashMap<String, String> user = sessionManager.getUserDetail();
        final String sessionName=user.get(sessionManager.NAME);

        welcome=view.findViewById(R.id.welcom);

        welcome.setText("Welcome "+sessionName+"!");

        //do licznika
        txtNumber1=view.findViewById(R.id.time);
        time1=view.findViewById(R.id.time1);

        final String NewTime = "10.06.2021, 21:01";//Timer date 2
        Date oldDate, newDate;
        try {
            oldDate = formatter.parse(oldTime);
            newDate = formatter.parse(NewTime);
            long oldLong = oldDate.getTime();
            long newLong = newDate.getTime();
            diff = newLong - oldLong;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        MyCount counter = new MyCount(diff, 1000);
        counter.start();
        //koniec licznika


        CardView cardView1 =view.findViewById(R.id.card_1);
        CardView cardView2 =view.findViewById(R.id.card_2);
        CardView cardView3 =view.findViewById(R.id.card_3);

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Howtoplay.class);
                Objects.requireNonNull(getActivity()).startActivity(intent);
            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Next.class);
                Objects.requireNonNull(getActivity()).startActivity(intent);
            }
        });

        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Credits.class);
                Objects.requireNonNull(getActivity()).startActivity(intent);
            }
        });


        return view;

    }


    //do licznika klasa wewnetrzna
    public class MyCount extends CountDownTimer {
        MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        //ustawia napis po
        @Override
        public void onFinish() {
            time1.setVisibility(View.GONE);
            txtNumber1.setVisibility(View.GONE);

        }


        @Override
        public void onTick(long millisUntilFinished) {
            long millis = millisUntilFinished;
            String hms = (TimeUnit.MILLISECONDS.toDays(millis)) + " day/s";
            txtNumber1.setText(hms);
        }
    }

}
