package com.example.eurocup2020.log_reg;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.eurocup2020.main.MainActivity;

import java.util.HashMap;

public class SessionManager {


    //standardowe dane do sessionmanagera
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;

    //co zapisujemy do srodka
    private static final String PREF_NAME="LOGIN";
    private static final String LOGIN="IS_LOGIN";
    public static final String ID_USER="ID_USER";
    public static final String EMAIL="EMAIL";
    public static final String NAME="NAME";

    //konstruktor
    public SessionManager(Context context){
        this.context=context;
        int PRIVATE_MODE = 0;
        sharedPreferences=context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    //dodaje dane do przechowania
    void createSession(String id_user, String email, String name){
        editor.putBoolean(LOGIN, true);
        editor.putString(ID_USER, id_user);
        editor.putString(EMAIL, email);
        editor.putString(NAME, name);
        editor.apply();
    }

    //sprawdza czy zalogowany
    private boolean isLoggin(){
        return sharedPreferences.getBoolean(LOGIN, false);
    }

    //jesli nie zalogowany to wypad do logowania
    public void checkLogin(){
        if(!this.isLoggin()){
            Intent i=new Intent(context, LoginActivity.class);
            ((MainActivity) context).finish();
            context.startActivity(i);
            }
    }

    //metoda do odczytywania danych z bazy
    public HashMap<String, String> getUserDetail(){
        HashMap<String, String> user=new HashMap<>();
        user.put(ID_USER, sharedPreferences.getString(ID_USER, null));
        user.put(EMAIL, sharedPreferences.getString(EMAIL, null));
        user.put(NAME, sharedPreferences.getString(NAME, null));
        return user;
    }

    //wylogowanie
    public void logout(){
        editor.clear();
        editor.commit();
    }

}