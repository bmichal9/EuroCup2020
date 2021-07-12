package com.example.eurocup2020.main;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eurocup2020.R;
import com.example.eurocup2020.log_reg.LoginActivity;
import com.example.eurocup2020.log_reg.SessionManager;

import java.util.HashMap;

public class Options extends AppCompatActivity {

    private TextView name, email;
    private Button btn_logout, btncopy;
    public SessionManager sessionManager;

    private ClipboardManager myClipboard;
    private ClipData myClip;
    private Switch sw1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        sessionManager= new SessionManager(this);

        HashMap<String, String> user = sessionManager.getUserDetail();
        final String mID=user.get(sessionManager.ID_USER);
        final String mEmail=user.get(sessionManager.EMAIL);
        final String mNamee=user.get(sessionManager.NAME);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        btn_logout = findViewById(R.id.btn_logout);
        btncopy = findViewById(R.id.btncopy);
       // sw1 = findViewById(R.id.switch1);

        name.setText("Name: "+mNamee);
        email.setText("Email: "+mEmail);

        //do kopiowania
        btncopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                String text;
                text = mNamee;

                myClip = ClipData.newPlainText("text", text);
                myClipboard.setPrimaryClip(myClip);

                Toast.makeText(getApplicationContext(), "Name copied", Toast.LENGTH_SHORT).show();

            }
        });

        //logout
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logout();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

                }
        });

      /*  sw1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(getApplicationContext(), "Notifications are ON", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Notifications are OFF", Toast.LENGTH_SHORT).show();
                }
            }
        });
        */

        //dodajemy toolbar
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //dodaje przycisk wroc do glownego widoku
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }
}
