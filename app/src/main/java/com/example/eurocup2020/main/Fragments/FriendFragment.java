package com.example.eurocup2020.main.Fragments;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.eurocup2020.R;
import com.example.eurocup2020.keys.PlayerConfig;
import com.example.eurocup2020.log_reg.SessionManager;
import com.example.eurocup2020.main.Znajomi.CaptionedImagesAdapter2;
import com.example.eurocup2020.main.Znajomi.ZnajomiDetailActivity;
import com.example.eurocup2020.main.Znajomi.ZnajomiPresenter;
import com.example.eurocup2020.main.Znajomi.ZnajomiView;
import com.example.eurocup2020.model.Znajomi;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static android.content.ClipDescription.MIMETYPE_TEXT_PLAIN;

/**
 * A simple {@link Fragment} subclass.
 */
public class FriendFragment extends Fragment implements ZnajomiView {


    //interfejs
    private ZnajomiPresenter presenter1;
    private List<Znajomi> listaznajomych;

    //standard
    private RecyclerView friendlist;

    //do wyciagania danych z sessionmanagera
    private String ID_USERz, NAME_USER;

    //loading...
    private ProgressBar pbFriend, pbAdd;

    //do listy znajomych
    private EditText edAdd;
    public static int[] punkty, miejsce2;
    private boolean znajExist;
    private Button newFriend;
    public static String[] znajomydetail; //do listy znajomych zeby sprawdzac jakis warunek ale jaki?

    //do uporzadkowania listy a pom do sessionmanagera
    private int pozycja1, idpom;

    //wklej
    private ClipboardManager clipboard;
    private String pasteData;

