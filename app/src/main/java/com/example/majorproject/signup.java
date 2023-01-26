package com.example.majorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class signup extends AppCompatActivity {

    public static final String Getusername = "com.example.application.example.Getusername";
    public static final String URL2 = "http://100.26.167.84/Register.php";

    TextInputEditText textInputEditTextFullname, textInputEditTextUsername, textInputEditTextPassword, textInputEditTextEmail;
    Button signupbtn;
    TextView textViewLoginHere;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        textInputEditTextFullname = findViewById(R.id.fullname);
        textInputEditTextUsername = findViewById(R.id.username);
        textInputEditTextPassword = findViewById(R.id.password);
        textInputEditTextEmail = findViewById(R.id.email);
        signupbtn = findViewById(R.id.signupbtn);
        textViewLoginHere = findViewById(R.id.loginhere);
        progressBar = findViewById(R.id.progressBar);

        textViewLoginHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), login.class);
                startActivity(intent);
                finish();
            }
        });

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Fullname, Username, Password, Email;
                Fullname = String.valueOf(textInputEditTextFullname.getText());
                Username = String.valueOf(textInputEditTextUsername.getText());
                Password = String.valueOf(textInputEditTextPassword.getText());
                Email = String.valueOf(textInputEditTextEmail.getText());

                if(!Fullname.equals("") && !Username.equals("") && !Password.equals("") && !Email.equals("")) {

                    progressBar.setVisibility(View.VISIBLE);
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] field = new String[4];
                            field[0] = "fullname";
                            field[1] = "username";
                            field[2] = "password";
                            field[3] = "email";
                            //Creating array for data
                            String[] data = new String[4];
                            data[0] = Fullname;
                            data[1] = Username;
                            data[2] = Password;
                            data[3] = Email;
                            PutData putData = new PutData(URL2, "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();
                                    if(result.equals("Sign Up Successful")) {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                        EditText getusername = (EditText) findViewById(R.id.username);
                                        String username = getusername.getText().toString();
                                        Intent intent = new Intent(getBaseContext(), SuccessScreen.class);
                                        intent.putExtra(Getusername, username);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                            //End Write and Read data with URL
                        }
                    });
                }
                else {
                    Toast.makeText(getApplicationContext(),"Please ensure all fields are filled", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}