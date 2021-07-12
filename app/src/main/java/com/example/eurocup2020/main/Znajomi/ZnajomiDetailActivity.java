package com.example.eurocup2020.main.Znajomi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eurocup2020.R;
import com.example.eurocup2020.main.Fragments.FriendFragment;
import com.example.eurocup2020.main.Punkty.CaptionedImagesAdapter3;
import com.example.eurocup2020.main.Punkty.PunktyPresenter;
import com.example.eurocup2020.main.Punkty.PunktyView;
import com.example.eurocup2020.model.Punkty;

import java.util.List;
import java.util.Objects;

public class ZnajomiDetailActivity extends AppCompatActivity implements PunktyView {

    //pozycja znajomego - potrzebne do listenera
    public static final String EXTRA_ZNAJOMI_ID="znajomiId";

    //widget
    TextView txtTotal, txtNameFriend;

    //standard
    RecyclerView recyclerView;

    //interfejs
    PunktyPresenter presenter8;

    //loading
    ProgressBar pbLista;

    //do odebrania danych z bazy
    List<Punkty> listaMeczy;

    //tablice na dane z bazy
    String [] gosp, gosc;
    int[] wynGosp, wynGosc, betGosp, betGosc, punktyZaMecz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_znajomi_detail);

        //dodajemy toolbar
        Toolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //dodaje przycisk wroc do glownego widoku
        ActionBar actionBar=getSupportActionBar();
        Objects.requireNonNull(actionBar).setDisplayHomeAsUpEnabled(true);

        //do listenera znajomego
        int znajomiId=(Integer)getIntent().getExtras().get(EXTRA_ZNAJOMI_ID);

        //do wysylania zadania do bd
        String player= FriendFragment.znajomydetail[znajomiId];
        int points=FriendFragment.punkty[znajomiId];

        //standard z xmla
        txtNameFriend=findViewById(R.id.friendname);
        txtNameFriend.setText(player);

        txtTotal=findViewById(R.id.totalscore);
        txtTotal.setText("Total points: "+ points);

        pbLista=findViewById(R.id.loadingMatchList);

        recyclerView=findViewById(R.id.matchlist11);

        //pobieramy dane
        presenter8=new PunktyPresenter(this);
        presenter8.getListePunktow(player);

    }

    @Override
    public void showLoading() {
        pbLista.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        pbLista.setVisibility(View.GONE);
    }

    @Override
    public void onGetResult(List<Punkty> punkties) {

        //lista dosc zlozona stad duzo tablic
        listaMeczy=punkties;

        gosp=new String[listaMeczy.size()];
        gosc=new String[listaMeczy.size()];
        betGosp=new int[listaMeczy.size()];
        betGosc=new int[listaMeczy.size()];
        wynGosc=new int[listaMeczy.size()];
        wynGosp=new int[listaMeczy.size()];
        punktyZaMecz=new int[listaMeczy.size()];

        Punkty pom55;

        //przypisanie danych
        for (int i = 0; i < gosp.length; i++) {

            pom55 = listaMeczy.get(i);

            gosp[i]=pom55.getGosp();
            gosc[i]=pom55.getGosc();
            betGosp[i]=pom55.getBet_gosp();
            betGosc[i]=pom55.getBet_gosc();
            wynGosp[i]=pom55.getWyn_gosp();
            wynGosc[i]=pom55.getWyn_gosc();
            punktyZaMecz[i]=pom55.getPunkty();
        }

        //standard
        CaptionedImagesAdapter3 adapter = new CaptionedImagesAdapter3(gosp, gosc, betGosp, betGosc, wynGosp, wynGosc);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter.setListener(new CaptionedImagesAdapter3.Listener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(getApplicationContext(), punktyZaMecz[position]+" points", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this, "Connection problem, please try again later", Toast.LENGTH_SHORT).show();
    }
}