    public FriendFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friend, container,false);

        //przypisanie podstawowych elementow
        pbAdd=view.findViewById(R.id.loadingAdd);
        friendlist = view.findViewById(R.id.friendlist11);
        pbFriend=view.findViewById(R.id.loadingFriends);

        //dane z sessionmanagera
        SessionManager sessionManager = new SessionManager(Objects.requireNonNull(getActivity()));

        HashMap<String, String> user1 = sessionManager.getUserDetail();
        ID_USERz=user1.get(SessionManager.ID_USER);
        NAME_USER=user1.get(SessionManager.NAME);

        idpom=Integer.parseInt(ID_USERz);

        //listener do dodawania friendsa i cala reszta z alertdialog
        newFriend=view.findViewById(R.id.newFriend1);

        //wczytujemy liste
        presenter1 = new ZnajomiPresenter(this);
        presenter1.getListeZnajomych(idpom);

        //wklej
        clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);

        newFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                View customLayout = getLayoutInflater().inflate(R.layout.edit_layout, null);

                edAdd=customLayout.findViewById(R.id.friendadd);

                AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));

                builder.setIcon(R.drawable.ic_action_friendadd);

                builder.setView(customLayout);

                builder.setCancelable(true);

                builder.setTitle("Add new friend");

                builder.setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if(edAdd.getText().toString().trim().isEmpty()){
                            Toast.makeText(getActivity(), "Friend not added", Toast.LENGTH_SHORT).show();
                        }else {
                            znajExist=false;
                            for(int d=0; d < listaznajomych.size(); d++){
                                if(listaznajomych.get(d).getName_znajomego().trim().equals(edAdd.getText().toString().trim())){
                                    znajExist=true;
                                }
                            }
                            if(!znajExist){
                                AddFriend();
                            } else {
                                Toast.makeText(getActivity(), "Friend is already on your list", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });

                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getActivity(), "Friend not added", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNeutralButton("Paste", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialogInterface, int i) {

                    }});
                final AlertDialog dialog=builder.create();
                dialog.show();

                dialog.getButton(AlertDialog.BUTTON_NEUTRAL).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!(clipboard.hasPrimaryClip())) {
                            edAdd.setText("");
                        } else {
                            if (!(clipboard.getPrimaryClipDescription().hasMimeType(MIMETYPE_TEXT_PLAIN))) {
                                edAdd.setText("");
                            } else {
                                ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);
                                pasteData = item.getText().toString();
                                edAdd.setText(pasteData);}
                            }
                    }
                });
            }
        });
        return view;
    }

    @Override
    public void showLoading() {
        pbFriend.setVisibility(View.VISIBLE);
        friendlist.setVisibility(View.GONE);
        newFriend.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        pbFriend.setVisibility(View.GONE);
        friendlist.setVisibility(View.VISIBLE);
        newFriend.setVisibility(View.VISIBLE);
    }

    @Override
    public void onGetResult(List<Znajomi> znajomis) {

        //tworzymy tabele do pracy z danymi z bazy
        listaznajomych=znajomis;

        String[] znajominazwa = new String[listaznajomych.size()];
        punkty = new int[listaznajomych.size()];
        znajomydetail = new String[listaznajomych.size()];
        miejsce2=new int[listaznajomych.size()];

        Znajomi znajomki;

        //sortowanie
        Collections.sort(listaznajomych, new Comparator<Znajomi>() {
            @Override
            public int compare(Znajomi lhs, Znajomi rhs) {
                return Integer.compare(rhs.getPunkty_razem(), lhs.getPunkty_razem());
            }
        });


        //przypisujemy dane z bazy i dodajemy * do zalogowanego uzytkownika
        for (int i = 0; i < znajominazwa.length; i++) {
            znajomki = listaznajomych.get(i);
            znajomydetail[i] = znajomki.getName_znajomego();
            if(!NAME_USER.equals(znajomki.getName_znajomego())){
                znajominazwa[i] = znajomki.getName_znajomego();
            } else {
                znajominazwa[i] = ("*"+znajomki.getName_znajomego());
            }
            punkty[i] = znajomki.getPunkty_razem();
            if (i == 0) {
                miejsce2[0] = 1;
            } else {
                if (punkty[i] == punkty[(i-1)]) {
                    miejsce2[i] = miejsce2[(i-1)];
                } else {
                    miejsce2[i] = i + 1;
                }

            }
        }

        //standard
        CaptionedImagesAdapter2 adapter = new CaptionedImagesAdapter2(znajominazwa, punkty, miejsce2);
        friendlist.setAdapter(adapter);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        friendlist.setLayoutManager(layoutManager);
        adapter.setListener(new CaptionedImagesAdapter2.Listener() {
            @Override
            public void onClick(int position) {
                pozycja1=position;
                Intent intent = new Intent(getActivity(), ZnajomiDetailActivity.class);
                intent.putExtra(ZnajomiDetailActivity.EXTRA_ZNAJOMI_ID, pozycja1);
                Objects.requireNonNull(getActivity()).startActivity(intent);

            }
        });
    }

    @Override
    public void onErrorLoading(String message) {
        newFriend.setVisibility(View.GONE);
        Toast.makeText(getActivity(), "Connection problem, please try again later", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter1.getListeZnajomych(idpom);
    }



    //dodajemy friendsa i sprawdzamy czy w ogole istnieje/juz jest na liscie/czy jest miejsce dla niego
    private void AddFriend(){
        pbAdd.setVisibility(View.VISIBLE);
        newFriend.setVisibility(View.GONE);
        friendlist.setVisibility(View.GONE);

        final String friendname=this.edAdd.getText().toString().trim();
        final String id_user=ID_USERz;

        String URL_REGIST = PlayerConfig.FRIENDADD;
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("0")){
                                Toast.makeText(getActivity(), "Connection problem, please try again later", Toast.LENGTH_SHORT).show();
                                pbAdd.setVisibility(View.GONE);
                                newFriend.setVisibility(View.VISIBLE);
                                friendlist.setVisibility(View.VISIBLE);
                            }

                            if (success.equals("1")){
                                Toast.makeText(getActivity(), "Friend add successful", Toast.LENGTH_SHORT).show();
                                pbAdd.setVisibility(View.GONE);
                                newFriend.setVisibility(View.VISIBLE);
                                presenter1.getListeZnajomych(idpom);
                            }

                            if(success.equals("2")){
                                Toast.makeText(getActivity(), "Friend list full, go to Options to increase it's capacity", Toast.LENGTH_SHORT).show();
                                pbAdd.setVisibility(View.GONE);
                                newFriend.setVisibility(View.VISIBLE);
                                friendlist.setVisibility(View.VISIBLE);
                            }

                            if(success.equals("3")){
                                Toast.makeText(getActivity(), "Invalid friend's name", Toast.LENGTH_SHORT).show();
                                pbAdd.setVisibility(View.GONE);
                                newFriend.setVisibility(View.VISIBLE);
                                friendlist.setVisibility(View.VISIBLE);
                            }

                        } catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "Connection problem, please try again later", Toast.LENGTH_SHORT).show();
                            pbAdd.setVisibility(View.GONE);
                            newFriend.setVisibility(View.VISIBLE);
                            friendlist.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Connection problem, please try again later", Toast.LENGTH_SHORT).show();
                        pbAdd.setVisibility(View.GONE);
                        newFriend.setVisibility(View.VISIBLE);
                        friendlist.setVisibility(View.VISIBLE);
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("friendname", friendname);
                params.put("id_user", id_user);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getActivity()));
        requestQueue.add(stringRequest);

    }
}
