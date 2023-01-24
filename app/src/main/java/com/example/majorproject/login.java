package com.example.majorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.net.URLConnection;

public class login extends AppCompatActivity {

    public static final String Getusername = "com.example.application.example.Getusername";
    public static final String URL = "http://192.168.50.200/MP/Login.php";

    TextInputEditText LoginUsername, LoginPassword;
    Button loginbtn;
    TextView textViewSignuphere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        LoginUsername = findViewById(R.id.username);
        LoginPassword = findViewById(R.id.password);
        loginbtn = findViewById(R.id.loginbtn);
        textViewSignuphere = findViewById(R.id.signuphere);

        textViewSignuphere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), signup.class);
                startActivity(intent);
                finish();
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Username, Password;
                Username = String.valueOf(LoginUsername.getText());
                Password = String.valueOf(LoginPassword.getText());

                if(!Username.equals("") && !Password.equals("")) {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] field = new String[2];
                            field[0] = "username";
                            field[1] = "password";
                            String[] data = new String[2];
                            data[0] = Username;
                            data[1] = Password;
                            PutData putData = new PutData(URL, "POST", field, data);
                            if(putData.startPut()) {
                                if(putData.onComplete()) {
                                    String result = putData.getResult();
                                    if(result.equals("Login Successful")) {
                                        Intent intent = new Intent(getBaseContext(), SuccessScreen.class);
                                        Toast.makeText(getBaseContext(), result, Toast.LENGTH_SHORT).show();
                                        EditText getusername = (EditText) findViewById(R.id.username);
                                        String username = getusername.getText().toString();
                                        intent.putExtra(Getusername, username);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }

                        }
                    });
                }
            }
        });


    }
}

