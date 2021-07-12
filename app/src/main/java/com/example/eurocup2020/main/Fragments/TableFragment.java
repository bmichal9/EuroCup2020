package com.example.eurocup2020.main.Fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import com.example.eurocup2020.main.Tabela.TabelaPresenter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eurocup2020.R;
import com.example.eurocup2020.main.Tabela.TabelaView;
import com.example.eurocup2020.model.Druzyna;
import com.example.eurocup2020.model.Grupa;
import com.google.android.material.button.MaterialButtonToggleGroup;

import java.util.List;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class TableFragment extends Fragment implements TabelaView {

    //do togglebutton
    private MaterialButtonToggleGroup materialButtonToggleGroup2, materialButtonToggleGroup1;
    private LinearLayout cl, cl2;

    private TabelaPresenter presenter; //interfejs

    //do danych z bazy
    private int[] tabwyngosp, tabwyngosc, tabid;
    private String[] tabgosp, tabgosc;

    //loading
    private ProgressBar tabpb;

    //Wszystkie napisy, obrazki na layoucie (matchday1,2,3)
    //t1-t12 to sa nazwy druzyn w meczach grupy, i1-i12 to sa flagi w meczach grupy, w to wyniki
    private TextView t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12;
    private ImageView i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12;
    private TextView w1, w2, w3, w4, w5, w6, w7, w8, w9, w10, w11, w12;

    //napisy flagi wyniki do knockout phase
    private TextView k1, k2, k3, k4, k5, k6, k7, k8, k9, k10, k11, k12, k13, k14, k15, k16, k17, k18, k19 ,k20, k21, k22, k23, k24, k25, k26, k27, k28, k29, k30;
    private ImageView g1, g2, g3, g4, g5, g6, g7, g8, g9, g10, g11, g12, g13, g14, g15, g16, g17, g18, g19, g20, g21, g22, g23, g24, g25, g26, g27, g28, g29, g30;
    private TextView a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20, a21, a22, a23, a24, a25, a26, a27, a28, a29, a30;


    //do tabelek
    private Druzyna[] tabelki;
    private Druzyna d1, d2, d3, d4;
    private TextView p1, p2, p3, p4, win1, win2, win3, win4, draw1, draw2, draw3, draw4, l1, l2, l3, l4, pts1, pts2, pts3, pts4;
    private TextView kraj1,kraj2,kraj3,kraj4;
    private ImageView flaga1,flaga2,flaga3,flaga4;
    private  TextView nazwaGrupy;

    //pomoc do tej samej liczby goli
    private Druzyna[] tabelki2;

    //zamiana 99 na puste
    private String[] tabpom1, tabpom2;

    //pojdz na gore widoku
    private NestedScrollView nestedScrollView, nestedScrollView2;

    public TableFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_table, container, false);

        //przypisanie kontenerow z layouta
        cl = view.findViewById(R.id.grupyyyyy);
        tabpb = view.findViewById(R.id.tab_pb);
        nestedScrollView=view.findViewById(R.id.nsv11);
        nestedScrollView2=view.findViewById(R.id.nsv22);
        cl2=view.findViewById(R.id.kophase);
        nazwaGrupy=view.findViewById(R.id.tabgrupa);

        //do grup
        tabelki = new Druzyna[4];

        //do toggle button
        materialButtonToggleGroup1 = view.findViewById(R.id.toggleButton1);
        materialButtonToggleGroup2 = view.findViewById(R.id.toggleButton2);

        //pobieramy dane
        presenter = new TabelaPresenter(this);
        presenter.getData();

        t1 = view.findViewById(R.id.d1kraj1);
        t2 = view.findViewById(R.id.d1kraj2);
        t3 = view.findViewById(R.id.d1kraj3);
        t4 = view.findViewById(R.id.d1kraj4);
        t5 = view.findViewById(R.id.d2kraj1);
        t6 = view.findViewById(R.id.d2kraj2);
        t7 = view.findViewById(R.id.d2kraj3);
        t8 = view.findViewById(R.id.d2kraj4);
        t9 = view.findViewById(R.id.d3kraj1);
        t10 = view.findViewById(R.id.d3kraj2);
        t11 = view.findViewById(R.id.d3kraj3);
        t12 = view.findViewById(R.id.d3kraj4);

        i1 = view.findViewById(R.id.d1flaga1);
        i2 = view.findViewById(R.id.d1flaga2);
        i3 = view.findViewById(R.id.d1flaga3);
        i4 = view.findViewById(R.id.d1flaga4);
        i5 = view.findViewById(R.id.d2flaga1);
        i6 = view.findViewById(R.id.d2flaga2);
        i7 = view.findViewById(R.id.d2flaga3);
        i8 = view.findViewById(R.id.d2flaga4);
        i9 = view.findViewById(R.id.d3flaga1);
        i10 = view.findViewById(R.id.d3flaga2);
        i11 = view.findViewById(R.id.d3flaga3);
        i12 = view.findViewById(R.id.d3flaga4);

        w1 = view.findViewById(R.id.d1wyn1);
        w2 = view.findViewById(R.id.d1wyn2);
        w3 = view.findViewById(R.id.d1wyn3);
        w4 = view.findViewById(R.id.d1wyn4);
        w5 = view.findViewById(R.id.d2wyn1);
        w6 = view.findViewById(R.id.d2wyn2);
        w7 = view.findViewById(R.id.d2wyn3);
        w8 = view.findViewById(R.id.d2wyn4);
        w9 = view.findViewById(R.id.d3wyn1);
        w10 = view.findViewById(R.id.d3wyn2);
        w11 = view.findViewById(R.id.d3wyn3);
        w12 = view.findViewById(R.id.d3wyn4);

        p1 = view.findViewById(R.id.tabplayed1);
        p2 = view.findViewById(R.id.tabplayed2);
        p3 = view.findViewById(R.id.tabplayed3);
        p4 = view.findViewById(R.id.tabplayed4);

        win1 = view.findViewById(R.id.tabwin1);
        win2 = view.findViewById(R.id.tabwin2);
        win3 = view.findViewById(R.id.tabwin3);
        win4 = view.findViewById(R.id.tabwin4);

        draw1 = view.findViewById(R.id.tabdraw1);
        draw2 = view.findViewById(R.id.tabdraw2);
        draw3 = view.findViewById(R.id.tabdraw3);
        draw4 = view.findViewById(R.id.tabdraw4);

        l1 = view.findViewById(R.id.tablose1);
        l2 = view.findViewById(R.id.tablose2);
        l3 = view.findViewById(R.id.tablose3);
        l4 = view.findViewById(R.id.tablose4);

        pts1 = view.findViewById(R.id.tabpts1);
        pts2 = view.findViewById(R.id.tabpts2);
        pts3 = view.findViewById(R.id.tabpts3);
        pts4 = view.findViewById(R.id.tabpts4);

        kraj1=view.findViewById(R.id.tabkraj1);
        kraj2=view.findViewById(R.id.tabkraj2);
        kraj3=view.findViewById(R.id.tabkraj3);
        kraj4=view.findViewById(R.id.tabkraj4);

        flaga1=view.findViewById(R.id.tabflaga1);
        flaga2=view.findViewById(R.id.tabflaga2);
        flaga3=view.findViewById(R.id.tabflaga3);
        flaga4=view.findViewById(R.id.tabflaga4);

        k1=view.findViewById(R.id.r16kraj1);
        k2=view.findViewById(R.id.r16kraj2);
        k3=view.findViewById(R.id.r16kraj3);
        k4=view.findViewById(R.id.r16kraj4);
        k5=view.findViewById(R.id.r16kraj5);
        k6=view.findViewById(R.id.r16kraj6);
        k7=view.findViewById(R.id.r16kraj7);
        k8=view.findViewById(R.id.r16kraj8);
        k9=view.findViewById(R.id.r16kraj9);
        k10=view.findViewById(R.id.r16kraj10);
        k11=view.findViewById(R.id.r16kraj11);
        k12=view.findViewById(R.id.r16kraj12);
        k13=view.findViewById(R.id.r16kraj13);
        k14=view.findViewById(R.id.r16kraj14);
        k15=view.findViewById(R.id.r16kraj15);
        k16=view.findViewById(R.id.r16kraj16);
        k17=view.findViewById(R.id.qfkraj1);
        k18=view.findViewById(R.id.qfkraj2);
        k19=view.findViewById(R.id.qfkraj3);
        k20=view.findViewById(R.id.qfkraj4);
        k21=view.findViewById(R.id.qfkraj5);
        k22=view.findViewById(R.id.qfkraj6);
        k23=view.findViewById(R.id.qfkraj7);
        k24=view.findViewById(R.id.qfkraj8);
        k25=view.findViewById(R.id.sfkraj1);
        k26=view.findViewById(R.id.sfkraj2);
        k27=view.findViewById(R.id.sfkraj3);
        k28=view.findViewById(R.id.sfkraj4);
        k29=view.findViewById(R.id.fkraj1);
        k30=view.findViewById(R.id.fkraj2);

        a1=view.findViewById(R.id.r16wyn1);
        a2=view.findViewById(R.id.r16wyn2);
        a3=view.findViewById(R.id.r16wyn3);
        a4=view.findViewById(R.id.r16wyn4);
        a5=view.findViewById(R.id.r16wyn5);
        a6=view.findViewById(R.id.r16wyn6);
        a7=view.findViewById(R.id.r16wyn7);
        a8=view.findViewById(R.id.r16wyn8);
        a9=view.findViewById(R.id.r16wyn9);
        a10=view.findViewById(R.id.r16wyn10);
        a11=view.findViewById(R.id.r16wyn11);
        a12=view.findViewById(R.id.r16wyn12);
        a13=view.findViewById(R.id.r16wyn13);
        a14=view.findViewById(R.id.r16wyn14);
        a15=view.findViewById(R.id.r16wyn15);
        a16=view.findViewById(R.id.r16wyn16);
        a17=view.findViewById(R.id.qfwyn1);
        a18=view.findViewById(R.id.qfwyn2);
        a19=view.findViewById(R.id.qfwyn3);
        a20=view.findViewById(R.id.qfwyn4);
        a21=view.findViewById(R.id.qfwyn5);
        a22=view.findViewById(R.id.qfwyn6);
        a23=view.findViewById(R.id.qfwyn7);
        a24=view.findViewById(R.id.qfwyn8);
        a25=view.findViewById(R.id.sfwyn1);
        a26=view.findViewById(R.id.sfwyn2);
        a27=view.findViewById(R.id.sfwyn3);
        a28=view.findViewById(R.id.sfwyn4);
        a29=view.findViewById(R.id.fwyn1);
        a30=view.findViewById(R.id.fwyn2);

        g1=view.findViewById(R.id.r16flaga1);
        g2=view.findViewById(R.id.r16flaga2);
        g3=view.findViewById(R.id.r16flaga3);
        g4=view.findViewById(R.id.r16flaga4);
        g5=view.findViewById(R.id.r16flaga5);
        g6=view.findViewById(R.id.r16flaga6);
        g7=view.findViewById(R.id.r16flaga7);
        g8=view.findViewById(R.id.r16flaga8);
        g9=view.findViewById(R.id.r16flaga9);
        g10=view.findViewById(R.id.r16flaga10);
        g11=view.findViewById(R.id.r16flaga11);
        g12=view.findViewById(R.id.r16flaga12);
        g13=view.findViewById(R.id.r16flaga13);
        g14=view.findViewById(R.id.r16flaga14);
        g15=view.findViewById(R.id.r16flaga15);
        g16=view.findViewById(R.id.r16flaga16);
        g17=view.findViewById(R.id.qfflaga1);
        g18=view.findViewById(R.id.qfflaga2);
        g19=view.findViewById(R.id.qfflaga3);
        g20=view.findViewById(R.id.qfflaga4);
        g21=view.findViewById(R.id.qfflaga5);
        g22=view.findViewById(R.id.qfflaga6);
        g23=view.findViewById(R.id.qfflaga7);
        g24=view.findViewById(R.id.qfflaga8);
        g25=view.findViewById(R.id.sfflaga1);
        g26=view.findViewById(R.id.sfflaga2);
        g27=view.findViewById(R.id.sfflaga3);
        g28=view.findViewById(R.id.sfflaga4);
        g29=view.findViewById(R.id.fflaga1);
        g30=view.findViewById(R.id.fflaga2);

        //listener toggle button
        materialButtonToggleGroup1.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                if (isChecked) {
                    if (checkedId == R.id.button1) {
                        materialButtonToggleGroup2.clearChecked();

                        cl2.setVisibility(View.GONE);

                        d1 = new Druzyna("TUR", 0, 0, 0, 0, 0, 0);
                        d2 = new Druzyna("ITA", 0, 0, 0, 0, 0, 0);
                        d3 = new Druzyna("WAL", 0, 0, 0, 0, 0, 0);
                        d4 = new Druzyna("SUI", 0, 0, 0, 0, 0, 0);

                        if(tabwyngosp[0]!=99){
                            if(tabwyngosp[0]>tabwyngosc[0]){
                                d1.wygral();
                                d2.przegral();
                            } else {
                                if(tabwyngosp[0]<tabwyngosc[0]){
                                    d1.przegral();
                                    d2.wygral();
                                } else {
                                    d1.remis();
                                    d2.remis();
                                }
                            }
                        }

                        if(tabwyngosp[1]!=99){
                            if(tabwyngosp[1]>tabwyngosc[1]){
                                d3.wygral();
                                d4.przegral();
                            } else {
                                if(tabwyngosp[1]<tabwyngosc[1]){
                                    d3.przegral();
                                    d4.wygral();
                                } else {
                                    d3.remis();
                                    d4.remis();
                                }
                            }
                        }

                        if(tabwyngosp[2]!=99){
                            if(tabwyngosp[2]>tabwyngosc[2]){
                                d2.wygral();
                                d4.przegral();
                            } else {
                                if(tabwyngosp[2]<tabwyngosc[2]){
                                    d2.przegral();
                                    d4.wygral();
                                } else {
                                    d2.remis();
                                    d4.remis();
                                }
                            }
                        }

                        if(tabwyngosp[3]!=99){
                            if(tabwyngosp[3]>tabwyngosc[3]){
                                d1.wygral();
                                d3.przegral();
                            } else {
                                if(tabwyngosp[3]<tabwyngosc[3]){
                                    d1.przegral();
                                    d3.wygral();
                                } else {
                                    d1.remis();
                                    d3.remis();
                                }
                            }
                        }

                        if(tabwyngosp[4]!=99){
                            if(tabwyngosp[4]>tabwyngosc[4]){
                                d4.wygral();
                                d1.przegral();
                            } else {
                                if(tabwyngosp[4]<tabwyngosc[4]){
                                    d4.przegral();
                                    d1.wygral();
                                } else {
                                    d4.remis();
                                    d1.remis();
                                }
                            }
                        }

                        if(tabwyngosp[5]!=99){
                            if(tabwyngosp[5]>tabwyngosc[5]){
                                d2.wygral();
                                d3.przegral();
                            } else {
                                if(tabwyngosp[4]<tabwyngosc[5]){
                                    d2.przegral();
                                    d3.wygral();
                                } else {
                                    d2.remis();
                                    d3.remis();
                                }
                            }
                        }

                        d1.setGole(tabwyngosp[51]);
                        d2.setGole(tabwyngosc[51]);
                        d3.setGole(tabwyngosp[52]);
                        d4.setGole(tabwyngosc[52]);

                        tabelki[0]=d1;
                        tabelki[1]=d2;
                        tabelki[2]=d3;
                        tabelki[3]=d4;

                        bubbleSort(tabelki);

                        if(tabelki[3].getPoints()==tabelki[2].getPoints()){

                            tabelki2=new Druzyna[2];

                            tabelki2[0]=tabelki[3];
                            tabelki2[1]=tabelki[2];

                            bubbleSort2(tabelki2);

                            tabelki[3]=tabelki2[1];
                            tabelki[2]=tabelki2[0];
                        }

                        if(tabelki[2].getPoints()==tabelki[1].getPoints()){

                            tabelki2=new Druzyna[2];

                            tabelki2[0]=tabelki[2];
                            tabelki2[1]=tabelki[1];

                            bubbleSort2(tabelki2);

                            tabelki[2]=tabelki2[1];
                            tabelki[1]=tabelki2[0];
                        }

                        if(tabelki[1].getPoints()==tabelki[0].getPoints()){

                            tabelki2=new Druzyna[2];

                            tabelki2[0]=tabelki[1];
                            tabelki2[1]=tabelki[0];

                            bubbleSort2(tabelki2);

                            tabelki[1]=tabelki2[1];
                            tabelki[0]=tabelki2[0];
                        }

                        if((tabelki[3].getPoints()==tabelki[2].getPoints())&&(tabelki[2].getPoints()==tabelki[1].getPoints())){

                            tabelki2=new Druzyna[3];

                            tabelki2[0]=tabelki[3];
                            tabelki2[1]=tabelki[2];
                            tabelki2[2]=tabelki[1];

                            bubbleSort2(tabelki2);

                            tabelki[3]=tabelki2[2];
                            tabelki[2]=tabelki2[1];
                            tabelki[1]=tabelki2[0];

                        }

                        if((tabelki[2].getPoints()==tabelki[1].getPoints())&&(tabelki[1].getPoints()==tabelki[0].getPoints())){

                            tabelki2=new Druzyna[3];

                            tabelki2[0]=tabelki[2];
                            tabelki2[1]=tabelki[1];
                            tabelki2[2]=tabelki[0];

                            bubbleSort2(tabelki2);

                            tabelki[2]=tabelki2[2];
                            tabelki[1]=tabelki2[1];
                            tabelki[0]=tabelki2[0];

                        }

                        if(((tabelki[3].getPoints()==tabelki[2].getPoints())&&(tabelki[2].getPoints()==tabelki[1].getPoints()))&&(tabelki[1].getPoints()==tabelki[0].getPoints())) {

                            bubbleSort2(tabelki);

                        }

                        nazwaGrupy.setText("GROUP A");

                        kraj1.setText(dajNazwePanstwa(tabelki[3].getName()));
                        kraj2.setText(dajNazwePanstwa(tabelki[2].getName()));
                        kraj3.setText(dajNazwePanstwa(tabelki[1].getName()));
                        kraj4.setText(dajNazwePanstwa(tabelki[0].getName()));

                        Drawable drawing1 = ContextCompat.getDrawable(Objects.requireNonNull(getContext()), dajFlage(tabelki[3].getName()));
                        flaga1.setImageDrawable(drawing1);
                        Drawable drawing2 = ContextCompat.getDrawable(getContext(), dajFlage(tabelki[2].getName()));
                        flaga2.setImageDrawable(drawing2);
                        Drawable drawing3 = ContextCompat.getDrawable(getContext(), dajFlage(tabelki[1].getName()));
                        flaga3.setImageDrawable(drawing3);
                        Drawable drawing4 = ContextCompat.getDrawable(getContext(), dajFlage(tabelki[0].getName()));
                        flaga4.setImageDrawable(drawing4);

                        p1.setText(String.valueOf(tabelki[3].getPlayed()));
                        p2.setText(String.valueOf(tabelki[2].getPlayed()));
                        p3.setText(String.valueOf(tabelki[1].getPlayed()));
                        p4.setText(String.valueOf(tabelki[0].getPlayed()));

                        win1.setText(String.valueOf(tabelki[3].getWin()));
                        win2.setText(String.valueOf(tabelki[2].getWin()));
                        win3.setText(String.valueOf(tabelki[1].getWin()));
                        win4.setText(String.valueOf(tabelki[0].getWin()));

                        draw1.setText(String.valueOf(tabelki[3].getDraw()));
                        draw2.setText(String.valueOf(tabelki[2].getDraw()));
                        draw3.setText(String.valueOf(tabelki[1].getDraw()));
                        draw4.setText(String.valueOf(tabelki[0].getDraw()));

                        l1.setText(String.valueOf(tabelki[3].getLost()));
                        l2.setText(String.valueOf(tabelki[2].getLost()));
                        l3.setText(String.valueOf(tabelki[1].getLost()));
                        l4.setText(String.valueOf(tabelki[0].getLost()));

                        pts1.setText(String.valueOf(tabelki[3].getPoints()));
                        pts2.setText(String.valueOf(tabelki[2].getPoints()));
                        pts3.setText(String.valueOf(tabelki[1].getPoints()));
                        pts4.setText(String.valueOf(tabelki[0].getPoints()));

                        t1.setText(tabgosp[0]);
                        t2.setText(tabgosc[0]);
                        t3.setText(tabgosp[1]);
                        t4.setText(tabgosc[1]);
                        t5.setText(tabgosp[2]);
                        t6.setText(tabgosc[2]);
                        t7.setText(tabgosp[3]);
                        t8.setText(tabgosc[3]);
                        t9.setText(tabgosp[4]);
                        t10.setText(tabgosc[4]);
                        t11.setText(tabgosp[5]);
                        t12.setText(tabgosc[5]);

                        w1.setText(tabpom1[0]);
                        w2.setText(tabpom2[0]);
                        w3.setText(tabpom1[1]);
                        w4.setText(tabpom2[1]);
                        w5.setText(tabpom1[2]);
                        w6.setText(tabpom2[2]);
                        w7.setText(tabpom1[3]);
                        w8.setText(tabpom2[3]);
                        w9.setText(tabpom1[4]);
                        w10.setText(tabpom2[4]);
                        w11.setText(tabpom1[5]);
                        w12.setText(tabpom2[5]);

                        Drawable drawable1 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosp[0]));
                        i1.setImageDrawable(drawable1);
                        Drawable drawable2 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosc[0]));
                        i2.setImageDrawable(drawable2);
                        Drawable drawable3 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosp[1]));
                        i3.setImageDrawable(drawable3);
                        Drawable drawable4 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosc[1]));
                        i4.setImageDrawable(drawable4);
                        Drawable drawable5 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosp[2]));
                        i5.setImageDrawable(drawable5);
                        Drawable drawable6 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosc[2]));
                        i6.setImageDrawable(drawable6);
                        Drawable drawable7 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosp[3]));
                        i7.setImageDrawable(drawable7);
                        Drawable drawable8 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosc[3]));
                        i8.setImageDrawable(drawable8);
                        Drawable drawable9 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosp[4]));
                        i9.setImageDrawable(drawable9);
                        Drawable drawable10 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosc[4]));
                        i10.setImageDrawable(drawable10);
                        Drawable drawable11 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosp[5]));
                        i11.setImageDrawable(drawable11);
                        Drawable drawable12 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosc[5]));
                        i12.setImageDrawable(drawable12);

                        nestedScrollView.post(new Runnable() {
                            public void run() {
                                nestedScrollView.fullScroll(nestedScrollView.FOCUS_UP);
                            }
                        });

                        cl.setVisibility(View.VISIBLE);

                    }
                    if (checkedId == R.id.button2) {
                        materialButtonToggleGroup2.clearChecked();

                        cl2.setVisibility(View.GONE);

                        d1 = new Druzyna("BEL", 0, 0, 0, 0, 0, 0);
                        d2 = new Druzyna("RUS", 0, 0, 0, 0, 0, 0);
                        d3 = new Druzyna("DEN", 0, 0, 0, 0, 0, 0);
                        d4 = new Druzyna("FIN", 0, 0, 0, 0, 0, 0);

                        if(tabwyngosp[6]!=99){
                            if(tabwyngosp[6]>tabwyngosc[6]){
                                d1.wygral();
                                d2.przegral();
                            } else {
                                if(tabwyngosp[6]<tabwyngosc[6]){
                                    d1.przegral();
                                    d2.wygral();
                                } else {
                                    d1.remis();
                                    d2.remis();
                                }
                            }
                        }

                        if(tabwyngosp[7]!=99){
                            if(tabwyngosp[7]>tabwyngosc[7]){
                                d3.wygral();
                                d4.przegral();
                            } else {
                                if(tabwyngosp[7]<tabwyngosc[7]){
                                    d3.przegral();
                                    d4.wygral();
                                } else {
                                    d3.remis();
                                    d4.remis();
                                }
                            }
                        }

                        if(tabwyngosp[8]!=99){
                            if(tabwyngosp[8]>tabwyngosc[8]){
                                d4.wygral();
                                d2.przegral();
                            } else {
                                if(tabwyngosp[8]<tabwyngosc[8]){
                                    d4.przegral();
                                    d2.wygral();
                                } else {
                                    d4.remis();
                                    d2.remis();
                                }
                            }
                        }

                        if(tabwyngosp[9]!=99){
                            if(tabwyngosp[9]>tabwyngosc[9]){
                                d3.wygral();
                                d1.przegral();
                            } else {
                                if(tabwyngosp[9]<tabwyngosc[9]){
                                    d3.przegral();
                                    d1.wygral();
                                } else {
                                    d3.remis();
                                    d1.remis();
                                }
                            }
                        }

                        if(tabwyngosp[10]!=99){
                            if(tabwyngosp[10]>tabwyngosc[10]){
                                d2.wygral();
                                d3.przegral();
                            } else {
                                if(tabwyngosp[10]<tabwyngosc[10]){
                                    d2.przegral();
                                    d3.wygral();
                                } else {
                                    d2.remis();
                                    d3.remis();
                                }
                            }
                        }

                        if(tabwyngosp[11]!=99){
                            if(tabwyngosp[11]>tabwyngosc[11]){
                                d4.wygral();
                                d1.przegral();
                            } else {
                                if(tabwyngosp[11]<tabwyngosc[11]){
                                    d4.przegral();
                                    d1.wygral();
                                } else {
                                    d4.remis();
                                    d1.remis();
                                }
                            }
                        }

                        d1.setGole(tabwyngosp[53]);
                        d2.setGole(tabwyngosc[53]);
                        d3.setGole(tabwyngosp[54]);
                        d4.setGole(tabwyngosc[54]);

                        tabelki[0]=d1;
                        tabelki[1]=d2;
                        tabelki[2]=d3;
                        tabelki[3]=d4;

                        bubbleSort(tabelki);

                        if(tabelki[3].getPoints()==tabelki[2].getPoints()){

                            tabelki2=new Druzyna[2];

                            tabelki2[0]=tabelki[3];
                            tabelki2[1]=tabelki[2];

                            bubbleSort2(tabelki2);

                            tabelki[3]=tabelki2[1];
                            tabelki[2]=tabelki2[0];
                        }

                        if(tabelki[2].getPoints()==tabelki[1].getPoints()){

                            tabelki2=new Druzyna[2];

                            tabelki2[0]=tabelki[2];
                            tabelki2[1]=tabelki[1];

                            bubbleSort2(tabelki2);

                            tabelki[2]=tabelki2[1];
                            tabelki[1]=tabelki2[0];
                        }

                        if(tabelki[1].getPoints()==tabelki[0].getPoints()){

                            tabelki2=new Druzyna[2];

                            tabelki2[0]=tabelki[1];
                            tabelki2[1]=tabelki[0];

                            bubbleSort2(tabelki2);

                            tabelki[1]=tabelki2[1];
                            tabelki[0]=tabelki2[0];
                        }

                        if((tabelki[3].getPoints()==tabelki[2].getPoints())&&(tabelki[2].getPoints()==tabelki[1].getPoints())){

                            tabelki2=new Druzyna[3];

                            tabelki2[0]=tabelki[3];
                            tabelki2[1]=tabelki[2];
                            tabelki2[2]=tabelki[1];

                            bubbleSort2(tabelki2);

                            tabelki[3]=tabelki2[2];
                            tabelki[2]=tabelki2[1];
                            tabelki[1]=tabelki2[0];

                        }

                        if((tabelki[2].getPoints()==tabelki[1].getPoints())&&(tabelki[1].getPoints()==tabelki[0].getPoints())){

                            tabelki2=new Druzyna[3];

                            tabelki2[0]=tabelki[2];
                            tabelki2[1]=tabelki[1];
                            tabelki2[2]=tabelki[0];

                            bubbleSort2(tabelki2);

                            tabelki[2]=tabelki2[2];
                            tabelki[1]=tabelki2[1];
                            tabelki[0]=tabelki2[0];

                        }

                        if(((tabelki[3].getPoints()==tabelki[2].getPoints())&&(tabelki[2].getPoints()==tabelki[1].getPoints()))&&(tabelki[1].getPoints()==tabelki[0].getPoints())) {

                            bubbleSort2(tabelki);

                        }

                        nazwaGrupy.setText("GROUP B");

                        kraj1.setText(dajNazwePanstwa(tabelki[3].getName()));
                        kraj2.setText(dajNazwePanstwa(tabelki[2].getName()));
                        kraj3.setText(dajNazwePanstwa(tabelki[1].getName()));
                        kraj4.setText(dajNazwePanstwa(tabelki[0].getName()));

                        Drawable drawing1 = ContextCompat.getDrawable(Objects.requireNonNull(getContext()), dajFlage(tabelki[3].getName()));
                        flaga1.setImageDrawable(drawing1);
                        Drawable drawing2 = ContextCompat.getDrawable(getContext(), dajFlage(tabelki[2].getName()));
                        flaga2.setImageDrawable(drawing2);
                        Drawable drawing3 = ContextCompat.getDrawable(getContext(), dajFlage(tabelki[1].getName()));
                        flaga3.setImageDrawable(drawing3);
                        Drawable drawing4 = ContextCompat.getDrawable(getContext(), dajFlage(tabelki[0].getName()));
                        flaga4.setImageDrawable(drawing4);

                        p1.setText(String.valueOf(tabelki[3].getPlayed()));
                        p2.setText(String.valueOf(tabelki[2].getPlayed()));
                        p3.setText(String.valueOf(tabelki[1].getPlayed()));
                        p4.setText(String.valueOf(tabelki[0].getPlayed()));

                        win1.setText(String.valueOf(tabelki[3].getWin()));
                        win2.setText(String.valueOf(tabelki[2].getWin()));
                        win3.setText(String.valueOf(tabelki[1].getWin()));
                        win4.setText(String.valueOf(tabelki[0].getWin()));

                        draw1.setText(String.valueOf(tabelki[3].getDraw()));
                        draw2.setText(String.valueOf(tabelki[2].getDraw()));
                        draw3.setText(String.valueOf(tabelki[1].getDraw()));
                        draw4.setText(String.valueOf(tabelki[0].getDraw()));

                        l1.setText(String.valueOf(tabelki[3].getLost()));
                        l2.setText(String.valueOf(tabelki[2].getLost()));
                        l3.setText(String.valueOf(tabelki[1].getLost()));
                        l4.setText(String.valueOf(tabelki[0].getLost()));

                        pts1.setText(String.valueOf(tabelki[3].getPoints()));
                        pts2.setText(String.valueOf(tabelki[2].getPoints()));
                        pts3.setText(String.valueOf(tabelki[1].getPoints()));
                        pts4.setText(String.valueOf(tabelki[0].getPoints()));

                        t1.setText(tabgosp[6]);
                        t2.setText(tabgosc[6]);
                        t3.setText(tabgosp[7]);
                        t4.setText(tabgosc[7]);
                        t5.setText(tabgosp[8]);
                        t6.setText(tabgosc[8]);
                        t7.setText(tabgosp[9]);
                        t8.setText(tabgosc[9]);
                        t9.setText(tabgosp[10]);
                        t10.setText(tabgosc[10]);
                        t11.setText(tabgosp[11]);
                        t12.setText(tabgosc[11]);

                        w1.setText(tabpom1[6]);
                        w2.setText(tabpom2[6]);
                        w3.setText(tabpom1[7]);
                        w4.setText(tabpom2[7]);
                        w5.setText(tabpom1[8]);
                        w6.setText(tabpom2[8]);
                        w7.setText(tabpom1[9]);
                        w8.setText(tabpom2[9]);
                        w9.setText(tabpom1[10]);
                        w10.setText(tabpom2[10]);
                        w11.setText(tabpom1[11]);
                        w12.setText(tabpom2[11]);

                        Drawable drawable1 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosp[6]));
                        i1.setImageDrawable(drawable1);
                        Drawable drawable2 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosc[6]));
                        i2.setImageDrawable(drawable2);
                        Drawable drawable3 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosp[7]));
                        i3.setImageDrawable(drawable3);
                        Drawable drawable4 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosc[7]));
                        i4.setImageDrawable(drawable4);
                        Drawable drawable5 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosp[8]));
                        i5.setImageDrawable(drawable5);
                        Drawable drawable6 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosc[8]));
                        i6.setImageDrawable(drawable6);
                        Drawable drawable7 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosp[9]));
                        i7.setImageDrawable(drawable7);
                        Drawable drawable8 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosc[9]));
                        i8.setImageDrawable(drawable8);
                        Drawable drawable9 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosp[10]));
                        i9.setImageDrawable(drawable9);
                        Drawable drawable10 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosc[10]));
                        i10.setImageDrawable(drawable10);
                        Drawable drawable11 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosp[11]));
                        i11.setImageDrawable(drawable11);
                        Drawable drawable12 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosc[11]));
                        i12.setImageDrawable(drawable12);

                        nestedScrollView.post(new Runnable() {
                            public void run() {
                                nestedScrollView.fullScroll(nestedScrollView.FOCUS_UP);
                            }
                        });

                        cl.setVisibility(View.VISIBLE);

                    }
                    if (checkedId == R.id.button3) {
                        materialButtonToggleGroup2.clearChecked();

                        cl2.setVisibility(View.GONE);

                        d1 = new Druzyna("AUT", 0, 0, 0, 0, 0, 0);
                        d2 = new Druzyna("MKD", 0, 0, 0, 0, 0, 0);
                        d3 = new Druzyna("NED", 0, 0, 0, 0, 0, 0);
                        d4 = new Druzyna("UKR", 0, 0, 0, 0, 0, 0);

                        if(tabwyngosp[12]!=99){
                            if(tabwyngosp[12]>tabwyngosc[12]){
                                d1.wygral();
                                d2.przegral();
                            } else {
                                if(tabwyngosp[12]<tabwyngosc[12]){
                                    d1.przegral();
                                    d2.wygral();
                                } else {
                                    d1.remis();
                                    d2.remis();
                                }
                            }
                        }

                        if(tabwyngosp[13]!=99){
                            if(tabwyngosp[13]>tabwyngosc[13]){
                                d3.wygral();
                                d4.przegral();
                            } else {
                                if(tabwyngosp[13]<tabwyngosc[13]){
                                    d3.przegral();
                                    d4.wygral();
                                } else {
                                    d3.remis();
                                    d4.remis();
                                }
                            }
                        }

                        if(tabwyngosp[14]!=99){
                            if(tabwyngosp[14]>tabwyngosc[14]){
                                d4.wygral();
                                d2.przegral();
                            } else {
                                if(tabwyngosp[14]<tabwyngosc[14]){
                                    d4.przegral();
                                    d2.wygral();
                                } else {
                                    d4.remis();
                                    d2.remis();
                                }
                            }
                        }

                        if(tabwyngosp[15]!=99){
                            if(tabwyngosp[15]>tabwyngosc[15]){
                                d3.wygral();
                                d1.przegral();
                            } else {
                                if(tabwyngosp[15]<tabwyngosc[15]){
                                    d3.przegral();
                                    d1.wygral();
                                } else {
                                    d3.remis();
                                    d1.remis();
                                }
                            }
                        }

                        if(tabwyngosp[16]!=99){
                            if(tabwyngosp[16]>tabwyngosc[16]){
                                d2.wygral();
                                d3.przegral();
                            } else {
                                if(tabwyngosp[16]<tabwyngosc[16]){
                                    d2.przegral();
                                    d3.wygral();
                                } else {
                                    d2.remis();
                                    d3.remis();
                                }
                            }
                        }

                        if(tabwyngosp[17]!=99){
                            if(tabwyngosp[17]>tabwyngosc[17]){
                                d4.wygral();
                                d1.przegral();
                            } else {
                                if(tabwyngosp[17]<tabwyngosc[17]){
                                    d4.przegral();
                                    d1.wygral();
                                } else {
                                    d4.remis();
                                    d1.remis();
                                }
                            }
                        }

                        d1.setGole(tabwyngosp[55]);
                        d2.setGole(tabwyngosc[55]);
                        d3.setGole(tabwyngosp[56]);
                        d4.setGole(tabwyngosc[56]);

                        tabelki[0]=d1;
                        tabelki[1]=d2;
                        tabelki[2]=d3;
                        tabelki[3]=d4;

                        bubbleSort(tabelki);

                        if(tabelki[3].getPoints()==tabelki[2].getPoints()){

                            tabelki2=new Druzyna[2];

                            tabelki2[0]=tabelki[3];
                            tabelki2[1]=tabelki[2];

                            bubbleSort2(tabelki2);

                            tabelki[3]=tabelki2[1];
                            tabelki[2]=tabelki2[0];
                        }

                        if(tabelki[2].getPoints()==tabelki[1].getPoints()){

                            tabelki2=new Druzyna[2];

                            tabelki2[0]=tabelki[2];
                            tabelki2[1]=tabelki[1];

                            bubbleSort2(tabelki2);

                            tabelki[2]=tabelki2[1];
                            tabelki[1]=tabelki2[0];
                        }

                        if(tabelki[1].getPoints()==tabelki[0].getPoints()){

                            tabelki2=new Druzyna[2];

                            tabelki2[0]=tabelki[1];
                            tabelki2[1]=tabelki[0];

                            bubbleSort2(tabelki2);

                            tabelki[1]=tabelki2[1];
                            tabelki[0]=tabelki2[0];
                        }

                        if((tabelki[3].getPoints()==tabelki[2].getPoints())&&(tabelki[2].getPoints()==tabelki[1].getPoints())){

                            tabelki2=new Druzyna[3];

                            tabelki2[0]=tabelki[3];
                            tabelki2[1]=tabelki[2];
                            tabelki2[2]=tabelki[1];

                            bubbleSort2(tabelki2);

                            tabelki[3]=tabelki2[2];
                            tabelki[2]=tabelki2[1];
                            tabelki[1]=tabelki2[0];

                        }

                        if((tabelki[2].getPoints()==tabelki[1].getPoints())&&(tabelki[1].getPoints()==tabelki[0].getPoints())){

                            tabelki2=new Druzyna[3];

                            tabelki2[0]=tabelki[2];
                            tabelki2[1]=tabelki[1];
                            tabelki2[2]=tabelki[0];

                            bubbleSort2(tabelki2);

                            tabelki[2]=tabelki2[2];
                            tabelki[1]=tabelki2[1];
                            tabelki[0]=tabelki2[0];

                        }

                        if(((tabelki[3].getPoints()==tabelki[2].getPoints())&&(tabelki[2].getPoints()==tabelki[1].getPoints()))&&(tabelki[1].getPoints()==tabelki[0].getPoints())) {

                            bubbleSort2(tabelki);

                        }

                        nazwaGrupy.setText("GROUP C");

                        kraj1.setText(dajNazwePanstwa(tabelki[3].getName()));
                        kraj2.setText(dajNazwePanstwa(tabelki[2].getName()));
                        kraj3.setText(dajNazwePanstwa(tabelki[1].getName()));
                        kraj4.setText(dajNazwePanstwa(tabelki[0].getName()));

                        Drawable drawing1 = ContextCompat.getDrawable(Objects.requireNonNull(getContext()), dajFlage(tabelki[3].getName()));
                        flaga1.setImageDrawable(drawing1);
                        Drawable drawing2 = ContextCompat.getDrawable(getContext(), dajFlage(tabelki[2].getName()));
                        flaga2.setImageDrawable(drawing2);
                        Drawable drawing3 = ContextCompat.getDrawable(getContext(), dajFlage(tabelki[1].getName()));
                        flaga3.setImageDrawable(drawing3);
                        Drawable drawing4 = ContextCompat.getDrawable(getContext(), dajFlage(tabelki[0].getName()));
                        flaga4.setImageDrawable(drawing4);

                        p1.setText(String.valueOf(tabelki[3].getPlayed()));
                        p2.setText(String.valueOf(tabelki[2].getPlayed()));
                        p3.setText(String.valueOf(tabelki[1].getPlayed()));
                        p4.setText(String.valueOf(tabelki[0].getPlayed()));

                        win1.setText(String.valueOf(tabelki[3].getWin()));
                        win2.setText(String.valueOf(tabelki[2].getWin()));
                        win3.setText(String.valueOf(tabelki[1].getWin()));
                        win4.setText(String.valueOf(tabelki[0].getWin()));

                        draw1.setText(String.valueOf(tabelki[3].getDraw()));
                        draw2.setText(String.valueOf(tabelki[2].getDraw()));
                        draw3.setText(String.valueOf(tabelki[1].getDraw()));
                        draw4.setText(String.valueOf(tabelki[0].getDraw()));

                        l1.setText(String.valueOf(tabelki[3].getLost()));
                        l2.setText(String.valueOf(tabelki[2].getLost()));
                        l3.setText(String.valueOf(tabelki[1].getLost()));
                        l4.setText(String.valueOf(tabelki[0].getLost()));

                        pts1.setText(String.valueOf(tabelki[3].getPoints()));
                        pts2.setText(String.valueOf(tabelki[2].getPoints()));
                        pts3.setText(String.valueOf(tabelki[1].getPoints()));
                        pts4.setText(String.valueOf(tabelki[0].getPoints()));

                        t1.setText(tabgosp[12]);
                        t2.setText(tabgosc[12]);
                        t3.setText(tabgosp[13]);
                        t4.setText(tabgosc[13]);
                        t5.setText(tabgosp[14]);
                        t6.setText(tabgosc[14]);
                        t7.setText(tabgosp[15]);
                        t8.setText(tabgosc[15]);
                        t9.setText(tabgosp[16]);
                        t10.setText(tabgosc[16]);
                        t11.setText(tabgosp[17]);
                        t12.setText(tabgosc[17]);

                        w1.setText(tabpom1[12]);
                        w2.setText(tabpom2[12]);
                        w3.setText(tabpom1[13]);
                        w4.setText(tabpom2[13]);
                        w5.setText(tabpom1[14]);
                        w6.setText(tabpom2[14]);
                        w7.setText(tabpom1[15]);
                        w8.setText(tabpom2[15]);
                        w9.setText(tabpom1[16]);
                        w10.setText(tabpom2[16]);
                        w11.setText(tabpom1[17]);
                        w12.setText(tabpom2[17]);

                        Drawable drawable1 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosp[12]));
                        i1.setImageDrawable(drawable1);
                        Drawable drawable2 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosc[12]));
                        i2.setImageDrawable(drawable2);
                        Drawable drawable3 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosp[13]));
                        i3.setImageDrawable(drawable3);
                        Drawable drawable4 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosc[13]));
                        i4.setImageDrawable(drawable4);
                        Drawable drawable5 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosp[14]));
                        i5.setImageDrawable(drawable5);
                        Drawable drawable6 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosc[14]));
                        i6.setImageDrawable(drawable6);
                        Drawable drawable7 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosp[15]));
                        i7.setImageDrawable(drawable7);
                        Drawable drawable8 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosc[15]));
                        i8.setImageDrawable(drawable8);
                        Drawable drawable9 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosp[16]));
                        i9.setImageDrawable(drawable9);
                        Drawable drawable10 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosc[16]));
                        i10.setImageDrawable(drawable10);
                        Drawable drawable11 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosp[17]));
                        i11.setImageDrawable(drawable11);
                        Drawable drawable12 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosc[17]));
                        i12.setImageDrawable(drawable12);

                        nestedScrollView.post(new Runnable() {
                            public void run() {
                                nestedScrollView.fullScroll(nestedScrollView.FOCUS_UP);
                            }
                        });

                        cl.setVisibility(View.VISIBLE);

                    }
                    if (checkedId == R.id.button4) {
                        materialButtonToggleGroup2.clearChecked();

                        cl2.setVisibility(View.GONE);

                        d1 = new Druzyna("ENG", 0, 0, 0, 0, 0, 0);
                        d2 = new Druzyna("CRO", 0, 0, 0, 0, 0, 0);
                        d3 = new Druzyna("SCO", 0, 0, 0, 0, 0, 0);
                        d4 = new Druzyna("CZE", 0, 0, 0, 0, 0, 0);

                        if(tabwyngosp[18]!=99){
                            if(tabwyngosp[18]>tabwyngosc[18]){
                                d1.wygral();
                                d2.przegral();
                            } else {
                                if(tabwyngosp[18]<tabwyngosc[18]){
                                    d1.przegral();
                                    d2.wygral();
                                } else {
                                    d1.remis();
                                    d2.remis();
                                }
                            }
                        }

                        if(tabwyngosp[19]!=99){
                            if(tabwyngosp[19]>tabwyngosc[19]){
                                d3.wygral();
                                d4.przegral();
                            } else {
                                if(tabwyngosp[19]<tabwyngosc[19]){
                                    d3.przegral();
                                    d4.wygral();
                                } else {
                                    d3.remis();
                                    d4.remis();
                                }
                            }
                        }

                        if(tabwyngosp[20]!=99){
                            if(tabwyngosp[20]>tabwyngosc[20]){
                                d2.wygral();
                                d4.przegral();
                            } else {
                                if(tabwyngosp[20]<tabwyngosc[20]){
                                    d2.przegral();
                                    d4.wygral();
                                } else {
                                    d2.remis();
                                    d4.remis();
                                }
                            }
                        }

                        if(tabwyngosp[21]!=99){
                            if(tabwyngosp[21]>tabwyngosc[21]){
                                d1.wygral();
                                d3.przegral();
                            } else {
                                if(tabwyngosp[21]<tabwyngosc[21]){
                                    d1.przegral();
                                    d3.wygral();
                                } else {
                                    d1.remis();
                                    d3.remis();
                                }
                            }
                        }

                        if(tabwyngosp[22]!=99){
                            if(tabwyngosp[22]>tabwyngosc[22]){
                                d2.wygral();
                                d3.przegral();
                            } else {
                                if(tabwyngosp[22]<tabwyngosc[22]){
                                    d2.przegral();
                                    d3.wygral();
                                } else {
                                    d2.remis();
                                    d3.remis();
                                }
                            }
                        }

                        if(tabwyngosp[23]!=99){
                            if(tabwyngosp[23]>tabwyngosc[23]){
                                d4.wygral();
                                d1.przegral();
                            } else {
                                if(tabwyngosp[23]<tabwyngosc[23]){
                                    d4.przegral();
                                    d1.wygral();
                                } else {
                                    d4.remis();
                                    d1.remis();
                                }
                            }
                        }

                        d1.setGole(tabwyngosp[57]);
                        d2.setGole(tabwyngosc[57]);
                        d3.setGole(tabwyngosp[58]);
                        d4.setGole(tabwyngosc[58]);

                        tabelki[0]=d1;
                        tabelki[1]=d2;
                        tabelki[2]=d3;
                        tabelki[3]=d4;

                        bubbleSort(tabelki);

                        if(tabelki[3].getPoints()==tabelki[2].getPoints()){

                            tabelki2=new Druzyna[2];

                            tabelki2[0]=tabelki[3];
                            tabelki2[1]=tabelki[2];

                            bubbleSort2(tabelki2);

                            tabelki[3]=tabelki2[1];
                            tabelki[2]=tabelki2[0];
                        }

                        if(tabelki[2].getPoints()==tabelki[1].getPoints()){

                            tabelki2=new Druzyna[2];

                            tabelki2[0]=tabelki[2];
                            tabelki2[1]=tabelki[1];

                            bubbleSort2(tabelki2);

                            tabelki[2]=tabelki2[1];
                            tabelki[1]=tabelki2[0];
                        }

                        if(tabelki[1].getPoints()==tabelki[0].getPoints()){

                            tabelki2=new Druzyna[2];

                            tabelki2[0]=tabelki[1];
                            tabelki2[1]=tabelki[0];

                            bubbleSort2(tabelki2);

                            tabelki[1]=tabelki2[1];
                            tabelki[0]=tabelki2[0];
                        }

                        if((tabelki[3].getPoints()==tabelki[2].getPoints())&&(tabelki[2].getPoints()==tabelki[1].getPoints())){

                            tabelki2=new Druzyna[3];

                            tabelki2[0]=tabelki[3];
                            tabelki2[1]=tabelki[2];
                            tabelki2[2]=tabelki[1];

                            bubbleSort2(tabelki2);

                            tabelki[3]=tabelki2[2];
                            tabelki[2]=tabelki2[1];
                            tabelki[1]=tabelki2[0];

                        }

                        if((tabelki[2].getPoints()==tabelki[1].getPoints())&&(tabelki[1].getPoints()==tabelki[0].getPoints())){

                            tabelki2=new Druzyna[3];

                            tabelki2[0]=tabelki[2];
                            tabelki2[1]=tabelki[1];
                            tabelki2[2]=tabelki[0];

                            bubbleSort2(tabelki2);

                            tabelki[2]=tabelki2[2];
                            tabelki[1]=tabelki2[1];
                            tabelki[0]=tabelki2[0];

                        }

                        if(((tabelki[3].getPoints()==tabelki[2].getPoints())&&(tabelki[2].getPoints()==tabelki[1].getPoints()))&&(tabelki[1].getPoints()==tabelki[0].getPoints())) {

                            bubbleSort2(tabelki);

                        }

                        nazwaGrupy.setText("GROUP D");

                        kraj1.setText(dajNazwePanstwa(tabelki[3].getName()));
                        kraj2.setText(dajNazwePanstwa(tabelki[2].getName()));
                        kraj3.setText(dajNazwePanstwa(tabelki[1].getName()));
                        kraj4.setText(dajNazwePanstwa(tabelki[0].getName()));

                        Drawable drawing1 = ContextCompat.getDrawable(Objects.requireNonNull(getContext()), dajFlage(tabelki[3].getName()));
                        flaga1.setImageDrawable(drawing1);
                        Drawable drawing2 = ContextCompat.getDrawable(getContext(), dajFlage(tabelki[2].getName()));
                        flaga2.setImageDrawable(drawing2);
                        Drawable drawing3 = ContextCompat.getDrawable(getContext(), dajFlage(tabelki[1].getName()));
                        flaga3.setImageDrawable(drawing3);
                        Drawable drawing4 = ContextCompat.getDrawable(getContext(), dajFlage(tabelki[0].getName()));
                        flaga4.setImageDrawable(drawing4);

                        p1.setText(String.valueOf(tabelki[3].getPlayed()));
                        p2.setText(String.valueOf(tabelki[2].getPlayed()));
                        p3.setText(String.valueOf(tabelki[1].getPlayed()));
                        p4.setText(String.valueOf(tabelki[0].getPlayed()));

                        win1.setText(String.valueOf(tabelki[3].getWin()));
                        win2.setText(String.valueOf(tabelki[2].getWin()));
                        win3.setText(String.valueOf(tabelki[1].getWin()));
                        win4.setText(String.valueOf(tabelki[0].getWin()));

                        draw1.setText(String.valueOf(tabelki[3].getDraw()));
                        draw2.setText(String.valueOf(tabelki[2].getDraw()));
                        draw3.setText(String.valueOf(tabelki[1].getDraw()));
                        draw4.setText(String.valueOf(tabelki[0].getDraw()));

                        l1.setText(String.valueOf(tabelki[3].getLost()));
                        l2.setText(String.valueOf(tabelki[2].getLost()));
                        l3.setText(String.valueOf(tabelki[1].getLost()));
                        l4.setText(String.valueOf(tabelki[0].getLost()));

                        pts1.setText(String.valueOf(tabelki[3].getPoints()));
                        pts2.setText(String.valueOf(tabelki[2].getPoints()));
                        pts3.setText(String.valueOf(tabelki[1].getPoints()));
                        pts4.setText(String.valueOf(tabelki[0].getPoints()));

                        t1.setText(tabgosp[18]);
                        t2.setText(tabgosc[18]);
                        t3.setText(tabgosp[19]);
                        t4.setText(tabgosc[19]);
                        t5.setText(tabgosp[20]);
                        t6.setText(tabgosc[20]);
                        t7.setText(tabgosp[21]);
                        t8.setText(tabgosc[21]);
                        t9.setText(tabgosp[22]);
                        t10.setText(tabgosc[22]);
                        t11.setText(tabgosp[23]);
                        t12.setText(tabgosc[23]);

                        w1.setText(tabpom1[18]);
                        w2.setText(tabpom2[18]);
                        w3.setText(tabpom1[19]);
                        w4.setText(tabpom2[19]);
                        w5.setText(tabpom1[20]);
                        w6.setText(tabpom2[20]);
                        w7.setText(tabpom1[21]);
                        w8.setText(tabpom2[21]);
                        w9.setText(tabpom1[22]);
                        w10.setText(tabpom2[22]);
                        w11.setText(tabpom1[23]);
                        w12.setText(tabpom2[23]);

                        Drawable drawable1 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosp[18]));
                        i1.setImageDrawable(drawable1);
                        Drawable drawable2 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosc[18]));
                        i2.setImageDrawable(drawable2);
                        Drawable drawable3 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosp[19]));
                        i3.setImageDrawable(drawable3);
                        Drawable drawable4 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosc[19]));
                        i4.setImageDrawable(drawable4);
                        Drawable drawable5 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosp[20]));
                        i5.setImageDrawable(drawable5);
                        Drawable drawable6 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosc[20]));
                        i6.setImageDrawable(drawable6);
                        Drawable drawable7 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosp[21]));
                        i7.setImageDrawable(drawable7);
                        Drawable drawable8 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosc[21]));
                        i8.setImageDrawable(drawable8);
                        Drawable drawable9 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosp[22]));
                        i9.setImageDrawable(drawable9);
                        Drawable drawable10 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosc[22]));
                        i10.setImageDrawable(drawable10);
                        Drawable drawable11 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosp[23]));
                        i11.setImageDrawable(drawable11);
                        Drawable drawable12 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosc[23]));
                        i12.setImageDrawable(drawable12);

                        nestedScrollView.post(new Runnable() {
                            public void run() {
                                nestedScrollView.fullScroll(nestedScrollView.FOCUS_UP);
                            }
                        });

                        cl.setVisibility(View.VISIBLE);

                    }
                    if (checkedId == R.id.button5) {
                        materialButtonToggleGroup2.clearChecked();

                        cl2.setVisibility(View.GONE);

                        d1 = new Druzyna("POL", 0, 0, 0, 0, 0, 0);
                        d2 = new Druzyna("SVN", 0, 0, 0, 0, 0, 0);
                        d3 = new Druzyna("ESP", 0, 0, 0, 0, 0, 0);
                        d4 = new Druzyna("SWE", 0, 0, 0, 0, 0, 0);

                        if(tabwyngosp[24]!=99){
                            if(tabwyngosp[24]>tabwyngosc[24]){
                                d1.wygral();
                                d2.przegral();
                            } else {
                                if(tabwyngosp[24]<tabwyngosc[24]){
                                    d1.przegral();
                                    d2.wygral();
                                } else {
                                    d1.remis();
                                    d2.remis();
                                }
                            }
                        }

                        if(tabwyngosp[25]!=99){
                            if(tabwyngosp[25]>tabwyngosc[25]){
                                d3.wygral();
                                d4.przegral();
                            } else {
                                if(tabwyngosp[25]<tabwyngosc[25]){
                                    d3.przegral();
                                    d4.wygral();
                                } else {
                                    d3.remis();
                                    d4.remis();
                                }
                            }
                        }

                        if(tabwyngosp[26]!=99){
                            if(tabwyngosp[26]>tabwyngosc[26]){
                                d4.wygral();
                                d2.przegral();
                            } else {
                                if(tabwyngosp[26]<tabwyngosc[26]){
                                    d4.przegral();
                                    d2.wygral();
                                } else {
                                    d4.remis();
                                    d2.remis();
                                }
                            }
                        }

                        if(tabwyngosp[27]!=99){
                            if(tabwyngosp[27]>tabwyngosc[27]){
                                d3.wygral();
                                d1.przegral();
                            } else {
                                if(tabwyngosp[27]<tabwyngosc[27]){
                                    d3.przegral();
                                    d1.wygral();
                                } else {
                                    d3.remis();
                                    d1.remis();
                                }
                            }
                        }

                        if(tabwyngosp[28]!=99){
                            if(tabwyngosp[28]>tabwyngosc[28]){
                                d2.wygral();
                                d3.przegral();
                            } else {
                                if(tabwyngosp[28]<tabwyngosc[28]){
                                    d2.przegral();
                                    d3.wygral();
                                } else {
                                    d2.remis();
                                    d3.remis();
                                }
                            }
                        }

                        if(tabwyngosp[29]!=99){
                            if(tabwyngosp[29]>tabwyngosc[29]){
                                d4.wygral();
                                d1.przegral();
                            } else {
                                if(tabwyngosp[29]<tabwyngosc[29]){
                                    d4.przegral();
                                    d1.wygral();
                                } else {
                                    d4.remis();
                                    d1.remis();
                                }
                            }
                        }

                        d1.setGole(tabwyngosp[59]);
                        d2.setGole(tabwyngosc[59]);
                        d3.setGole(tabwyngosp[60]);
                        d4.setGole(tabwyngosc[60]);

                        tabelki[0]=d1;
                        tabelki[1]=d2;
                        tabelki[2]=d3;
                        tabelki[3]=d4;

                        bubbleSort(tabelki);

                        if(tabelki[3].getPoints()==tabelki[2].getPoints()){

                            tabelki2=new Druzyna[2];

                            tabelki2[0]=tabelki[3];
                            tabelki2[1]=tabelki[2];

                            bubbleSort2(tabelki2);

                            tabelki[3]=tabelki2[1];
                            tabelki[2]=tabelki2[0];
                        }

                        if(tabelki[2].getPoints()==tabelki[1].getPoints()){

                            tabelki2=new Druzyna[2];

                            tabelki2[0]=tabelki[2];
                            tabelki2[1]=tabelki[1];

                            bubbleSort2(tabelki2);

                            tabelki[2]=tabelki2[1];
                            tabelki[1]=tabelki2[0];
                        }

                        if(tabelki[1].getPoints()==tabelki[0].getPoints()){

                            tabelki2=new Druzyna[2];

                            tabelki2[0]=tabelki[1];
                            tabelki2[1]=tabelki[0];

                            bubbleSort2(tabelki2);

                            tabelki[1]=tabelki2[1];
                            tabelki[0]=tabelki2[0];
                        }

                        if((tabelki[3].getPoints()==tabelki[2].getPoints())&&(tabelki[2].getPoints()==tabelki[1].getPoints())){

                            tabelki2=new Druzyna[3];

                            tabelki2[0]=tabelki[3];
                            tabelki2[1]=tabelki[2];
                            tabelki2[2]=tabelki[1];

                            bubbleSort2(tabelki2);

                            tabelki[3]=tabelki2[2];
                            tabelki[2]=tabelki2[1];
                            tabelki[1]=tabelki2[0];

                        }

                        if((tabelki[2].getPoints()==tabelki[1].getPoints())&&(tabelki[1].getPoints()==tabelki[0].getPoints())){

                            tabelki2=new Druzyna[3];

                            tabelki2[0]=tabelki[2];
                            tabelki2[1]=tabelki[1];
                            tabelki2[2]=tabelki[0];

                            bubbleSort2(tabelki2);

                            tabelki[2]=tabelki2[2];
                            tabelki[1]=tabelki2[1];
                            tabelki[0]=tabelki2[0];

                        }

                        if(((tabelki[3].getPoints()==tabelki[2].getPoints())&&(tabelki[2].getPoints()==tabelki[1].getPoints()))&&(tabelki[1].getPoints()==tabelki[0].getPoints())) {

                            bubbleSort2(tabelki);

                        }

                        nazwaGrupy.setText("GROUP E");

                        kraj1.setText(dajNazwePanstwa(tabelki[3].getName()));
                        kraj2.setText(dajNazwePanstwa(tabelki[2].getName()));
                        kraj3.setText(dajNazwePanstwa(tabelki[1].getName()));
                        kraj4.setText(dajNazwePanstwa(tabelki[0].getName()));

                        Drawable drawing1 = ContextCompat.getDrawable(Objects.requireNonNull(getContext()), dajFlage(tabelki[3].getName()));
                        flaga1.setImageDrawable(drawing1);
                        Drawable drawing2 = ContextCompat.getDrawable(getContext(), dajFlage(tabelki[2].getName()));
                        flaga2.setImageDrawable(drawing2);
                        Drawable drawing3 = ContextCompat.getDrawable(getContext(), dajFlage(tabelki[1].getName()));
                        flaga3.setImageDrawable(drawing3);
                        Drawable drawing4 = ContextCompat.getDrawable(getContext(), dajFlage(tabelki[0].getName()));
                        flaga4.setImageDrawable(drawing4);

                        p1.setText(String.valueOf(tabelki[3].getPlayed()));
                        p2.setText(String.valueOf(tabelki[2].getPlayed()));
                        p3.setText(String.valueOf(tabelki[1].getPlayed()));
                        p4.setText(String.valueOf(tabelki[0].getPlayed()));

                        win1.setText(String.valueOf(tabelki[3].getWin()));
                        win2.setText(String.valueOf(tabelki[2].getWin()));
                        win3.setText(String.valueOf(tabelki[1].getWin()));
                        win4.setText(String.valueOf(tabelki[0].getWin()));

                        draw1.setText(String.valueOf(tabelki[3].getDraw()));
                        draw2.setText(String.valueOf(tabelki[2].getDraw()));
                        draw3.setText(String.valueOf(tabelki[1].getDraw()));
                        draw4.setText(String.valueOf(tabelki[0].getDraw()));

                        l1.setText(String.valueOf(tabelki[3].getLost()));
                        l2.setText(String.valueOf(tabelki[2].getLost()));
                        l3.setText(String.valueOf(tabelki[1].getLost()));
                        l4.setText(String.valueOf(tabelki[0].getLost()));

                        pts1.setText(String.valueOf(tabelki[3].getPoints()));
                        pts2.setText(String.valueOf(tabelki[2].getPoints()));
                        pts3.setText(String.valueOf(tabelki[1].getPoints()));
                        pts4.setText(String.valueOf(tabelki[0].getPoints()));

                        t1.setText(tabgosp[24]);
                        t2.setText(tabgosc[24]);
                        t3.setText(tabgosp[25]);
                        t4.setText(tabgosc[25]);
                        t5.setText(tabgosp[26]);
                        t6.setText(tabgosc[26]);
                        t7.setText(tabgosp[27]);
                        t8.setText(tabgosc[27]);
                        t9.setText(tabgosp[28]);
                        t10.setText(tabgosc[28]);
                        t11.setText(tabgosp[29]);
                        t12.setText(tabgosc[29]);

                        w1.setText(tabpom1[24]);
                        w2.setText(tabpom2[24]);
                        w3.setText(tabpom1[25]);
                        w4.setText(tabpom2[25]);
                        w5.setText(tabpom1[26]);
                        w6.setText(tabpom2[26]);
                        w7.setText(tabpom1[27]);
                        w8.setText(tabpom2[27]);
                        w9.setText(tabpom1[28]);
                        w10.setText(tabpom2[28]);
                        w11.setText(tabpom1[29]);
                        w12.setText(tabpom2[29]);

                        Drawable drawable1 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosp[24]));
                        i1.setImageDrawable(drawable1);
                        Drawable drawable2 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosc[24]));
                        i2.setImageDrawable(drawable2);
                        Drawable drawable3 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosp[25]));
                        i3.setImageDrawable(drawable3);
                        Drawable drawable4 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosc[25]));
                        i4.setImageDrawable(drawable4);
                        Drawable drawable5 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosp[26]));
                        i5.setImageDrawable(drawable5);
                        Drawable drawable6 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosc[26]));
                        i6.setImageDrawable(drawable6);
                        Drawable drawable7 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosp[27]));
                        i7.setImageDrawable(drawable7);
                        Drawable drawable8 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosc[27]));
                        i8.setImageDrawable(drawable8);
                        Drawable drawable9 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosp[28]));
                        i9.setImageDrawable(drawable9);
                        Drawable drawable10 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosc[28]));
                        i10.setImageDrawable(drawable10);
                        Drawable drawable11 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosp[29]));
                        i11.setImageDrawable(drawable11);
                        Drawable drawable12 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosc[29]));
                        i12.setImageDrawable(drawable12);

                        nestedScrollView.post(new Runnable() {
                            public void run() {
                                nestedScrollView.fullScroll(nestedScrollView.FOCUS_UP);
                            }
                        });

                        cl.setVisibility(View.VISIBLE);

                    }
                    if (checkedId == R.id.button6) {
                        materialButtonToggleGroup2.clearChecked();

                        cl2.setVisibility(View.GONE);

                        d1 = new Druzyna("HUN", 0, 0, 0, 0, 0, 0);
                        d2 = new Druzyna("POR", 0, 0, 0, 0, 0, 0);
                        d3 = new Druzyna("FRA", 0, 0, 0, 0, 0, 0);
                        d4 = new Druzyna("GER", 0, 0, 0, 0, 0, 0);

                        if(tabwyngosp[30]!=99){
                            if(tabwyngosp[30]>tabwyngosc[30]){
                                d1.wygral();
                                d2.przegral();
                            } else {
                                if(tabwyngosp[30]<tabwyngosc[30]){
                                    d1.przegral();
                                    d2.wygral();
                                } else {
                                    d1.remis();
                                    d2.remis();
                                }
                            }
                        }

                        if(tabwyngosp[31]!=99){
                            if(tabwyngosp[31]>tabwyngosc[31]){
                                d3.wygral();
                                d4.przegral();
                            } else {
                                if(tabwyngosp[31]<tabwyngosc[31]){
                                    d3.przegral();
                                    d4.wygral();
                                } else {
                                    d3.remis();
                                    d4.remis();
                                }
                            }
                        }

                        if(tabwyngosp[32]!=99){
                            if(tabwyngosp[32]>tabwyngosc[32]){
                                d1.wygral();
                                d3.przegral();
                            } else {
                                if(tabwyngosp[32]<tabwyngosc[32]){
                                    d1.przegral();
                                    d3.wygral();
                                } else {
                                    d1.remis();
                                    d3.remis();
                                }
                            }
                        }

                        if(tabwyngosp[33]!=99){
                            if(tabwyngosp[33]>tabwyngosc[33]){
                                d2.wygral();
                                d4.przegral();
                            } else {
                                if(tabwyngosp[33]<tabwyngosc[33]){
                                    d2.przegral();
                                    d4.wygral();
                                } else {
                                    d2.remis();
                                    d4.remis();
                                }
                            }
                        }

                        if(tabwyngosp[34]!=99){
                            if(tabwyngosp[34]>tabwyngosc[34]){
                                d2.wygral();
                                d3.przegral();
                            } else {
                                if(tabwyngosp[34]<tabwyngosc[34]){
                                    d2.przegral();
                                    d3.wygral();
                                } else {
                                    d2.remis();
                                    d3.remis();
                                }
                            }
                        }

                        if(tabwyngosp[35]!=99){
                            if(tabwyngosp[35]>tabwyngosc[35]){
                                d4.wygral();
                                d1.przegral();
                            } else {
                                if(tabwyngosp[35]<tabwyngosc[35]){
                                    d4.przegral();
                                    d1.wygral();
                                } else {
                                    d4.remis();
                                    d1.remis();
                                }
                            }
                        }

                        d1.setGole(tabwyngosp[61]);
                        d2.setGole(tabwyngosc[61]);
                        d3.setGole(tabwyngosp[62]);
                        d4.setGole(tabwyngosc[62]);

                        tabelki[0]=d1;
                        tabelki[1]=d2;
                        tabelki[2]=d3;
                        tabelki[3]=d4;

                        bubbleSort(tabelki);

                        if(tabelki[3].getPoints()==tabelki[2].getPoints()){

                            tabelki2=new Druzyna[2];

                            tabelki2[0]=tabelki[3];
                            tabelki2[1]=tabelki[2];

                            bubbleSort2(tabelki2);

                            tabelki[3]=tabelki2[1];
                            tabelki[2]=tabelki2[0];
                        }

                        if(tabelki[2].getPoints()==tabelki[1].getPoints()){

                            tabelki2=new Druzyna[2];

                            tabelki2[0]=tabelki[2];
                            tabelki2[1]=tabelki[1];

                            bubbleSort2(tabelki2);

                            tabelki[2]=tabelki2[1];
                            tabelki[1]=tabelki2[0];
                        }

                        if(tabelki[1].getPoints()==tabelki[0].getPoints()){

                            tabelki2=new Druzyna[2];

                            tabelki2[0]=tabelki[1];
                            tabelki2[1]=tabelki[0];

                            bubbleSort2(tabelki2);

                            tabelki[1]=tabelki2[1];
                            tabelki[0]=tabelki2[0];
                        }

                        if((tabelki[3].getPoints()==tabelki[2].getPoints())&&(tabelki[2].getPoints()==tabelki[1].getPoints())){

                            tabelki2=new Druzyna[3];

                            tabelki2[0]=tabelki[3];
                            tabelki2[1]=tabelki[2];
                            tabelki2[2]=tabelki[1];

                            bubbleSort2(tabelki2);

                            tabelki[3]=tabelki2[2];
                            tabelki[2]=tabelki2[1];
                            tabelki[1]=tabelki2[0];

                        }

                        if((tabelki[2].getPoints()==tabelki[1].getPoints())&&(tabelki[1].getPoints()==tabelki[0].getPoints())){

                            tabelki2=new Druzyna[3];

                            tabelki2[0]=tabelki[2];
                            tabelki2[1]=tabelki[1];
                            tabelki2[2]=tabelki[0];

                            bubbleSort2(tabelki2);

                            tabelki[2]=tabelki2[2];
                            tabelki[1]=tabelki2[1];
                            tabelki[0]=tabelki2[0];

                        }

                        if(((tabelki[3].getPoints()==tabelki[2].getPoints())&&(tabelki[2].getPoints()==tabelki[1].getPoints()))&&(tabelki[1].getPoints()==tabelki[0].getPoints())) {

                            bubbleSort2(tabelki);

                        }

                        nazwaGrupy.setText("GROUP F");

                        kraj1.setText(dajNazwePanstwa(tabelki[3].getName()));
                        kraj2.setText(dajNazwePanstwa(tabelki[2].getName()));
                        kraj3.setText(dajNazwePanstwa(tabelki[1].getName()));
                        kraj4.setText(dajNazwePanstwa(tabelki[0].getName()));

                        Drawable drawing1 = ContextCompat.getDrawable(Objects.requireNonNull(getContext()), dajFlage(tabelki[3].getName()));
                        flaga1.setImageDrawable(drawing1);
                        Drawable drawing2 = ContextCompat.getDrawable(getContext(), dajFlage(tabelki[2].getName()));
                        flaga2.setImageDrawable(drawing2);
                        Drawable drawing3 = ContextCompat.getDrawable(getContext(), dajFlage(tabelki[1].getName()));
                        flaga3.setImageDrawable(drawing3);
                        Drawable drawing4 = ContextCompat.getDrawable(getContext(), dajFlage(tabelki[0].getName()));
                        flaga4.setImageDrawable(drawing4);

                        p1.setText(String.valueOf(tabelki[3].getPlayed()));
                        p2.setText(String.valueOf(tabelki[2].getPlayed()));
                        p3.setText(String.valueOf(tabelki[1].getPlayed()));
                        p4.setText(String.valueOf(tabelki[0].getPlayed()));

                        win1.setText(String.valueOf(tabelki[3].getWin()));
                        win2.setText(String.valueOf(tabelki[2].getWin()));
                        win3.setText(String.valueOf(tabelki[1].getWin()));
                        win4.setText(String.valueOf(tabelki[0].getWin()));

                        draw1.setText(String.valueOf(tabelki[3].getDraw()));
                        draw2.setText(String.valueOf(tabelki[2].getDraw()));
                        draw3.setText(String.valueOf(tabelki[1].getDraw()));
                        draw4.setText(String.valueOf(tabelki[0].getDraw()));

                        l1.setText(String.valueOf(tabelki[3].getLost()));
                        l2.setText(String.valueOf(tabelki[2].getLost()));
                        l3.setText(String.valueOf(tabelki[1].getLost()));
                        l4.setText(String.valueOf(tabelki[0].getLost()));

                        pts1.setText(String.valueOf(tabelki[3].getPoints()));
                        pts2.setText(String.valueOf(tabelki[2].getPoints()));
                        pts3.setText(String.valueOf(tabelki[1].getPoints()));
                        pts4.setText(String.valueOf(tabelki[0].getPoints()));

                        t1.setText(tabgosp[30]);
                        t2.setText(tabgosc[30]);
                        t3.setText(tabgosp[31]);
                        t4.setText(tabgosc[31]);
                        t5.setText(tabgosp[32]);
                        t6.setText(tabgosc[32]);
                        t7.setText(tabgosp[33]);
                        t8.setText(tabgosc[33]);
                        t9.setText(tabgosp[34]);
                        t10.setText(tabgosc[34]);
                        t11.setText(tabgosp[35]);
                        t12.setText(tabgosc[35]);

                        w1.setText(tabpom1[30]);
                        w2.setText(tabpom2[30]);
                        w3.setText(tabpom1[31]);
                        w4.setText(tabpom2[31]);
                        w5.setText(tabpom1[32]);
                        w6.setText(tabpom2[32]);
                        w7.setText(tabpom1[33]);
                        w8.setText(tabpom2[33]);
                        w9.setText(tabpom1[34]);
                        w10.setText(tabpom2[34]);
                        w11.setText(tabpom1[35]);
                        w12.setText(tabpom2[35]);

                        Drawable drawable1 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosp[30]));
                        i1.setImageDrawable(drawable1);
                        Drawable drawable2 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosc[30]));
                        i2.setImageDrawable(drawable2);
                        Drawable drawable3 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosp[31]));
                        i3.setImageDrawable(drawable3);
                        Drawable drawable4 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosc[31]));
                        i4.setImageDrawable(drawable4);
                        Drawable drawable5 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosp[32]));
                        i5.setImageDrawable(drawable5);
                        Drawable drawable6 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosc[32]));
                        i6.setImageDrawable(drawable6);
                        Drawable drawable7 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosp[33]));
                        i7.setImageDrawable(drawable7);
                        Drawable drawable8 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosc[33]));
                        i8.setImageDrawable(drawable8);
                        Drawable drawable9 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosp[34]));
                        i9.setImageDrawable(drawable9);
                        Drawable drawable10 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosc[34]));
                        i10.setImageDrawable(drawable10);
                        Drawable drawable11 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosp[35]));
                        i11.setImageDrawable(drawable11);
                        Drawable drawable12 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosc[35]));
                        i12.setImageDrawable(drawable12);

                        nestedScrollView.post(new Runnable() {
                            public void run() {
                                nestedScrollView.fullScroll(nestedScrollView.FOCUS_UP);
                            }
                        });

                        cl.setVisibility(View.VISIBLE);

                    }
                }
            }
        });

        //togglebutton dodatkowy do fazy pucharowej
        materialButtonToggleGroup2.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                if (isChecked) {
                    if (checkedId == R.id.button7) {
                        materialButtonToggleGroup1.clearChecked();

                        nestedScrollView2.post(new Runnable() {
                            public void run() {
                                nestedScrollView2.fullScroll(nestedScrollView2.FOCUS_UP);
                            }
                        });

                        cl.setVisibility(View.GONE);

                        k1.setText(tabgosp[36]);
                        k2.setText(tabgosc[36]);
                        k3.setText(tabgosp[37]);
                        k4.setText(tabgosc[37]);
                        k5.setText(tabgosp[38]);
                        k6.setText(tabgosc[38]);
                        k7.setText(tabgosp[39]);
                        k8.setText(tabgosc[39]);
                        k9.setText(tabgosp[40]);
                        k10.setText(tabgosc[40]);
                        k11.setText(tabgosp[41]);
                        k12.setText(tabgosc[41]);
                        k13.setText(tabgosp[42]);
                        k14.setText(tabgosc[42]);
                        k15.setText(tabgosp[43]);
                        k16.setText(tabgosc[43]);
                        k17.setText(tabgosp[44]);
                        k18.setText(tabgosc[44]);
                        k19.setText(tabgosp[45]);
                        k20.setText(tabgosc[45]);
                        k21.setText(tabgosp[46]);
                        k22.setText(tabgosc[46]);
                        k23.setText(tabgosp[47]);
                        k24.setText(tabgosc[47]);
                        k25.setText(tabgosp[48]);
                        k26.setText(tabgosc[48]);
                        k27.setText(tabgosp[49]);
                        k28.setText(tabgosc[49]);
                        k29.setText(tabgosp[50]);
                        k30.setText(tabgosc[50]);

                        a1.setText(tabpom1[36]);
                        a2.setText(tabpom2[36]);
                        a3.setText(tabpom1[37]);
                        a4.setText(tabpom2[37]);
                        a5.setText(tabpom1[38]);
                        a6.setText(tabpom2[38]);
                        a7.setText(tabpom1[39]);
                        a8.setText(tabpom2[39]);
                        a9.setText(tabpom1[40]);
                        a10.setText(tabpom2[40]);
                        a11.setText(tabpom1[41]);
                        a12.setText(tabpom2[41]);
                        a13.setText(tabpom1[42]);
                        a14.setText(tabpom2[42]);
                        a15.setText(tabpom1[43]);
                        a16.setText(tabpom2[43]);
                        a17.setText(tabpom1[44]);
                        a18.setText(tabpom2[44]);
                        a19.setText(tabpom1[45]);
                        a20.setText(tabpom2[45]);
                        a21.setText(tabpom1[46]);
                        a22.setText(tabpom2[46]);
                        a23.setText(tabpom1[47]);
                        a24.setText(tabpom2[47]);
                        a25.setText(tabpom1[48]);
                        a26.setText(tabpom2[48]);
                        a27.setText(tabpom1[49]);
                        a28.setText(tabpom2[49]);
                        a29.setText(tabpom1[50]);
                        a30.setText(tabpom2[50]);

                        Drawable drawableg1 = ContextCompat.getDrawable(Objects.requireNonNull(getContext()), dajFlage(tabgosp[36]));
                        g1.setImageDrawable(drawableg1);
                        Drawable drawableg2 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosc[36]));
                        g2.setImageDrawable(drawableg2);
                        Drawable drawableg3 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosp[37]));
                        g3.setImageDrawable(drawableg3);
                        Drawable drawableg4 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosc[37]));
                        g4.setImageDrawable(drawableg4);
                        Drawable drawableg5 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosp[38]));
                        g5.setImageDrawable(drawableg5);
                        Drawable drawableg6 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosc[38]));
                        g6.setImageDrawable(drawableg6);
                        Drawable drawableg7 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosp[39]));
                        g7.setImageDrawable(drawableg7);
                        Drawable drawableg8 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosc[39]));
                        g8.setImageDrawable(drawableg8);
                        Drawable drawableg9 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosp[40]));
                        g9.setImageDrawable(drawableg9);
                        Drawable drawableg10 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosc[40]));
                        g10.setImageDrawable(drawableg10);
                        Drawable drawableg11 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosp[41]));
                        g11.setImageDrawable(drawableg11);
                        Drawable drawableg12 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosc[41]));
                        g12.setImageDrawable(drawableg12);
                        Drawable drawableg13 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosp[42]));
                        g13.setImageDrawable(drawableg13);
                        Drawable drawableg14 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosc[42]));
                        g14.setImageDrawable(drawableg14);
                        Drawable drawableg15 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosp[43]));
                        g15.setImageDrawable(drawableg15);
                        Drawable drawableg16 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosc[43]));
                        g16.setImageDrawable(drawableg16);
                        Drawable drawableg17 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosp[44]));
                        g17.setImageDrawable(drawableg17);
                        Drawable drawableg18 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosc[44]));
                        g18.setImageDrawable(drawableg18);
                        Drawable drawableg19 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosp[45]));
                        g19.setImageDrawable(drawableg19);
                        Drawable drawableg20 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosc[45]));
                        g20.setImageDrawable(drawableg20);
                        Drawable drawableg21 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosp[46]));
                        g21.setImageDrawable(drawableg21);
                        Drawable drawableg22 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosc[46]));
                        g22.setImageDrawable(drawableg22);
                        Drawable drawableg23 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosp[47]));
                        g23.setImageDrawable(drawableg23);
                        Drawable drawableg24 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosc[47]));
                        g24.setImageDrawable(drawableg24);
                        Drawable drawableg25 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosp[48]));
                        g25.setImageDrawable(drawableg25);
                        Drawable drawableg26 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosc[48]));
                        g26.setImageDrawable(drawableg26);
                        Drawable drawableg27 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosp[49]));
                        g27.setImageDrawable(drawableg27);
                        Drawable drawableg28 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosc[49]));
                        g28.setImageDrawable(drawableg28);
                        Drawable drawableg29 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosp[50]));
                        g29.setImageDrawable(drawableg29);
                        Drawable drawableg30 = ContextCompat.getDrawable(getContext(), dajFlage(tabgosc[50]));
                        g30.setImageDrawable(drawableg30);


                        cl2.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        return view;
    }

    @Override
    public void showLoading() {
        tabpb.setVisibility(View.VISIBLE);
        materialButtonToggleGroup1.setVisibility(View.GONE);
        materialButtonToggleGroup2.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        tabpb.setVisibility(View.GONE);
        materialButtonToggleGroup1.setVisibility(View.VISIBLE);
        materialButtonToggleGroup2.setVisibility(View.VISIBLE);
    }

    @Override
    public void onGetResult(List<Grupa> groups) {

        tabid = new int[groups.size()];
        tabwyngosp = new int[groups.size()];
        tabwyngosc = new int[groups.size()];
        tabgosp = new String[groups.size()];
        tabgosc = new String[groups.size()];
        tabpom1 = new String[groups.size()];
        tabpom2 = new String[groups.size()];

        Grupa grupa1;

        for (int i = 0; i < tabgosp.length; i++) {
            grupa1 = groups.get(i);
            tabid[i] = grupa1.getId_meczu();
            tabwyngosp[i] = grupa1.getWyn_gosp();
            tabwyngosc[i] = grupa1.getWyn_gosc();
            tabgosp[i] = grupa1.getGosp();
            tabgosc[i] = grupa1.getGosc();
        }

        for (int i = 0; i < tabgosp.length; i++) {
            if(tabwyngosp[i]!=99) {
                tabpom1[i] = Integer.toString(tabwyngosp[i]);
                tabpom2[i] = Integer.toString(tabwyngosc[i]);
            } else {
                tabpom1[i] = "-";
                tabpom2[i] = "-";
            }
            if(tabgosp[i].equals("AAA")){
                tabgosp[i]="?";
                tabgosc[i]="?";
            } else {

            }
        }
    }

    @Override
    public void onErrorLoading(String message) {
        materialButtonToggleGroup1.setVisibility(View.GONE);
        materialButtonToggleGroup2.setVisibility(View.GONE);
        Toast.makeText(getActivity(), "Connection problem, please try again later", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        cl.setVisibility(View.GONE);
        cl2.setVisibility(View.GONE);
        presenter.getData();
        materialButtonToggleGroup1.clearChecked();
        materialButtonToggleGroup2.clearChecked();
    }

    //zamienia dane z bazy na konkretna flage
    private int dajFlage(String gg) {
        int flag = 0;
        switch (gg) {
            case "AUT":
                flag = R.drawable.aut;
                break;
            case "BEL":
                flag = R.drawable.bel;
                break;
            case "CRO":
                flag = R.drawable.cro;
                break;
            case "CZE":
                flag = R.drawable.cze;
                break;
            case "DEN":
                flag = R.drawable.den;
                break;
            case "ENG":
                flag = R.drawable.eng;
                break;
            case "ESP":
                flag = R.drawable.esp;
                break;
            case "FIN":
                flag = R.drawable.fin;
                break;
            case "FRA":
                flag = R.drawable.fra;
                break;
            case "GER":
                flag = R.drawable.ger;
                break;
            case "HUN":
                flag = R.drawable.hun;
                break;
            case "ITA":
                flag = R.drawable.ita;
                break;
            case "MKD":
                flag = R.drawable.mkd;
                break;
            case "NED":
                flag = R.drawable.ned;
                break;
            case "POL":
                flag = R.drawable.pol;
                break;
            case "POR":
                flag = R.drawable.por;
                break;
            case "RUS":
                flag = R.drawable.rus;
                break;
            case "SCO":
                flag = R.drawable.sco;
                break;
            case "SUI":
                flag = R.drawable.sui;
                break;
            case "SVN":
                flag = R.drawable.svn;
                break;
            case "SWE":
                flag = R.drawable.swe;
                break;
            case "TUR":
                flag = R.drawable.tur;
                break;
            case "UKR":
                flag = R.drawable.ukr;
                break;
            case "WAL":
                flag = R.drawable.wal;
                break;
            case "AAA":
                flag = R.drawable.pilka;
                break;
            case "?":
                flag = R.drawable.pilka;
                break;
        }
        return flag;
    }

    private void bubbleSort(Druzyna arr[])
    {
        int n = arr.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (arr[j].getPoints() > arr[j+1].getPoints())
                {
                    // zamiana arr[j+1] i arr[j]
                    Druzyna temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
    }

    private void bubbleSort2(Druzyna arr[])
    {
        int n = arr.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (arr[j].getGole() > arr[j+1].getGole())
                {
                    // zamiana arr[j+1] i arr[j]
                    Druzyna temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
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
