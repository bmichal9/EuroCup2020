package com.example.eurocup2020.log_reg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
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
import com.example.eurocup2020.main.MainActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    //widgety do logowania
    private TextInputEditText email, password;
    private Button btn_login, btn_regist1, btn_lost;
    private ProgressBar loading;
    private TextInputLayout inEmail, inPassword;

    //do zapisania danych z zalogowania
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //dane z logowania
        sessionManager=new SessionManager(this);

        //przypisanie widgetow do xmla
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btn_login = findViewById(R.id.btn_login);
        loading = findViewById(R.id.loading);
        btn_regist1=findViewById(R.id.btn_regist1);
        btn_lost=findViewById(R.id.btn_lost);
        inEmail=findViewById(R.id.loginEmail);
        inPassword=findViewById(R.id.loginPassword);

        //listener do logowania
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mEmail=email.getText().toString().trim();
                String mPass=password.getText().toString().trim();

                if(validateEmail()){
                    if(validatePassword()){
                        Login(mEmail, mPass);
                    }
                }
            }
        });

        //listener do btn register
        btn_regist1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegistActivity.class));
            }
        });

        //gdy haslo sie zapomni wysyla do strony z odzyskiwaniem
        btn_lost.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        startActivity(new Intent(LoginActivity.this, LostPass.class));
                                    }
                                }
        );

    }

    //metoda do logowania
    private void Login(final String email, final String password) {

        loading.setVisibility(View.VISIBLE);
        btn_login.setVisibility(View.GONE);
        btn_regist1.setVisibility(View.GONE);
        btn_lost.setVisibility(View.GONE);

        String URL_LOGIN = PlayerConfig.LOGIN;
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray= jsonObject.getJSONArray("login");

                            if(success.equals("1")){
                                for (int i=0; i<jsonArray.length(); i++){
                                    JSONObject object=jsonArray.getJSONObject(i);

                                    String id_user = object.getString("id_user").trim();
                                    String email = object.getString("email").trim();
                                    String name = object.getString("name").trim();

                                    sessionManager.createSession(id_user, email, name);

                                    Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                                    intent.putExtra("id_user", id_user);
                                    intent.putExtra("email", email);
                                    intent.putExtra("name", name);
                                    finish();
                                    startActivity(intent);
                                    loading.setVisibility(View.GONE);

                                }
                            }

                            if(success.equals("0")){
                                Toast.makeText(LoginActivity.this, "Wrong password/email", Toast.LENGTH_SHORT).show();
                                loading.setVisibility(View.GONE);
                                btn_login.setVisibility(View.VISIBLE);
                                btn_regist1.setVisibility(View.VISIBLE);
                                btn_lost.setVisibility(View.VISIBLE);
                                }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            loading.setVisibility(View.GONE);
                            btn_login.setVisibility(View.VISIBLE);
                            btn_regist1.setVisibility(View.VISIBLE);
                            btn_lost.setVisibility(View.VISIBLE);
                            Toast.makeText(LoginActivity.this, "Wrong password/email", Toast.LENGTH_LONG).show();
                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.setVisibility(View.GONE);
                        btn_login.setVisibility(View.VISIBLE);
                        btn_regist1.setVisibility(View.VISIBLE);
                        btn_lost.setVisibility(View.VISIBLE);
                        Toast.makeText(LoginActivity.this, "Connection problem! Please try again later", Toast.LENGTH_LONG).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    //otwiera linka do lost password
   /*
    public void openWebURL( String inURL ) {
        Intent browse = new Intent( Intent.ACTION_VIEW , Uri.parse( inURL ) );

        startActivity( browse );
    }
    */

    private boolean validateEmail(){
        if (email.getText().toString().trim().isEmpty()) {
            inEmail.setError("Email cannot be empty");
            return false;
        } else {
            if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString().trim()).matches())
            {
                inEmail.setError("Wrong email address");
                return false;
            } else {
                inEmail.setErrorEnabled(false);
            }
        }
        return true;
    }

    private boolean validatePassword(){
        if (password.getText().toString().trim().isEmpty()) {
            inPassword.setError("Password cannot be empty");
            return false;
        } else {
            if(password.getText().toString().trim().length()<4)
            {
                inPassword.setError("Password too short");
                return false;
            } else {
                inPassword.setErrorEnabled(false);
            }
        }
        return true;
    }

}
