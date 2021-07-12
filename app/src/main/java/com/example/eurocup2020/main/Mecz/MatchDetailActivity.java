package com.example.eurocup2020.main.Mecz;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.eurocup2020.R;
import com.example.eurocup2020.log_reg.SessionManager;
import com.example.eurocup2020.main.Fragments.BetFragment;

import java.util.HashMap;
import java.util.Objects;

public class MatchDetailActivity extends AppCompatActivity implements AddView{

    //do wyboru meczu ktory user kliknal
    public static final String EXTRA_MATCH_ID="meczId";

    //do id gracza
    public SessionManager sessionManager;

    EditText wyn1, wyn2;
    Button save;
    ProgressBar pbDetail;
    AddPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_detail);

        sessionManager= new SessionManager(this);

        HashMap<String, String> user = sessionManager.getUserDetail();
        final String mIDUSER=user.get(SessionManager.ID_USER);

        final int id_useraInt=Integer.parseInt(mIDUSER);

        //dodajemy toolbar
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //dodaje przycisk wroc do glownego widoku
        ActionBar actionBar=getSupportActionBar();
        Objects.requireNonNull(actionBar).setDisplayHomeAsUpEnabled(true);

        //wyswietlamy flagi pobrane z pol statycznych z betfragment
        int meczId=(Integer)getIntent().getExtras().get(EXTRA_MATCH_ID);

        //flaga1
        final int flagaImage1= BetFragment.flagi[meczId];
        ImageView imgWyn1=findViewById(R.id.flagaDetail1);
        imgWyn1.setImageDrawable(ContextCompat.getDrawable(this, flagaImage1));

        //flaga2
        final int flagaImage2= BetFragment.flagi2[meczId];
        ImageView imgWyn2=findViewById(R.id.flagaDetail2);
        imgWyn2.setImageDrawable(ContextCompat.getDrawable(this, flagaImage2));

        //id meczu
        final int idtest=BetFragment.spotkanieId[meczId];

        wyn1=findViewById(R.id.wynGosp);
        wyn2=findViewById(R.id.wynGosc);
        save=findViewById(R.id.btn_score);
        pbDetail=findViewById(R.id.loadingDetail);

        presenter = new AddPresenter(this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String spr1 = wyn1.getText().toString();
                String spr2 = wyn2.getText().toString();

                if ((spr1.matches(""))||(spr2.matches(""))) {
                    Toast.makeText(MatchDetailActivity.this, "Please put the score for both teams", Toast.LENGTH_SHORT).show();
                } else {

                    final int gosp = Integer.parseInt(wyn1.getText().toString());
                    final int gosc = Integer.parseInt(wyn2.getText().toString());

                    AlertDialog.Builder builder = new AlertDialog.Builder(MatchDetailActivity.this);
                    builder.setCancelable(true);
                    builder.setIcon(R.drawable.ic_action_confirm);
                    builder.setTitle(dajKraj(flagaImage1) + " " + spr1 + " : " + spr2 + " " + dajKraj(flagaImage2));
                    builder.setMessage("Please confirm the score");
                    builder.setPositiveButton("Confirm",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    presenter.saveMecz(id_useraInt, idtest, dajKraj(flagaImage1), dajKraj(flagaImage2), gosp, gosc);
                                    finish();
                                }
                            });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MatchDetailActivity.this, "Score not saved", Toast.LENGTH_SHORT).show();
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
             }
        });

    }

    @Override
    public void showProgress() {
        pbDetail.setVisibility(View.VISIBLE);
        save.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        pbDetail.setVisibility(View.GONE);
    }

    @Override
    public void onAddSuccess(String message) {
        Toast.makeText(MatchDetailActivity.this, "Score saved!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAddError(String message) {
        Toast.makeText(MatchDetailActivity.this, "Connection problem, please try again later", Toast.LENGTH_SHORT).show();
    }

    String dajKraj (int gg){
        String flag="";
        switch (gg) {
            case R.drawable.aut:
                flag="AUT";
                break;
            case R.drawable.bel:
                flag="BEL";
                break;
            case R.drawable.cro:
                flag="CRO";
                break;
            case R.drawable.cze:
                flag="CZE";
                break;
            case R.drawable.den:
                flag="DEN";
                break;
            case R.drawable.eng:
                flag="ENG";
                break;
            case R.drawable.esp:
                flag="ESP";
                break;
            case R.drawable.fin:
                flag="FIN";
                break;
            case R.drawable.fra:
                flag="FRA";
                break;
            case R.drawable.ger:
                flag="GER";
                break;
            case R.drawable.hun:
                flag="HUN";
                break;
            case R.drawable.ita:
                flag="ITA";
                break;
            case R.drawable.mkd:
                flag="MKD";
                break;
            case R.drawable.ned:
                flag="NED";
                break;
            case R.drawable.pol:
                flag="POL";
                break;
            case R.drawable.por:
                flag="POR";
                break;
            case R.drawable.rus:
                flag="RUS";
                break;
            case R.drawable.sco:
                flag="SCO";
                break;
            case R.drawable.sui:
                flag="SUI";
                break;
            case R.drawable.svn:
                flag="SVN";
                break;
            case R.drawable.swe:
                flag="SWE";
                break;
            case R.drawable.tur:
                flag="TUR";
                break;
            case R.drawable.ukr:
                flag="UKR";
                break;
            case R.drawable.wal:
                flag="WAL";
                break;

        }
        return flag;
    }
}
