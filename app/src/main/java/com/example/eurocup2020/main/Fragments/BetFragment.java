package com.example.eurocup2020.main.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eurocup2020.R;
import com.example.eurocup2020.log_reg.SessionManager;
import com.example.eurocup2020.main.Mecz.CaptionedImagesAdapter;
import com.example.eurocup2020.main.Mecz.MeczView;
import com.example.eurocup2020.main.Mecz.MatchDetailActivity;
import com.example.eurocup2020.main.Mecz.MeczPresenter;
import com.example.eurocup2020.model.Mecz;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class BetFragment extends Fragment implements MeczView {

    private MeczPresenter presenter; //interfejs

    public static int[] flagi, flagi2, spotkanieId; //tablice do danych z bazy

    private ProgressBar pb;
    private RecyclerView typujR;
    private int pozycja, idpom2;
    private TextView tomorrow, predict;

    public BetFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bet, container,false);
        typujR = view.findViewById(R.id.typujfr);

        //pasek ladowania
        pb=view.findViewById(R.id.loadingBet);

        //do sprawdzenia czy mecz juz postawiony
        SessionManager sessionManager = new SessionManager(Objects.requireNonNull(getActivity()));

        HashMap<String, String> user1 = sessionManager.getUserDetail();
        String ID_USER1 = user1.get(SessionManager.ID_USER);

        //przechowuje id usera
        idpom2=Integer.parseInt(ID_USER1);

        predict=view.findViewById(R.id.predict);
        tomorrow=view.findViewById(R.id.tomorrow);

        //pobieramy dane
        presenter = new MeczPresenter(this);
        presenter.getData(idpom2);

        return view;

    }

    @Override
    public void showLoading() {
    pb.setVisibility(View.VISIBLE);
    typujR.setVisibility(View.GONE);
    tomorrow.setVisibility(View.GONE);
    predict.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
    pb.setVisibility(View.GONE);
    typujR.setVisibility(View.VISIBLE);
    }

    @Override
    public void onGetResult(List<Mecz> meczes) {

        //przypisuje flagi, id do danych z bazy

        flagi = new int[meczes.size()];
        flagi2 = new int[meczes.size()];
        String[] panstwo1 = new String[meczes.size()];
        String[] panstwo2 = new String[meczes.size()];
        spotkanieId = new int[meczes.size()];
        String[] runda = new String[meczes.size()];

        Mecz mecz1;

        for (int i = 0; i < flagi.length; i++) {
            mecz1 = meczes.get(i);
            flagi[i] = dajFlage(mecz1.getGosp());
            flagi2[i] = dajFlage(mecz1.getGosc());
            panstwo1[i]=dajNazwePanstwa(mecz1.getGosp());
            panstwo2[i]=dajNazwePanstwa(mecz1.getGosc());
            spotkanieId[i]=mecz1.getId_meczu();
            runda[i]= mecz1.getGrupa();
        }

        //standardowe zaladowanie adaptera do recyclerview + listener na klikniety mecz
        CaptionedImagesAdapter adapter = new CaptionedImagesAdapter(flagi, flagi2, panstwo1, panstwo2, runda);
        typujR.setAdapter(adapter);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        typujR.setLayoutManager(layoutManager);
        adapter.setListener(new CaptionedImagesAdapter.Listener() {
            @Override
            public void onClick(int position) {
                pozycja=position;
                Intent intent = new Intent(getActivity(), MatchDetailActivity.class);
                intent.putExtra(MatchDetailActivity.EXTRA_MATCH_ID, pozycja);
                Objects.requireNonNull(getActivity()).startActivity(intent);
            }
        });
        if (meczes.size()==0){
            tomorrow.setVisibility(View.VISIBLE);
        } else {
            predict.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(getActivity(), "Connection problem, please try again later", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        pb.setVisibility(View.VISIBLE);
        typujR.setVisibility(View.GONE);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                presenter.getData(idpom2);
            }
        }, 1300);

    }

    //zamienia dane z bazy na konkretna flage
    private int dajFlage(String gg){
        int flag=0;
        switch (gg) {
            case "AUT":
                flag= R.drawable.aut;
                break;
            case "BEL":
                flag= R.drawable.bel;
                break;
            case "CRO":
                flag= R.drawable.cro;
                break;
            case "CZE":
                flag= R.drawable.cze;
                break;
            case "DEN":
                flag= R.drawable.den;
                break;
            case "ENG":
                flag= R.drawable.eng;
                break;
            case "ESP":
                flag= R.drawable.esp;
                break;
            case "FIN":
                flag= R.drawable.fin;
                break;
            case "FRA":
                flag= R.drawable.fra;
                break;
            case "GER":
                flag= R.drawable.ger;
                break;
            case "HUN":
                flag= R.drawable.hun;
                break;
            case "ITA":
                flag= R.drawable.ita;
                break;
            case "MKD":
                flag= R.drawable.mkd;
                break;
            case "NED":
                flag= R.drawable.ned;
                break;
            case "POL":
                flag= R.drawable.pol;
                break;
            case "POR":
                flag= R.drawable.por;
                break;
            case "RUS":
                flag= R.drawable.rus;
                break;
            case "SCO":
                flag= R.drawable.sco;
                break;
            case "SUI":
                flag= R.drawable.sui;
                break;
            case "SVN":
                flag= R.drawable.svn;
                break;
            case "SWE":
                flag= R.drawable.swe;
                break;
            case "TUR":
                flag= R.drawable.tur;
                break;
            case "UKR":
                flag= R.drawable.ukr;
                break;
            case "WAL":
                flag= R.drawable.wal;
                break;
        }
        return flag;
    }

    private String dajNazwePanstwa(String gg){
        String panstwo="";
        switch (gg) {
            case "AUT":
                panstwo= "Austria";
                break;
            case "BEL":
                panstwo= "Belgium";
                break;
            case "CRO":
                panstwo= "Croatia";
                break;
            case "CZE":
                panstwo= "Czech Rep.";
                break;
            case "DEN":
                panstwo= "Denmark";
                break;
            case "ENG":
                panstwo= "England";
                break;
            case "ESP":
                panstwo= "Spain";
                break;
            case "FIN":
                panstwo= "Finland";
                break;
            case "FRA":
                panstwo= "France";
                break;
            case "GER":
                panstwo= "Germany";
                break;
            case "HUN":
                panstwo= "Hungary";
                break;
            case "ITA":
                panstwo= "Italy";
                break;
            case "MKD":
                panstwo= "Macedonia";
                break;
            case "NED":
                panstwo= "Netherlands";
                break;
            case "POL":
                panstwo= "Poland";
                break;
            case "POR":
                panstwo= "Portugal";
                break;
            case "RUS":
                panstwo= "Russia";
                break;
            case "SCO":
                panstwo= "Scotland";
                break;
            case "SUI":
                panstwo= "Switzerland";
                break;
            case "SVN":
                panstwo= "Slovakia";
                break;
            case "SWE":
                panstwo= "Sweden";
                break;
            case "TUR":
                panstwo= "Turkey";
                break;
            case "UKR":
                panstwo= "Ukraine";
                break;
            case "WAL":
                panstwo= "Wales";
                break;
        }
        return panstwo;
    }
}
