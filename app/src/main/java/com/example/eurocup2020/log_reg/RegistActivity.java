package com.example.eurocup2020.log_reg;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.net.Uri;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.CheckBox;
        import android.widget.CompoundButton;
        import android.widget.ProgressBar;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.android.volley.Request;
        import com.android.volley.RequestQueue;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.StringRequest;
        import com.android.volley.toolbox.Volley;
        import com.example.eurocup2020.R;
        import com.example.eurocup2020.keys.PlayerConfig;
        import com.google.android.material.textfield.TextInputEditText;
        import com.google.android.material.textfield.TextInputLayout;

        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.HashMap;
        import java.util.Map;

public class RegistActivity extends AppCompatActivity {

    //widgety
    private TextInputEditText name, email, c_email, password, c_password;
    private Button btn_regist;
    private ProgressBar loading;

    private TextInputLayout inputName, inputEmail, inputCEmail, inputPassword, inputCPassword;

    private Boolean cbbool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        //standard
        loading=findViewById(R.id.loading1);
        name=findViewById(R.id.name);
        email=findViewById(R.id.r_email);
        c_email=findViewById(R.id.cr_email);
        password=findViewById(R.id.r_password);
        c_password=findViewById(R.id.cr_password);
        btn_regist = findViewById(R.id.btn2);
        CheckBox cb = findViewById(R.id.cb);
        TextView term = findViewById(R.id.term);
        inputName=findViewById(R.id.iName);
        inputEmail=findViewById(R.id.iEmail);
        inputCEmail=findViewById(R.id.icEmail);
        inputPassword=findViewById(R.id.iPassword);
        inputCPassword=findViewById(R.id.icPassword);

        //czy regulamin potwierdzony
        cbbool=false;

        //klikniecie w linka pokaz regulamin, trzeba zrobic regulamin i dodac linka!!
        term.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        openWebURL(PlayerConfig.RULES);
                                    }
                                }
        );

        //klikniecie w button rejestracji
        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateName()){
                    if(validateEmail()){
                        if(validateConfirmEmail()){
                            if(validatePassword()){
                                if(validateConfirmPassword()){
                                    if(!cbbool){
                                        Toast.makeText(RegistActivity.this, "Please confirm the Terms and Conditions and Private Policy", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Regist();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });

        //listener do checkboxa z regulaminem
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cbbool = ((CompoundButton) view).isChecked();
            }
        });
    }

    //robi linka do www z regulaminem
    public void openWebURL( String inURL ) {
        Intent browse = new Intent( Intent.ACTION_VIEW , Uri.parse( inURL ) );
        startActivity( browse );
    }

    //cala metoda do rejestracji
    private void Regist(){
        loading.setVisibility(View.VISIBLE);
        btn_regist.setVisibility(View.GONE);

        final String name=this.name.getText().toString().trim();
        final String email=this.email.getText().toString().trim();
        final String password=this.password.getText().toString().trim();

        String URL_REGIST = PlayerConfig.REGISTER;
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")){
                                Toast.makeText(RegistActivity.this, "Registered successfully! You can now login", Toast.LENGTH_SHORT).show();
                                loading.setVisibility(View.GONE);
                                btn_regist.setVisibility(View.VISIBLE);
                                finish();
                            }

                            if(success.equals("2")){
                                Toast.makeText(RegistActivity.this, "Name already registered, use another one", Toast.LENGTH_SHORT).show();
                                loading.setVisibility(View.GONE);
                                btn_regist.setVisibility(View.VISIBLE);
                            }

                            if(success.equals("3")){
                                Toast.makeText(RegistActivity.this, "Email already registered, use another one", Toast.LENGTH_SHORT).show();
                                loading.setVisibility(View.GONE);
                                btn_regist.setVisibility(View.VISIBLE);
                            }

                        } catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(RegistActivity.this, "Register error! Please try again later", Toast.LENGTH_SHORT).show();
                            loading.setVisibility(View.GONE);
                            btn_regist.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegistActivity.this, "Register error! Please try again later", Toast.LENGTH_SHORT).show();
                        loading.setVisibility(View.GONE);
                        btn_regist.setVisibility(View.VISIBLE);
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    //ponizej sprawdzenia edittextow z rejestracji
    private boolean validateName() {
        if (name.getText().toString().trim().isEmpty()) {
            inputName.setError("Name cannot be empty");
            return false;
        } else {
            if(name.getText().toString().trim().length()<4)
            {
                inputName.setError("Name must be at least 4 characters long");
                return false;
            } else {
                inputName.setErrorEnabled(false);
            }
        }
        return true;
    }

    //sprawdza czy email to email
    private boolean validateEmail() {
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString().trim()).matches()){
            inputEmail.setError("Put correct email");
            return false;
        } else {
            inputEmail.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateConfirmEmail(){
        if (!email.getText().toString().trim().equals(c_email.getText().toString().trim())) {
            inputCEmail.setError("Emails must be the same");
            return false;
        } else {
                inputCEmail.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validatePassword(){
        if (password.getText().toString().trim().isEmpty()) {
            inputPassword.setError("Password cannot be empty");
            return false;
        } else {
            if(password.getText().toString().trim().length()<4)
            {
                inputPassword.setError("Password must be at least 4 characters long");
                return false;
            } else {
                inputPassword.setErrorEnabled(false);
            }
        }
        return true;
    }

    private boolean validateConfirmPassword(){
        if (!password.getText().toString().trim().equals(c_password.getText().toString().trim())) {
            inputCPassword.setError("Passwords must be the same");
            return false;
        } else {
            inputCPassword.setErrorEnabled(false);
        }
        return true;
    }
}
